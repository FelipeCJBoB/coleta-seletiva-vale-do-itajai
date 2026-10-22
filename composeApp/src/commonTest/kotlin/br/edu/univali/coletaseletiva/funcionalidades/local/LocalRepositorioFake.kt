package br.edu.univali.coletaseletiva.funcionalidades.local

/** Repositório de mentira (só na memória) para testar ViewModels sem banco. */
class LocalRepositorioFake(
    municipiosIniciais: List<Municipio> = emptyList(),
    setoresIniciais: List<Setor> = emptyList(),
) : LocalRepositorio {
    private val municipios = municipiosIniciais.toMutableList()
    private val setores = setoresIniciais.toMutableList()
    private var setorEscolhidoId: Long? = null

    override suspend fun listarMunicipios(): List<Municipio> = municipios.toList()

    override suspend fun listarSetores(municipioId: Long): List<Setor> =
        setores.filter { it.municipioId == municipioId }

    override suspend fun escolherSetor(setorId: Long) {
        setorEscolhidoId = setorId
    }

    override suspend fun setorEscolhido(): SetorEscolhido? {
        val setor = setores.firstOrNull { it.id == setorEscolhidoId } ?: return null
        val municipio = municipios.first { it.id == setor.municipioId }
        return SetorEscolhido(setor.id, municipio.nome, setor.nome)
    }

    override suspend fun cadastrarMunicipio(nome: String, uf: String) {
        municipios.add(Municipio((municipios.maxOfOrNull { it.id } ?: 0L) + 1, nome, uf))
    }

    override suspend fun cadastrarSetor(municipioId: Long, nome: String) {
        setores.add(Setor((setores.maxOfOrNull { it.id } ?: 0L) + 1, municipioId, nome))
    }
}
