package br.edu.univali.coletaseletiva.funcionalidades.guia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

// PB11 · critérios 2, 5 e 7 na tela (R5).
class GuiaTelaTest {

    @get:Rule
    val rule = createComposeRule()

    private val caixaDeLeite = GuiaItem(
        id = 1,
        nome = "Caixa de leite (longa vida)",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Enxágue e amasse.",
        sinonimos = emptyList(),
    )
    private val garrafaPet = GuiaItem(
        id = 2,
        nome = "Garrafa PET",
        destino = Destino.COLETA_SELETIVA,
        orientacao = "Esvazie e amasse.",
        sinonimos = listOf("refrigerante"),
    )
    private val oleo = GuiaItem(
        id = 3,
        nome = "Óleo de cozinha usado",
        destino = Destino.PONTO_DE_DESCARTE,
        orientacao = "Leve a um ponto de descarte.",
        sinonimos = emptyList(),
    )
    private val todos = listOf(caixaDeLeite, garrafaPet, oleo)

    @Test
    fun digitar_filtra_os_itens_na_tela() {
        rule.setContent {
            // Simula o ViewModel: digitar recalcula a lista com a mesma regra do app.
            var estado by remember { mutableStateOf(GuiaEstado(carregando = false, itens = todos)) }
            GuiaTela(
                estado = estado,
                onDigitar = { termo ->
                    val itens = buscarNoGuia(todos, termo)
                    estado = GuiaEstado(false, termo, itens, itens.isEmpty())
                },
                onAbrir = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Garrafa PET").assertIsDisplayed()

        rule.onNodeWithTag(TAG_CAMPO_BUSCA).performTextInput("leite")

        rule.onNodeWithText("Caixa de leite (longa vida)").assertIsDisplayed()
        rule.onNodeWithText("Garrafa PET").assertDoesNotExist()
    }

    @Test
    fun sem_resultado_mostra_nada_encontrado() {
        rule.setContent {
            GuiaTela(
                estado = GuiaEstado(carregando = false, termo = "pneu", itens = emptyList(), nadaEncontrado = true),
                onDigitar = {},
                onAbrir = {},
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Nada encontrado").assertIsDisplayed()
    }

    @Test
    fun detalhe_mostra_destino_orientacao_e_leva_aos_pontos() {
        var foiParaPontos = false
        rule.setContent {
            GuiaDetalheTela(
                estado = GuiaDetalheEstado(carregando = false, item = oleo),
                onVerPontos = { foiParaPontos = true },
                onVoltar = {},
            )
        }
        rule.onNodeWithText("Óleo de cozinha usado").assertIsDisplayed()
        rule.onNodeWithText("Ponto de descarte").assertIsDisplayed()
        rule.onNodeWithText("Leve a um ponto de descarte.").assertIsDisplayed()

        rule.onNodeWithText("Ver pontos de descarte").performClick()
        assertTrue(foiParaPontos)
    }
}
