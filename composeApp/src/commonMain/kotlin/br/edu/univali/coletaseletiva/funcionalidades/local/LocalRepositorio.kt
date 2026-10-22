package br.edu.univali.coletaseletiva.funcionalidades.local

/**
 * Contrato do repositório de local (RF01). Quem implementa: LocalRepositorioSql (app) e
 * LocalRepositorioFake (testes).
 *
 * RNF05: incluir município ou setor = só cadastrar dados (cadastrarMunicipio / cadastrarSetor),
 * sem mudar o código das telas.
 */
interface LocalRepositorio {
    suspend fun listarMunicipios(): List<Municipio>
    suspend fun listarSetores(municipioId: Long): List<Setor>

    /** Guarda a escolha só no aparelho (RN07). */
    suspend fun escolherSetor(setorId: Long)

    /** Devolve a escolha salva ou nulo se o usuário ainda não escolheu. */
    suspend fun setorEscolhido(): SetorEscolhido?

    suspend fun cadastrarMunicipio(nome: String, uf: String)
    suspend fun cadastrarSetor(municipioId: Long, nome: String)
}
