package br.edu.univali.coletaseletiva.funcionalidades.dicas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.util.Relogio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado da tela de dicas de reciclagem.
 */
data class DicasEstado(
    val carregando: Boolean = true,
    val dicaDoDia: Dica? = null,
    val dicas: List<Dica> = emptyList(),
)

/**
 * ViewModel que carrega a lista de dicas e calcula a dica do dia.
 */
class DicasViewModel(
    private val repo: DicasRepositorio,
    private val relogio: Relogio,
) : ViewModel() {

    private val _estado = MutableStateFlow(DicasEstado())
    val estado: StateFlow<DicasEstado> = _estado.asStateFlow()

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            val lista = repo.listar()
            val hoje = relogio.hoje()
            val doDia = if (lista.isNotEmpty()) {
                dicaDoDia(lista, hoje)
            } else {
                null
            }

            _estado.value = DicasEstado(
                carregando = false,
                dicaDoDia = doDia,
                dicas = lista,
            )
        }
    }
}
