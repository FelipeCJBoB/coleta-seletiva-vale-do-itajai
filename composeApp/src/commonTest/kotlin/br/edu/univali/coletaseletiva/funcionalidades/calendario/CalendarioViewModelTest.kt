package br.edu.univali.coletaseletiva.funcionalidades.calendario

import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorioFake
import br.edu.univali.coletaseletiva.funcionalidades.local.Municipio
import br.edu.univali.coletaseletiva.funcionalidades.local.Setor
import br.edu.univali.coletaseletiva.util.RelogioFixo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// PB08 · critérios 7 e 8: o que a tela de calendário recebe do ViewModel.
@OptIn(ExperimentalCoroutinesApi::class)
class CalendarioViewModelTest {

    // Sexta-feira, 23/10/2026.
    private val relogio = RelogioFixo(LocalDate(2026, 10, 23))

    private fun localComSetor5() = LocalRepositorioFake(
        municipiosIniciais = listOf(Municipio(2, "Camboriú", "SC")),
        setoresIniciais = listOf(Setor(5, 2, "Monte Alegre")),
    )

    @BeforeTest
    fun antes() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun depois() {
        Dispatchers.resetMain()
    }

    @Test
    fun mostra_dias_e_proxima_data_dos_dois_tipos() = runTest {
        val local = localComSetor5()
        local.escolherSetor(5)
        val calendario = CalendarioRepositorioFake(
            mapOf(
                5L to listOf(
                    ColetaDoSetor(TipoColeta.COMUM, setOf(DayOfWeek.FRIDAY, DayOfWeek.TUESDAY)),
                    ColetaDoSetor(TipoColeta.SELETIVA, setOf(DayOfWeek.MONDAY)),
                ),
            ),
        )

        val estado = CalendarioViewModel(local, calendario, relogio).estado.value

        assertFalse(estado.carregando)
        assertFalse(estado.semLocal)
        assertEquals("Camboriú · Monte Alegre", estado.local)
        // A coleta seletiva vem primeiro, depois a comum.
        assertEquals(listOf(TipoColeta.SELETIVA, TipoColeta.COMUM), estado.itens.map { it.tipo })
        val seletiva = estado.itens[0]
        assertEquals(listOf(DayOfWeek.MONDAY), seletiva.diasDaSemana)
        assertEquals(LocalDate(2026, 10, 26), seletiva.proximaData)
        val comum = estado.itens[1]
        // Os dias vêm em ordem da semana, e hoje (sexta) já é dia de coleta comum.
        assertEquals(listOf(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY), comum.diasDaSemana)
        assertEquals(LocalDate(2026, 10, 23), comum.proximaData)
    }

    @Test
    fun sem_local_escolhido_pede_para_escolher() = runTest {
        val estado = CalendarioViewModel(localComSetor5(), CalendarioRepositorioFake(), relogio).estado.value

        assertFalse(estado.carregando)
        assertTrue(estado.semLocal)
        assertTrue(estado.itens.isEmpty())
    }
}
