package br.edu.univali.coletaseletiva.funcionalidades.calendario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorio
import br.edu.univali.coletaseletiva.util.Relogio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

/** Um tipo de coleta já pronto para a tela: dias da semana (em ordem) e a próxima data, com feriados (RN03). */
data class ItemCalendario(
    val tipo: TipoColeta,
    val diasDaSemana: List<DayOfWeek>,
    val proximaData: LocalDate,
)

/** `semLocal` = o morador ainda não escolheu o setor; `local` = texto "Município · Setor". */
data class CalendarioEstado(
    val carregando: Boolean = true,
    val semLocal: Boolean = false,
    val local: String = "",
    val itens: List<ItemCalendario> = emptyList(),
)

/** Calendário de coleta do setor escolhido (RF03). */
class CalendarioViewModel(
    private val local: LocalRepositorio,
    private val calendario: CalendarioRepositorio,
    private val relogio: Relogio,
) : ViewModel() {

    private val _estado = MutableStateFlow(CalendarioEstado())
    val estado: StateFlow<CalendarioEstado> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            val escolhido = local.setorEscolhido()
            if (escolhido == null) {
                _estado.value = CalendarioEstado(carregando = false, semLocal = true)
                return@launch
            }
            val hoje = relogio.hoje()
            val itens = calendario.coletasDoSetor(escolhido.setorId)
                .filter { it.diasDaSemana.isNotEmpty() }
                .sortedBy { it.tipo.ordinal }
                .map { coleta ->
                    ItemCalendario(
                        tipo = coleta.tipo,
                        diasDaSemana = coleta.diasDaSemana.sortedBy { it.ordinal },
                        proximaData = proximaColeta(hoje, coleta.diasDaSemana),
                    )
                }
            _estado.value = CalendarioEstado(
                carregando = false,
                local = "${escolhido.municipio} · ${escolhido.setor}",
                itens = itens,
            )
        }
    }
}
