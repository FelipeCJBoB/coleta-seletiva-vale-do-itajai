package br.edu.univali.coletaseletiva.funcionalidades.calendario

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

// PB08 · critérios 7 e 8 na tela (R5).
class CalendarioTelaTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_os_dois_tipos_e_a_proxima_data() {
        val estado = CalendarioEstado(
            carregando = false,
            local = "Camboriú · Monte Alegre",
            itens = listOf(
                ItemCalendario(TipoColeta.SELETIVA, listOf(DayOfWeek.MONDAY), LocalDate(2026, 10, 26)),
                ItemCalendario(TipoColeta.COMUM, listOf(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY), LocalDate(2026, 10, 23)),
            ),
        )

        rule.setContent { CalendarioTela(estado = estado, onVoltar = {}, onEscolherLocal = {}) }

        rule.onNodeWithText("Camboriú · Monte Alegre").assertIsDisplayed()
        rule.onNodeWithText("Coleta seletiva").assertIsDisplayed()
        rule.onNodeWithText("Segunda-feira").assertIsDisplayed()
        rule.onNodeWithText("Próxima coleta: 26/10/2026").assertIsDisplayed()
        rule.onNodeWithText("Coleta comum").assertIsDisplayed()
        rule.onNodeWithText("Terça-feira, Sexta-feira").assertIsDisplayed()
        rule.onNodeWithText("Próxima coleta: 23/10/2026").assertIsDisplayed()
        rule.onNodeWithText("Não há horário fixo de passagem do caminhão.").assertIsDisplayed()
    }

    @Test
    fun sem_local_pede_para_escolher() {
        var abriuEscolha = false

        rule.setContent {
            CalendarioTela(
                estado = CalendarioEstado(carregando = false, semLocal = true),
                onVoltar = {},
                onEscolherLocal = { abriuEscolha = true },
            )
        }

        rule.onNodeWithText("Escolha seu local").assertIsDisplayed()
        rule.onNodeWithText("Escolher local").performClick()
        assertEquals(true, abriuEscolha)
    }
}
