package br.edu.univali.coletaseletiva.funcionalidades.calendario

/** Contrato de onde vêm os dias de coleta de cada setor. Quem implementa: CalendarioRepositorioSql (app) e o Fake (testes). */
interface CalendarioRepositorio {
    suspend fun coletasDoSetor(setorId: Long): List<ColetaDoSetor>
}
