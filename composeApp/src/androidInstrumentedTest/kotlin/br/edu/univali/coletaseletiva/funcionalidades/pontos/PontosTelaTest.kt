package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PontosTelaTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun mostra_chips_e_um_ponto() {
        val pontoExemplo = PontoDeDescarte(
            id = 1L,
            municipioId = 1L,
            nome = "Ecoponto Central",
            endereco = "Rua Silva, 100",
            horario = "Seg a Sex, 8h às 17h",
            latitude = -26.9,
            longitude = -48.6,
            materiais = listOf(
                MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
            ),
        )

        val estadoExemplo = PontosEstado(
            carregando = false,
            semLocal = false,
            pontosFiltrados = listOf(pontoExemplo),
            filtro = null,
            avisoRN04 = false,
        )

        rule.setContent {
            PontosTela(
                estado = estadoExemplo,
                onFiltrar = {},
                onAbrirPonto = {},
                onVoltar = {},
            )
        }

        rule.onNodeWithText("Ecoponto Central").assertIsDisplayed()
        rule.onNodeWithText("Todos").assertIsDisplayed()
        rule.onNodeWithText("Papel").assertIsDisplayed()
    }
}
