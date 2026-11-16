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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/** Agendador de mentira: só anota o que o ViewModel pediu. */
class AgendadorFalso : AgendadorDeLembrete {
    val agendados = mutableListOf<LocalDateTime>()
    var cancelamentos = 0

    override fun agendar(quando: LocalDateTime) {
        agendados += quando
    }

    override fun cancelar() {
        cancelamentos++
    }
}

// PB12 · critérios 3 e 4: ativar agenda a véspera certa; desativar cancela.
@OptIn(ExperimentalCoroutinesApi::class)
class LembreteViewModelTest {

    // Sexta-feira, 23/10/2026.
    private val relogio = RelogioFixo(LocalDate(2026, 10, 23))
    private val agendador = AgendadorFalso()

    private suspend fun localEscolhido(): LocalRepositorioFake {
        val local = LocalRepositorioFake(
            municipiosIniciais = listOf(Municipio(2, "Camboriú", "SC")),
            setoresIniciais = listOf(Setor(5, 2, "Monte Alegre")),
        )
        local.escolherSetor(5)
        return local
    }

    private val calendario = CalendarioRepositorioFake(
        mapOf(
            5L to listOf(
                ColetaDoSetor(TipoColeta.SELETIVA, setOf(DayOfWeek.MONDAY)),
                ColetaDoSetor(TipoColeta.COMUM, setOf(DayOfWeek.THURSDAY)),
            ),
        ),
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
    fun ativar_agenda_a_vespera_da_proxima_coleta() = runTest {
        val vm = LembreteViewModel(agendador, calendario, localEscolhido(), relogio)

        vm.ativar(LocalTime(19, 0))

        // Hoje é sexta; a próxima coleta é a segunda 26/10 (a quinta 29/10 vem depois): aviso no domingo 25/10, 19:00.
        val esperado = LocalDateTime(2026, 10, 25, 19, 0)
        assertEquals(listOf(esperado), agendador.agendados)
        assertTrue(vm.estado.value.ativo)
        assertEquals(LocalTime(19, 0), vm.estado.value.horario)
        assertEquals(esperado, vm.estado.value.proximoAviso)
    }

    @Test
    fun desativar_cancela_o_agendamento() = runTest {
        val vm = LembreteViewModel(agendador, calendario, localEscolhido(), relogio)
        vm.ativar(LocalTime(19, 0))

        vm.desativar()

        assertEquals(1, agendador.cancelamentos)
        assertFalse(vm.estado.value.ativo)
        assertEquals(null, vm.estado.value.proximoAviso)
    }

    @Test
    fun hoje_e_dia_de_coleta_o_aviso_vai_para_a_proxima() = runTest {
        // Hoje é segunda 26/10 (dia de coleta seletiva): a véspera já passou, então avisa pela quinta 29/10 (quarta 28/10).
        relogio.data = LocalDate(2026, 10, 26)
        val vm = LembreteViewModel(agendador, calendario, localEscolhido(), relogio)

        vm.ativar(LocalTime(18, 30))

        assertEquals(listOf(LocalDateTime(2026, 10, 28, 18, 30)), agendador.agendados)
    }

    @Test
    fun sem_local_escolhido_nao_agenda_nada() = runTest {
        val semEscolha = LocalRepositorioFake()
        val vm = LembreteViewModel(agendador, calendario, semEscolha, relogio)

        vm.ativar(LocalTime(19, 0))

        assertTrue(agendador.agendados.isEmpty())
        assertFalse(vm.estado.value.ativo)
        assertTrue(vm.estado.value.semLocal)
    }
}
