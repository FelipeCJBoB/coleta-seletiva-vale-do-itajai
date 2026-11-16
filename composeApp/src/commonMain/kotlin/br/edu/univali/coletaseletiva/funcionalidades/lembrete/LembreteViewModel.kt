package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.funcionalidades.calendario.CalendarioRepositorio
import br.edu.univali.coletaseletiva.funcionalidades.calendario.proximaColeta
import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorio
import br.edu.univali.coletaseletiva.util.Relogio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus

/** `proximoAviso` = quando o aparelho vai avisar (nulo com o lembrete desativado). */
data class LembreteEstado(
    val ativo: Boolean = false,
    val horario: LocalTime = LocalTime(19, 0),
    val semLocal: Boolean = false,
    val proximoAviso: LocalDateTime? = null,
)

/** Liga e desliga o lembrete da véspera da coleta (RF02). */
class LembreteViewModel(
    private val agendador: AgendadorDeLembrete,
    private val calendario: CalendarioRepositorio,
    private val local: LocalRepositorio,
    private val relogio: Relogio,
) : ViewModel() {

    private val _estado = MutableStateFlow(LembreteEstado())
    val estado: StateFlow<LembreteEstado> = _estado.asStateFlow()

    /** Agenda o aviso da véspera da próxima coleta (a mais cedo entre seletiva e comum) no horário escolhido. */
    fun ativar(horario: LocalTime) {
        viewModelScope.launch {
            val escolhido = local.setorEscolhido()
            if (escolhido == null) {
                _estado.value = _estado.value.copy(ativo = false, semLocal = true, proximoAviso = null)
                return@launch
            }
            // A partir de amanhã: se a coleta é hoje, a véspera já passou e o aviso vai para a coleta seguinte.
            val amanha = relogio.hoje().plus(DatePeriod(days = 1))
            val proxima = calendario.coletasDoSetor(escolhido.setorId)
                .filter { it.diasDaSemana.isNotEmpty() }
                .minOfOrNull { proximaColeta(amanha, it.diasDaSemana) }
            if (proxima == null) {
                _estado.value = _estado.value.copy(ativo = false, semLocal = false, proximoAviso = null)
                return@launch
            }
            val quando = dataDoLembrete(proxima, horario)
            agendador.agendar(quando)
            _estado.value = LembreteEstado(ativo = true, horario = horario, proximoAviso = quando)
        }
    }

    fun desativar() {
        agendador.cancelar()
        _estado.value = _estado.value.copy(ativo = false, semLocal = false, proximoAviso = null)
    }
}
