package br.edu.univali.coletaseletiva.dados

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlin.test.Test
import kotlin.test.assertEquals

class DescarteTabelaTest {

    @Test
    fun tabela_descarte_comeca_vazia() {
        val banco = bancoEmMemoria()
        val total = banco.descarteQueries.contarDescartes().executeAsOne()
        assertEquals(0L, total)
    }

    @Test
    fun bancos_em_memoria_sao_independentes() {
        val driverA = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        val bancoA = bancoEmMemoria(driverA)
        val bancoB = bancoEmMemoria()

        driverA.execute(
            identifier = null,
            sql = "INSERT INTO descarte (material, pontos, data) VALUES ('PAPEL', 5, '2026-10-21')",
            parameters = 0,
        )

        assertEquals(1L, bancoA.descarteQueries.contarDescartes().executeAsOne())
        assertEquals(0L, bancoB.descarteQueries.contarDescartes().executeAsOne())
    }
}
