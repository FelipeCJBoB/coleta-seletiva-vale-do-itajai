package br.edu.univali.coletaseletiva.funcionalidades.guia

/** Contrato do guia de separação (PB11). */
interface GuiaRepositorio {
    suspend fun listar(): List<GuiaItem>
    suspend fun buscar(id: Long): GuiaItem?
}

/**
 * Guia em memória: conteúdo estático, sem SQL e sem internet (D-08, RNF03).
 * No app usa DadosGuiaDemo; nos testes recebe itens de exemplo.
 */
class GuiaRepositorioEmMemoria(
    private val itens: List<GuiaItem> = DadosGuiaDemo.itens,
) : GuiaRepositorio {
    override suspend fun listar(): List<GuiaItem> = itens
    override suspend fun buscar(id: Long): GuiaItem? = itens.firstOrNull { it.id == id }
}
