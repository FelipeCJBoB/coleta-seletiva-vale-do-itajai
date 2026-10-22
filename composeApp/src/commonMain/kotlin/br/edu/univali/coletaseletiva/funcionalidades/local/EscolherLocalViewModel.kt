package br.edu.univali.coletaseletiva.funcionalidades.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EscolherLocalEstado(
    val carregando: Boolean = true,
    val municipios: List<Municipio> = emptyList(),
    val municipioSelecionado: Municipio? = null,
    val setores: List<Setor> = emptyList(),
    val setorSelecionado: Setor? = null,
    val concluido: Boolean = false,
    val semMunicipios: Boolean = false,
)

class EscolherLocalViewModel(private val repositorio: LocalRepositorio) : ViewModel() {
    private val _estado = MutableStateFlow(EscolherLocalEstado())
    val estado: StateFlow<EscolherLocalEstado> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            val municipios = repositorio.listarMunicipios().sortedBy { it.nome.lowercase() }
            _estado.value = EscolherLocalEstado(
                carregando = false,
                municipios = municipios,
                semMunicipios = municipios.isEmpty(),
            )
        }
    }

    fun escolherMunicipio(id: Long) {
        viewModelScope.launch {
            val municipio = _estado.value.municipios.firstOrNull { it.id == id } ?: return@launch
            val setores = repositorio.listarSetores(id).sortedBy { it.nome.lowercase() }
            _estado.update { it.copy(municipioSelecionado = municipio, setores = setores, setorSelecionado = null) }
        }
    }

    fun escolherSetor(id: Long) {
        _estado.update { atual -> atual.copy(setorSelecionado = atual.setores.firstOrNull { it.id == id }) }
    }

    fun confirmar() {
        val setor = _estado.value.setorSelecionado ?: return
        viewModelScope.launch {
            repositorio.escolherSetor(setor.id)
            _estado.update { it.copy(concluido = true) }
        }
    }
}
