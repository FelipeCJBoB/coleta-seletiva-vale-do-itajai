package br.edu.univali.coletaseletiva.funcionalidades.dicas

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class DicasTelaTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_a_dica_do_dia() {
        val dicaExemplo = Dica(
            id = 1L,
            titulo = "Enxágue embalagens recicláveis",
            texto = "Passe uma água rápida para retirar restos de comida.",
        )

        val estadoExemplo = DicasEstado(
            carregando = false,
            dicaDoDia = dicaExemplo,
            dicas = listOf(dicaExemplo),
        )

        rule.setContent {
            DicasTela(
                estado = estadoExemplo,
                onVoltar = {},
            )
        }

        rule.onNodeWithText("💡 DICA DO DIA").assertIsDisplayed()
        // O título aparece 2 vezes: no destaque ("dica do dia") e na lista. Por isso onAllNodesWithText.
        rule.onAllNodesWithText("Enxágue embalagens recicláveis").onFirst().assertIsDisplayed()
    }
}
