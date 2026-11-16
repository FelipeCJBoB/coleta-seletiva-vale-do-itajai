package br.edu.univali.coletaseletiva.funcionalidades.guia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Estado da lista do guia: `itens` já vem filtrado pelo `termo`. */
data class GuiaEstado(
    val carregando: Boolean = true,
    val termo: String = "",
    val itens: List<GuiaItem> = emptyList(),
    val nadaEncontrado: Boolean = false,
)

/** Lista do guia com busca (RF08). */
class GuiaViewModel(private val repo: GuiaRepositorio) : ViewModel() {

    private val _estado = MutableStateFlow(GuiaEstado())
    val estado: StateFlow<GuiaEstado> = _estado.asStateFlow()

    private var todos: List<GuiaItem> = emptyList()

    init {
        viewModelScope.launch {
            todos = repo.listar()
            aplicarBusca(_estado.value.termo, carregando = false)
        }
    }

    fun digitar(termo: String) {
        aplicarBusca(termo, carregando = _estado.value.carregando)
    }

    private fun aplicarBusca(termo: String, carregando: Boolean) {
        val itens = buscarNoGuia(todos, termo)
        _estado.value = GuiaEstado(
            carregando = carregando,
            termo = termo,
            itens = itens,
            nadaEncontrado = !carregando && itens.isEmpty(),
        )
    }
}

/** Estado do detalhe de um item do guia. */
data class GuiaDetalheEstado(
    val carregando: Boolean = true,
    val item: GuiaItem? = null,
    val naoEncontrado: Boolean = false,
)

/** Detalhe de um item: nome, destino e orientação. */
class GuiaDetalheViewModel(
    private val itemId: Long,
    private val repo: GuiaRepositorio,
) : ViewModel() {

    private val _estado = MutableStateFlow(GuiaDetalheEstado())
    val estado: StateFlow<GuiaDetalheEstado> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            val item = repo.buscar(itemId)
            _estado.value = GuiaDetalheEstado(
                carregando = false,
                item = item,
                naoEncontrado = item == null,
            )
        }
    }
}
