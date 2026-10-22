package br.edu.univali.coletaseletiva.funcionalidades.local

import br.edu.univali.coletaseletiva.dados.Banco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Repositório de local com SQLite (SQLDelight). As consultas estão em dados/local.sq. */
class LocalRepositorioSql(private val banco: Banco) : LocalRepositorio {

    override suspend fun listarMunicipios(): List<Municipio> = withContext(Dispatchers.Default) {
        banco.localQueries.listarMunicipios { id, nome, uf -> Municipio(id, nome, uf) }.executeAsList()
    }

    override suspend fun listarSetores(municipioId: Long): List<Setor> = withContext(Dispatchers.Default) {
        banco.localQueries.listarSetoresDoMunicipio(municipioId) { id, municipio, nome -> Setor(id, municipio, nome) }
            .executeAsList()
    }

    override suspend fun escolherSetor(setorId: Long) = withContext(Dispatchers.Default) {
        banco.localQueries.salvarEscolha(setorId)
        Unit
    }

    override suspend fun setorEscolhido(): SetorEscolhido? = withContext(Dispatchers.Default) {
        banco.localQueries.buscarSetorEscolhido { setorId, municipio, setor -> SetorEscolhido(setorId, municipio, setor) }
            .executeAsOneOrNull()
    }

    override suspend fun cadastrarMunicipio(nome: String, uf: String) = withContext(Dispatchers.Default) {
        banco.localQueries.inserirMunicipio(nome, uf)
        Unit
    }

    override suspend fun cadastrarSetor(municipioId: Long, nome: String) = withContext(Dispatchers.Default) {
        banco.localQueries.inserirSetor(municipioId, nome)
        Unit
    }

    /** Insere os dados de demonstração só se ainda não houver nenhum município. */
    suspend fun semearSeVazio() = withContext(Dispatchers.Default) {
        if (banco.localQueries.contarMunicipios().executeAsOne() == 0L) {
            DadosLocalDemo.municipios.forEach { banco.localQueries.inserirMunicipioComId(it.id, it.nome, it.uf) }
            DadosLocalDemo.setores.forEach { banco.localQueries.inserirSetorComId(it.id, it.municipioId, it.nome) }
        }
    }
}
