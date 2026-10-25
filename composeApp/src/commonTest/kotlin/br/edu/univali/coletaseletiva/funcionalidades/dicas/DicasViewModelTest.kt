package br.edu.univali.coletaseletiva.funcionalidades.dicas

import br.edu.univali.coletaseletiva.util.RelogioFixo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DicasViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun lista_tem_pelo_menos_oito_dicas_completas() = runTest {
        val repo = DicasRepositorioEmMemoria()
        val relogio = RelogioFixo(LocalDate(2025, 1, 15))
        val viewModel = DicasViewModel(repo, relogio)

        testDispatcher.scheduler.advanceUntilIdle()

        val estado = viewModel.estado.value
        assertFalse(estado.carregando)
        assertTrue(estado.dicas.size >= 8)

        estado.dicas.forEach { dica ->
            assertTrue(dica.titulo.isNotBlank())
            assertTrue(dica.texto.isNotBlank())
        }
    }

    @Test
    fun destaca_a_dica_do_dia() = runTest {
        val repo = DicasRepositorioEmMemoria()
        val dataFixa = LocalDate(2025, 1, 10)
        val relogio = RelogioFixo(dataFixa)

        val viewModel = DicasViewModel(repo, relogio)
        testDispatcher.scheduler.advanceUntilIdle()

        val estado = viewModel.estado.value
        assertNotNull(estado.dicaDoDia)

        val esperada = dicaDoDia(repo.listar(), dataFixa)
        assertEquals(esperada, estado.dicaDoDia)
    }
}
