package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Estado da tela "Registrar descarte": só os materiais que pontuam são oferecidos (roupas ficam de fora, D-07). */
data class RegistrarEstado(
    val materiaisDisponiveis: List<Material> = Material.entries.filter { pontosPorMaterial(it) != null },
    val mensagem: String? = null,
    val registrando: Boolean = false,
)

class RegistrarDescarteViewModel(private val registrarDescarte: RegistrarDescarte) : ViewModel() {

    private val _estado = MutableStateFlow(RegistrarEstado())
    val estado: StateFlow<RegistrarEstado> = _estado.asStateFlow()

    fun registrar(material: Material) {
        if (_estado.value.registrando) return
        _estado.value = _estado.value.copy(registrando = true)
        viewModelScope.launch {
            val resultado = registrarDescarte(material)
            _estado.value = _estado.value.copy(registrando = false, mensagem = resultado.paraMensagem())
        }
    }
}

private fun ResultadoRegistro.paraMensagem(): String = when (this) {
    is ResultadoRegistro.Sucesso -> "Você ganhou $pontos pontos!"
    ResultadoRegistro.LimiteAtingido -> "Limite de $LIMITE_DIARIO descartes por hoje atingido"
    ResultadoRegistro.MaterialNaoPontua -> "Esse material não gera pontos"
}
