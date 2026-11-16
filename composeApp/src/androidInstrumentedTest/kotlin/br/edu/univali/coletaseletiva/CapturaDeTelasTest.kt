package br.edu.univali.coletaseletiva

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import br.edu.univali.coletaseletiva.funcionalidades.calendario.CalendarioEstado
import br.edu.univali.coletaseletiva.funcionalidades.calendario.CalendarioTela
import br.edu.univali.coletaseletiva.funcionalidades.calendario.ItemCalendario
import br.edu.univali.coletaseletiva.funcionalidades.calendario.TipoColeta
import br.edu.univali.coletaseletiva.funcionalidades.dicas.DadosDicasDemo
import br.edu.univali.coletaseletiva.funcionalidades.dicas.DicasEstado
import br.edu.univali.coletaseletiva.funcionalidades.dicas.DicasTela
import br.edu.univali.coletaseletiva.funcionalidades.guia.DadosGuiaDemo
import br.edu.univali.coletaseletiva.funcionalidades.guia.GuiaDetalheEstado
import br.edu.univali.coletaseletiva.funcionalidades.guia.GuiaDetalheTela
import br.edu.univali.coletaseletiva.funcionalidades.guia.GuiaEstado
import br.edu.univali.coletaseletiva.funcionalidades.guia.GuiaTela
import br.edu.univali.coletaseletiva.funcionalidades.inicio.InicioEstado
import br.edu.univali.coletaseletiva.funcionalidades.inicio.InicioTela
import br.edu.univali.coletaseletiva.funcionalidades.inicio.Tema
import br.edu.univali.coletaseletiva.funcionalidades.lembrete.LembreteEstado
import br.edu.univali.coletaseletiva.funcionalidades.lembrete.LembreteTela
import br.edu.univali.coletaseletiva.funcionalidades.local.DadosLocalDemo
import br.edu.univali.coletaseletiva.funcionalidades.local.EscolherLocalEstado
import br.edu.univali.coletaseletiva.funcionalidades.local.EscolherLocalTela
import br.edu.univali.coletaseletiva.funcionalidades.pontos.DadosPontosDemo
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.funcionalidades.pontos.PontoDetalheEstado
import br.edu.univali.coletaseletiva.funcionalidades.pontos.PontoDetalheTela
import br.edu.univali.coletaseletiva.funcionalidades.pontos.PontosEstado
import br.edu.univali.coletaseletiva.funcionalidades.pontos.PontosTela
import br.edu.univali.coletaseletiva.funcionalidades.pontuacao.Descarte
import br.edu.univali.coletaseletiva.funcionalidades.pontuacao.MeusPontosEstado
import br.edu.univali.coletaseletiva.funcionalidades.pontuacao.MeusPontosTela
import br.edu.univali.coletaseletiva.funcionalidades.pontuacao.RegistrarDescarteTela
import br.edu.univali.coletaseletiva.funcionalidades.pontuacao.RegistrarEstado
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.FileOutputStream

/**
 * Não testa nada: renderiza cada tela com dados de demonstração e salva um PNG por tela (numeradas,
 * na ordem de uso do app) em `files/prints` do aplicativo. O CI baixa essa pasta como artefato
 * "prints-das-telas", para usar nos prints do relatório (5.1.1) e no vídeo.
 */
class CapturaDeTelasTest {

    @get:Rule
    val rule = createComposeRule()

    private val pasta: File by lazy {
        val contexto = InstrumentationRegistry.getInstrumentation().targetContext
        File(contexto.getExternalFilesDir(null), "prints").apply { mkdirs() }
    }

    private var atual by mutableStateOf<@Composable () -> Unit>({})
    private var iniciado = false

    private fun capturar(nome: String, tela: @Composable () -> Unit) {
        if (!iniciado) {
            rule.setContent { Tema { Surface(Modifier.fillMaxSize()) { atual() } } }
            iniciado = true
        }
        rule.runOnIdle { atual = tela }
        rule.waitForIdle()
        val imagem = rule.onRoot().captureToImage().asAndroidBitmap()
        FileOutputStream(File(pasta, "$nome.png")).use {
            imagem.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    private val hoje = LocalDate(2026, 11, 16)

    @Test
    fun captura_todas_as_telas() {
        capturar("01-inicio") {
            InicioTela(InicioEstado(carregando = false, local = "Camboriú · Monte Alegre"), onIr = {})
        }

        val camboriu = DadosLocalDemo.municipios.last()
        capturar("02-escolher-local") {
            EscolherLocalTela(
                estado = EscolherLocalEstado(
                    carregando = false,
                    municipios = DadosLocalDemo.municipios,
                    municipioSelecionado = camboriu,
                    setores = DadosLocalDemo.setores.filter { it.municipioId == camboriu.id },
                    setorSelecionado = DadosLocalDemo.setores.first { it.nome == "Monte Alegre" },
                ),
                onEscolherMunicipio = {},
                onEscolherSetor = {},
                onConfirmar = {},
                onVoltar = {},
            )
        }

        capturar("03-calendario") {
            CalendarioTela(
                estado = CalendarioEstado(
                    carregando = false,
                    local = "Camboriú · Monte Alegre",
                    itens = listOf(
                        ItemCalendario(TipoColeta.SELETIVA, listOf(DayOfWeek.FRIDAY), LocalDate(2026, 11, 20)),
                        ItemCalendario(TipoColeta.COMUM, listOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), LocalDate(2026, 11, 19)),
                    ),
                ),
                onVoltar = {},
                onEscolherLocal = {},
            )
        }

        val pontos = DadosPontosDemo.lista.filter { it.municipioId == camboriu.id }
        capturar("04-pontos-de-descarte") {
            PontosTela(
                estado = PontosEstado(carregando = false, pontosFiltrados = pontos),
                onFiltrar = {},
                onAbrirPonto = {},
                onVoltar = {},
            )
        }
        capturar("05-pontos-filtro-pilhas") {
            PontosTela(
                estado = PontosEstado(
                    carregando = false,
                    filtro = Material.PILHAS_BATERIAS,
                    avisoRN04 = true,
                    pontosFiltrados = pontos.filter { p -> p.materiais.any { it.material == Material.PILHAS_BATERIAS } },
                ),
                onFiltrar = {},
                onAbrirPonto = {},
                onVoltar = {},
            )
        }
        capturar("06-detalhe-do-ponto") {
            PontoDetalheTela(
                estado = PontoDetalheEstado(carregando = false, ponto = pontos.first(), urlDaRota = "geo:0,0"),
                onAbrirRota = {},
                onVoltar = {},
            )
        }

        val itens = DadosGuiaDemo.itens
        capturar("07-guia-busca") {
            GuiaTela(estado = GuiaEstado(carregando = false, itens = itens), onDigitar = {}, onAbrir = {}, onVoltar = {})
        }
        capturar("08-guia-detalhe") {
            GuiaDetalheTela(
                estado = GuiaDetalheEstado(carregando = false, item = itens.first { it.nome == "Pilhas" }),
                onVerPontos = {},
                onVoltar = {},
            )
        }

        capturar("09-meus-pontos") {
            MeusPontosTela(
                estado = MeusPontosEstado(
                    carregando = false,
                    saldo = 30,
                    historico = listOf(
                        Descarte(3, Material.ELETRONICOS, 15, hoje),
                        Descarte(2, Material.OLEO, 10, LocalDate(2026, 11, 15)),
                        Descarte(1, Material.PAPEL, 5, LocalDate(2026, 11, 14)),
                    ),
                ),
                onRegistrar = {},
                onVoltar = {},
            )
        }
        capturar("10-registrar-descarte") {
            RegistrarDescarteTela(
                estado = RegistrarEstado(mensagem = "Você ganhou 5 pontos!"),
                onRegistrar = {},
                onVoltar = {},
            )
        }

        capturar("11-dicas") {
            DicasTela(
                estado = DicasEstado(
                    carregando = false,
                    dicaDoDia = DadosDicasDemo.lista.first(),
                    dicas = DadosDicasDemo.lista,
                ),
                onVoltar = {},
            )
        }

        capturar("12-lembrete") {
            LembreteTela(
                estado = LembreteEstado(ativo = true, proximoAviso = LocalDateTime(2026, 11, 19, 19, 0)),
                onAtivar = {},
                onDesativar = {},
                onVoltar = {},
            )
        }
    }
}
