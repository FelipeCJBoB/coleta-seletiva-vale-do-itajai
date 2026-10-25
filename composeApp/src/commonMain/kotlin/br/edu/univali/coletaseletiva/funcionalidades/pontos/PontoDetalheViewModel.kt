package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado da tela de detalhes do ponto de descarte.
 */
data class PontoDetalheEstado(
    val carregando: Boolean = true,
    val ponto: PontoDeDescarte? = null,
    val naoEncontrado: Boolean = false,
    val urlDaRota: String = "",
)

/**
 * ViewModel que carrega os detalhes de um ponto de descarte pelo ID e calcula a URL da rota.
 */
class PontoDetalheViewModel(
    private val pontoId: Long,
    private val pontosRepo: PontosRepositorio,
) : ViewModel() {

    private val _estado = MutableStateFlow(PontoDetalheEstado())
    val estado: StateFlow<PontoDetalheEstado> = _estado.asStateFlow()

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            val pontoEncontrado = pontosRepo.ponto(pontoId)
            if (pontoEncontrado == null) {
                _estado.value = PontoDetalheEstado(
                    carregando = false,
                    naoEncontrado = true,
                )
            } else {
                _estado.value = PontoDetalheEstado(
                    carregando = false,
                    ponto = pontoEncontrado,
                    naoEncontrado = false,
                    urlDaRota = urlDaRota(pontoEncontrado.latitude, pontoEncontrado.longitude),
                )
            }
        }
    }
}
