package br.edu.univali.coletaseletiva.funcionalidades.local

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class EscolherLocalTelaTest {
    @get:Rule
    val rule = createComposeRule()

    private val itajai = Municipio(1, "Itajaí", "SC")

    @Test
    fun mostra_titulo_e_municipios() {
        rule.setContent {
            EscolherLocalTela(
                estado = EscolherLocalEstado(carregando = false, municipios = listOf(itajai)),
                onEscolherMunicipio = {},
                onEscolherSetor = {},
                onConfirmar = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Escolha seu local").assertIsDisplayed()
        rule.onNodeWithText("Itajaí · SC").assertIsDisplayed()
        rule.onNodeWithText("Sua escolha fica só no aparelho.").assertIsDisplayed()
    }

    @Test
    fun sem_municipios_mostra_aviso() {
        rule.setContent {
            EscolherLocalTela(
                estado = EscolherLocalEstado(carregando = false, semMunicipios = true),
                onEscolherMunicipio = {},
                onEscolherSetor = {},
                onConfirmar = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Nenhum município cadastrado").assertIsDisplayed()
    }

    @Test
    fun confirmar_so_fica_ativo_com_setor_escolhido() {
        val setor = Setor(1, 1, "Centro")
        rule.setContent {
            EscolherLocalTela(
                estado = EscolherLocalEstado(
                    carregando = false,
                    municipios = listOf(itajai),
                    municipioSelecionado = itajai,
                    setores = listOf(setor),
                ),
                onEscolherMunicipio = {},
                onEscolherSetor = {},
                onConfirmar = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Confirmar").assertIsNotEnabled()
    }

    @Test
    fun confirmar_ativo_quando_o_setor_foi_escolhido() {
        val setor = Setor(1, 1, "Centro")
        rule.setContent {
            EscolherLocalTela(
                estado = EscolherLocalEstado(
                    carregando = false,
                    municipios = listOf(itajai),
                    municipioSelecionado = itajai,
                    setores = listOf(setor),
                    setorSelecionado = setor,
                ),
                onEscolherMunicipio = {},
                onEscolherSetor = {},
                onConfirmar = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Confirmar").assertIsEnabled()
    }
}
