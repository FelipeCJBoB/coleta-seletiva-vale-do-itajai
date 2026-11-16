package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

// PB16 · critério 10: as duas telas mostram o que o morador precisa ver (R5).
class PontuacaoTelasTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun meus_pontos_mostra_saldo_e_botao() {
        val estado = MeusPontosEstado(
            carregando = false,
            saldo = 30,
            historico = listOf(
                Descarte(2, Material.ELETRONICOS, 15, LocalDate(2026, 11, 16)),
                Descarte(1, Material.OLEO, 10, LocalDate(2026, 11, 15)),
            ),
        )
        var abriuRegistro = false

        rule.setContent {
            MeusPontosTela(estado = estado, onRegistrar = { abriuRegistro = true }, onVoltar = {})
        }

        rule.onNodeWithText("Saldo: 30 pontos").assertIsDisplayed()
        rule.onNodeWithText("Eletrônicos").assertIsDisplayed()
        rule.onNodeWithText("16/11/2026").assertIsDisplayed()
        rule.onNodeWithText("Registro autodeclarado: no máximo 3 por dia.").assertIsDisplayed()
        rule.onNodeWithText("Registrar descarte").assertIsDisplayed().performClick()
        assertEquals(true, abriuRegistro)
    }

    @Test
    fun registrar_mostra_os_materiais() {
        var escolhido: Material? = null

        rule.setContent {
            RegistrarDescarteTela(
                estado = RegistrarEstado(mensagem = "Limite de 3 descartes por hoje atingido"),
                onRegistrar = { escolhido = it },
                onVoltar = {},
            )
        }

        rule.onNodeWithText("Papel").assertIsDisplayed()
        rule.onNodeWithText("Eletrônicos").assertIsDisplayed()
        rule.onNodeWithText("Limite de 3 descartes por hoje atingido").assertIsDisplayed()
        rule.onNodeWithText("Papel").performClick()
        assertEquals(Material.PAPEL, escolhido)
    }
}
