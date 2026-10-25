package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado da tela de pontos de descarte.
 */
data class PontosEstado(
    val carregando: Boolean = true,
    val semLocal: Boolean = false,
    val pontosFiltrados: List<PontoDeDescarte> = emptyList(),
    val filtro: Material? = null,
    val avisoRN04: Boolean = false,
)

/**
 * ViewModel que gerencia a lista de pontos de descarte por município e filtro por material.
 */
class PontosViewModel(
    private val localRepo: LocalRepositorio,
    private val pontosRepo: PontosRepositorio,
) : ViewModel() {

    private val _estado = MutableStateFlow(PontosEstado())
    val estado: StateFlow<PontosEstado> = _estado.asStateFlow()

    private var todosPontosDoMunicipio: List<PontoDeDescarte> = emptyList()

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            val escolhido = localRepo.setorEscolhido()
            if (escolhido == null) {
                _estado.value = PontosEstado(carregando = false, semLocal = true)
                return@launch
            }

            val municipio = localRepo.listarMunicipios().find { it.nome == escolhido.municipio }
            if (municipio == null) {
                _estado.value = PontosEstado(carregando = false, semLocal = true)
                return@launch
            }

            todosPontosDoMunicipio = pontosRepo.pontosDoMunicipio(municipio.id)
            atualizarEstado()
        }
    }

    fun filtrarPor(material: Material?) {
        _estado.value = _estado.value.copy(filtro = material)
        atualizarEstado()
    }

    private fun atualizarEstado() {
        val filtroAtual = _estado.value.filtro
        val filtrados = filtrarPontos(todosPontosDoMunicipio, filtroAtual)
        val aviso = filtroAtual == Material.PILHAS_BATERIAS || filtroAtual == Material.OLEO

        _estado.value = _estado.value.copy(
            carregando = false,
            semLocal = false,
            pontosFiltrados = filtrados,
            avisoRN04 = aviso,
        )
    }
}
