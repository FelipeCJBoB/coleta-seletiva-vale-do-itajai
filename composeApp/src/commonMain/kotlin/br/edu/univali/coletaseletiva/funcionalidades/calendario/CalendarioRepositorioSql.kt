package br.edu.univali.coletaseletiva.funcionalidades.calendario

import br.edu.univali.coletaseletiva.dados.Banco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber

/** Repositório do calendário com SQLite (SQLDelight). As consultas estão em dados/calendario.sq. */
class CalendarioRepositorioSql(private val banco: Banco) : CalendarioRepositorio {

    override suspend fun coletasDoSetor(setorId: Long): List<ColetaDoSetor> = withContext(Dispatchers.Default) {
        banco.calendarioQueries.listarColetasDoSetor(setorId).executeAsList()
            .groupBy { TipoColeta.valueOf(it.tipo) }
            .map { (tipo, linhas) ->
                // dia_semana: 1 (segunda) a 7 (domingo), o mesmo número ISO que o DayOfWeek usa.
                ColetaDoSetor(tipo, linhas.map { DayOfWeek(it.dia_semana.toInt()) }.toSet())
            }
    }

    /** Insere os dados de demonstração só se a tabela ainda estiver vazia. */
    suspend fun semearSeVazio() = withContext(Dispatchers.Default) {
        if (banco.calendarioQueries.contarColetas().executeAsOne() == 0L) {
            DadosCalendarioDemo.coletasPorSetor.forEach { (setorId, coletas) ->
                coletas.forEach { coleta ->
                    coleta.diasDaSemana.forEach { dia ->
                        banco.calendarioQueries.inserirColeta(setorId, coleta.tipo.name, dia.isoDayNumber.toLong())
                    }
                }
            }
        }
    }
}
