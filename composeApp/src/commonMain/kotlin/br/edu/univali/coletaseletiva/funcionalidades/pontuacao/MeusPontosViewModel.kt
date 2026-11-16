package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Saldo e histórico da tela "Meus pontos" (RF10). O histórico vem do mais recente para o mais antigo. */
data class MeusPontosEstado(
    val carregando: Boolean = true,
    val saldo: Int = 0,
    val historico: List<Descarte> = emptyList(),
)

class MeusPontosViewModel(private val repo: DescarteRepositorio) : ViewModel() {

    private val _estado = MutableStateFlow(MeusPontosEstado())
    val estado: StateFlow<MeusPontosEstado> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            _estado.value = MeusPontosEstado(
                carregando = false,
                saldo = repo.somarPontos(),
                historico = repo.listarMaisRecentesPrimeiro(),
            )
        }
    }
}
