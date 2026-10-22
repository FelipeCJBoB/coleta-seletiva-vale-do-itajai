package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.edu.univali.coletaseletiva.navegacao.Rota
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class InicioTelaTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_os_cinco_atalhos_e_o_aviso_de_demonstracao() {
        rule.setContent {
            InicioTela(estado = InicioEstado(carregando = false, local = "Itajaí · Centro"), onIr = {})
        }
        rule.onNodeWithText("Itajaí · Centro").assertIsDisplayed()
        listOf("Calendário", "Pontos de descarte", "Guia de separação", "Meus pontos", "Dicas").forEach {
            rule.onNodeWithText(it).assertIsDisplayed()
        }
        rule.onNodeWithText("Dados de demonstração").assertIsDisplayed()
    }

    @Test
    fun sem_local_mostra_o_botao_escolher_local() {
        rule.setContent {
            InicioTela(estado = InicioEstado(carregando = false, precisaEscolherLocal = true), onIr = {})
        }
        rule.onNodeWithText("Escolher local").assertIsDisplayed()
    }

    @Test
    fun atalho_chama_a_rota_certa_com_um_toque() {
        var aberta: Rota? = null
        rule.setContent {
            InicioTela(estado = InicioEstado(carregando = false, local = "Itajaí · Centro"), onIr = { aberta = it })
        }
        rule.onNodeWithText("Calendário").performClick()
        assertEquals(Rota.Calendario, aberta)
    }
}
