package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PontoDetalheTelaTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_dados_e_botao_abrir_rota() {
        val pontoExemplo = PontoDeDescarte(
            id = 1L,
            municipioId = 1L,
            nome = "Ecoponto Central",
            endereco = "Rua Lauro Müller, 150",
            horario = "Seg a Sex, 8h às 17h",
            latitude = -26.9085,
            longitude = -48.6612,
            materiais = listOf(
                MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
            ),
        )

        val estadoExemplo = PontoDetalheEstado(
            carregando = false,
            ponto = pontoExemplo,
            naoEncontrado = false,
            urlDaRota = "https://www.google.com/maps/search/?api=1&query=-26.9085,-48.6612",
        )

        rule.setContent {
            PontoDetalheTela(
                estado = estadoExemplo,
                onAbrirRota = {},
                onVoltar = {},
            )
        }

        rule.onNodeWithText("Ecoponto Central").assertIsDisplayed()
        rule.onNodeWithText("Rua Lauro Müller, 150").assertIsDisplayed()
        rule.onNodeWithText("Abrir rota").assertIsDisplayed()
    }
}
