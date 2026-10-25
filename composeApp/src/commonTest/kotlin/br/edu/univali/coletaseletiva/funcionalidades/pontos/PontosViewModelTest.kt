package br.edu.univali.coletaseletiva.funcionalidades.pontos

import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorioFake
import br.edu.univali.coletaseletiva.funcionalidades.local.Municipio
import br.edu.univali.coletaseletiva.funcionalidades.local.Setor
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
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class PontosViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val municipio1 = Municipio(1L, "Itajaí", "SC")
    private val municipio2 = Municipio(2L, "Camboriú", "SC")

    private val setor1 = Setor(10L, 1L, "Centro Itajaí")
    private val setor2 = Setor(20L, 2L, "Centro Camboriú")

    private val pontoM1 = PontoDeDescarte(
        id = 1L,
        municipioId = 1L,
        nome = "Ponto Itajaí",
        endereco = "End 1",
        horario = "8h-18h",
        latitude = -26.9,
        longitude = -48.6,
        materiais = listOf(MaterialAceito(Material.PAPEL)),
    )

    private val pontoM2 = PontoDeDescarte(
        id = 2L,
        municipioId = 2L,
        nome = "Ponto Camboriú",
        endereco = "End 2",
        horario = "8h-18h",
        latitude = -27.0,
        longitude = -48.6,
        materiais = listOf(
            MaterialAceito(Material.PILHAS_BATERIAS),
            MaterialAceito(Material.OLEO),
        ),
    )

    @Test
    fun mostra_so_os_pontos_do_municipio_escolhido() = runTest {
        val localRepo = LocalRepositorioFake(
            municipiosIniciais = listOf(municipio1, municipio2),
            setoresIniciais = listOf(setor1, setor2),
        )
        localRepo.escolherSetor(setor2.id) // Camboriú (municipioId = 2)

        val pontosRepo = PontosRepositorioFake(listOf(pontoM1, pontoM2))

        val viewModel = PontosViewModel(localRepo, pontosRepo)
        testDispatcher.scheduler.advanceUntilIdle()

        val estado = viewModel.estado.value
        assertFalse(estado.carregando)
        assertFalse(estado.semLocal)
        assertEquals(listOf(pontoM2), estado.pontosFiltrados)
    }

    @Test
    fun aviso_rn04_ligado_so_para_pilhas_baterias_e_oleo() = runTest {
        val localRepo = LocalRepositorioFake(
            municipiosIniciais = listOf(municipio2),
            setoresIniciais = listOf(setor2),
        )
        localRepo.escolherSetor(setor2.id)

        val pontosRepo = PontosRepositorioFake(listOf(pontoM2))

        val viewModel = PontosViewModel(localRepo, pontosRepo)
        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse(viewModel.estado.value.avisoRN04)

        viewModel.filtrarPor(Material.PAPEL)
        testDispatcher.scheduler.advanceUntilIdle()
        assertFalse(viewModel.estado.value.avisoRN04)

        viewModel.filtrarPor(Material.PILHAS_BATERIAS)
        testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.estado.value.avisoRN04)

        viewModel.filtrarPor(Material.OLEO)
        testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.estado.value.avisoRN04)
    }

    @Test
    fun trocar_filtro_atualiza_a_lista() = runTest {
        val localRepo = LocalRepositorioFake(
            municipiosIniciais = listOf(municipio2),
            setoresIniciais = listOf(setor2),
        )
        localRepo.escolherSetor(setor2.id)

        val pontoOutro = PontoDeDescarte(
            id = 3L,
            municipioId = 2L,
            nome = "Ponto Vidros",
            endereco = "End 3",
            horario = "8h-18h",
            latitude = -27.0,
            longitude = -48.6,
            materiais = listOf(MaterialAceito(Material.VIDRO)),
        )

        val pontosRepo = PontosRepositorioFake(listOf(pontoM2, pontoOutro))

        val viewModel = PontosViewModel(localRepo, pontosRepo)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(2, viewModel.estado.value.pontosFiltrados.size)

        viewModel.filtrarPor(Material.VIDRO)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(listOf(pontoOutro), viewModel.estado.value.pontosFiltrados)
        assertEquals(Material.VIDRO, viewModel.estado.value.filtro)
    }
}
