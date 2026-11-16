package br.edu.univali.coletaseletiva.funcionalidades.calendario

/** Repositório de mentira para os testes: um mapa setorId -> coletas cadastradas. */
class CalendarioRepositorioFake(
    private val porSetor: Map<Long, List<ColetaDoSetor>> = emptyMap(),
) : CalendarioRepositorio {

    override suspend fun coletasDoSetor(setorId: Long): List<ColetaDoSetor> = porSetor[setorId].orEmpty()
}
