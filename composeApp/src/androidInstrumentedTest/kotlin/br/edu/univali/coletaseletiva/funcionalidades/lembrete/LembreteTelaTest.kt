package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

// PB12 · tela do lembrete (R5): interruptor, horários e aviso.
class LembreteTelaTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_o_interruptor_e_ativa_com_o_horario_escolhido() {
        var ativadoCom: LocalTime? = null

        rule.setContent {
            LembreteTela(
                estado = LembreteEstado(),
                onAtivar = { ativadoCom = it },
                onDesativar = {},
                onVoltar = {},
            )
        }

        rule.onNodeWithText("Lembrar na véspera").assertIsDisplayed()
        rule.onNodeWithText("20:00").performClick()
        rule.onNodeWithText("Lembrar na véspera").performClick()
        assertEquals(LocalTime(20, 0), ativadoCom)
    }

    @Test
    fun ativo_mostra_quando_vai_avisar() {
        val estado = LembreteEstado(ativo = true, proximoAviso = LocalDateTime(2026, 10, 25, 19, 0))

        rule.setContent {
            LembreteTela(estado = estado, onAtivar = {}, onDesativar = {}, onVoltar = {})
        }

        rule.onNodeWithText("Próximo aviso: 25/10/2026 às 19:00").assertIsDisplayed()
    }

    @Test
    fun sem_local_pede_para_escolher_o_local() {
        rule.setContent {
            LembreteTela(estado = LembreteEstado(semLocal = true), onAtivar = {}, onDesativar = {}, onVoltar = {})
        }

        rule.onNodeWithText("Escolha seu local para receber o lembrete.").assertIsDisplayed()
    }
}
