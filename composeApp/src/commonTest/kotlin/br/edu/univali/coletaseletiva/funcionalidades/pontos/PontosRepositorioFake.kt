package br.edu.univali.coletaseletiva.funcionalidades.pontos

/**
 * Repositório fake de pontos em memória para testes.
 */
class PontosRepositorioFake(
    pontosIniciais: List<PontoDeDescarte> = emptyList(),
) : PontosRepositorio {

    private val pontos = pontosIniciais.toMutableList()

    override suspend fun pontosDoMunicipio(municipioId: Long): List<PontoDeDescarte> {
        return pontos.filter { it.municipioId == municipioId }
    }

    override suspend fun ponto(id: Long): PontoDeDescarte? {
        return pontos.find { it.id == id }
    }

    override suspend fun cadastrar(ponto: PontoDeDescarte) {
        pontos.removeAll { it.id == ponto.id }
        pontos.add(ponto)
    }
}
