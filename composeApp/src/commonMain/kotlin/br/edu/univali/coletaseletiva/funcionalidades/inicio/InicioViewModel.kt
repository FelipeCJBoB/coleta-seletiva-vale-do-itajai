package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class InicioEstado(
    val carregando: Boolean = true,
    /** Texto "Município · Setor" ou nulo se o usuário ainda não escolheu. */
    val local: String? = null,
    val precisaEscolherLocal: Boolean = false,
)

class InicioViewModel(private val local: LocalRepositorio) : ViewModel() {
    private val _estado = MutableStateFlow(InicioEstado())
    val estado: StateFlow<InicioEstado> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            val escolhido = local.setorEscolhido()
            _estado.value = InicioEstado(
                carregando = false,
                local = escolhido?.let { "${it.municipio} · ${it.setor}" },
                precisaEscolherLocal = escolhido == null,
            )
        }
    }
}
