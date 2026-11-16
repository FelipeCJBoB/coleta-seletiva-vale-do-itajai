package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import br.edu.univali.coletaseletiva.funcionalidades.calendario.CalendarioRepositorioFake
import br.edu.univali.coletaseletiva.funcionalidades.calendario.ColetaDoSetor
import br.edu.univali.coletaseletiva.funcionalidades.calendario.TipoColeta
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
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

// PB12 · critério 4: o agendador recebe exatamente 1 pedido, com a data certa (R10).
@OptIn(ExperimentalCoroutinesApi::class)
class LembreteViewModelMockitoTest {

    private val relogio = RelogioFixo(LocalDate(2026, 10, 23))

    private suspend fun local(): LocalRepositorioFake {
        val local = LocalRepositorioFake(
            municipiosIniciais = listOf(Municipio(2, "Camboriú", "SC")),
            setoresIniciais = listOf(Setor(5, 2, "Monte Alegre")),
        )
        local.escolherSetor(5)
        return local
    }

    private val calendario = CalendarioRepositorioFake(
        mapOf(5L to listOf(ColetaDoSetor(TipoColeta.SELETIVA, setOf(DayOfWeek.MONDAY)))),
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
    fun salvar_chama_o_agendador_uma_vez_com_a_data_certa() = runTest {
        val agendador = mock<AgendadorDeLembrete>()

        LembreteViewModel(agendador, calendario, local(), relogio).ativar(LocalTime(19, 0))

        verify(agendador, times(1)).agendar(LocalDateTime(2026, 10, 25, 19, 0))
        verify(agendador, never()).cancelar()
    }
}
