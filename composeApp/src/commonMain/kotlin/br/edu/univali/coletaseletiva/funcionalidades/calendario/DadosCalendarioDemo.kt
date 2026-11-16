package br.edu.univali.coletaseletiva.funcionalidades.calendario

import kotlinx.datetime.DayOfWeek.FRIDAY
import kotlinx.datetime.DayOfWeek.MONDAY
import kotlinx.datetime.DayOfWeek.THURSDAY
import kotlinx.datetime.DayOfWeek.TUESDAY
import kotlinx.datetime.DayOfWeek.WEDNESDAY

/**
 * Dias de coleta FICTÍCIOS, só para demonstrar o app (não são os dias reais da prefeitura).
 * Um conjunto por setor do DadosLocalDemo (ids 1 a 6), sempre de segunda a sexta e sem horário (RN01).
 */
object DadosCalendarioDemo {

    val coletasPorSetor: Map<Long, List<ColetaDoSetor>> = mapOf(
        1L to listOf( // Itajaí · Centro
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(TUESDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(MONDAY, THURSDAY)),
        ),
        2L to listOf( // Itajaí · São Vicente
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(WEDNESDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(TUESDAY, FRIDAY)),
        ),
        3L to listOf( // Itajaí · Fazenda
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(THURSDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(MONDAY, WEDNESDAY)),
        ),
        4L to listOf( // Camboriú · Centro (seletiva na segunda: bom para conferir feriado, como 07/09)
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(MONDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(TUESDAY, FRIDAY)),
        ),
        5L to listOf( // Camboriú · Monte Alegre
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(FRIDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(MONDAY, THURSDAY)),
        ),
        6L to listOf( // Camboriú · Tabuleiro
            ColetaDoSetor(TipoColeta.SELETIVA, setOf(WEDNESDAY)),
            ColetaDoSetor(TipoColeta.COMUM, setOf(MONDAY, FRIDAY)),
        ),
    )
}
