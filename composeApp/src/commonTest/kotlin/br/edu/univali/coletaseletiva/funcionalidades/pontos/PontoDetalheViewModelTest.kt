package br.edu.univali.coletaseletiva.funcionalidades.pontos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class PontoDetalheViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val pontoExemplo = PontoDeDescarte(
        id = 100L,
        municipioId = 1L,
        nome = "Ecoponto Central",
        endereco = "Rua Central, 500",
        horario = "Seg a Sex, 8h às 18h",
        latitude = -26.91,
        longitude = -48.66,
        materiais = listOf(
            MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
            MaterialAceito(Material.PILHAS_BATERIAS),
        ),
    )

    @Test
    fun carrega_o_ponto_pelo_id() = runTest {
        val repo = PontosRepositorioFake(listOf(pontoExemplo))
        val viewModel = PontoDetalheViewModel(pontoId = 100L, pontosRepo = repo)

        testDispatcher.scheduler.advanceUntilIdle()

        val estado = viewModel.estado.value
        assertFalse(estado.carregando)
        assertFalse(estado.naoEncontrado)
        assertNotNull(estado.ponto)
        assertEquals("Ecoponto Central", estado.ponto?.nome)
        assertEquals("https://www.google.com/maps/search/?api=1&query=-26.91,-48.66", estado.urlDaRota)
    }

    @Test
    fun id_inexistente_marca_naoEncontrado() = runTest {
        val repo = PontosRepositorioFake(listOf(pontoExemplo))
        val viewModel = PontoDetalheViewModel(pontoId = 999L, pontosRepo = repo)

        testDispatcher.scheduler.advanceUntilIdle()

        val estado = viewModel.estado.value
        assertFalse(estado.carregando)
        assertTrue(estado.naoEncontrado)
        assertNull(estado.ponto)
    }
}
