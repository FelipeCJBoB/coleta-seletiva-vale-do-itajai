package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.util.RelogioFixo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

// PB16 · critérios 6, 7 e 10: saldo, histórico e mensagens do registro.
@OptIn(ExperimentalCoroutinesApi::class)
class PontuacaoViewModelsTest {

    private val hoje = LocalDate(2026, 11, 16)
    private val relogio = RelogioFixo(hoje)

    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun saldo_soma_os_pontos() {
        val repo = DescarteRepositorioFake(
            listOf(
                Descarte(1, Material.PAPEL, 5, LocalDate(2026, 11, 14)),
                Descarte(2, Material.OLEO, 10, LocalDate(2026, 11, 15)),
                Descarte(3, Material.ELETRONICOS, 15, hoje),
            ),
        )

        val estado = MeusPontosViewModel(repo).estado.value

        assertFalse(estado.carregando)
        assertEquals(30, estado.saldo)
    }

    @Test
    fun historico_do_mais_recente_para_o_mais_antigo() {
        val repo = DescarteRepositorioFake(
            listOf(
                Descarte(1, Material.PAPEL, 5, LocalDate(2026, 11, 14)),
                Descarte(2, Material.OLEO, 10, LocalDate(2026, 11, 15)),
                Descarte(3, Material.ELETRONICOS, 15, hoje),
            ),
        )

        val historico = MeusPontosViewModel(repo).estado.value.historico

        assertEquals(listOf(hoje, LocalDate(2026, 11, 15), LocalDate(2026, 11, 14)), historico.map { it.data })
        assertEquals(listOf(Material.ELETRONICOS, Material.OLEO, Material.PAPEL), historico.map { it.material })
    }

    @Test
    fun registrar_com_limite_mostra_aviso() {
        val repo = DescarteRepositorioFake(List(3) { Descarte(it + 1L, Material.PAPEL, 5, hoje) })
        val vm = RegistrarDescarteViewModel(RegistrarDescarte(repo, relogio))

        vm.registrar(Material.VIDRO)

        assertEquals("Limite de 3 descartes por hoje atingido", vm.estado.value.mensagem)
        assertEquals(3, repo.guardados.size)
    }

    @Test
    fun registrar_com_sucesso_atualiza_o_saldo() {
        val repo = DescarteRepositorioFake()
        val vm = RegistrarDescarteViewModel(RegistrarDescarte(repo, relogio))
        assertNull(vm.estado.value.mensagem)
        // Só os materiais que pontuam são oferecidos (roupas ficam de fora, D-07).
        assertTrue(Material.ROUPAS !in vm.estado.value.materiaisDisponiveis)
        assertTrue(Material.PAPEL in vm.estado.value.materiaisDisponiveis)

        vm.registrar(Material.PAPEL)

        assertEquals("Você ganhou 5 pontos!", vm.estado.value.mensagem)
        assertEquals(5, MeusPontosViewModel(repo).estado.value.saldo)
    }
}
