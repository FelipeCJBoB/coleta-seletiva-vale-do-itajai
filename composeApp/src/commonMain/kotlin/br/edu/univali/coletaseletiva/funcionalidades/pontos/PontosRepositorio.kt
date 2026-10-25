package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Contrato para o repositório de pontos de descarte.
 */
interface PontosRepositorio {
    suspend fun pontosDoMunicipio(municipioId: Long): List<PontoDeDescarte>
    suspend fun ponto(id: Long): PontoDeDescarte?
    suspend fun cadastrar(ponto: PontoDeDescarte)
}
