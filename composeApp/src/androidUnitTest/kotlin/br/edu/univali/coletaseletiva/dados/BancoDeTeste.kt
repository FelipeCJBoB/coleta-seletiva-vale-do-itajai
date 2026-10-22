package br.edu.univali.coletaseletiva.dados

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

/**
 * Ajudante para criar bancos SQLite em memória nos testes.
 * Usado por todos os desenvolvedores nos testes de SQL.
 */
fun bancoEmMemoria(driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)): Banco {
    Banco.Schema.create(driver)
    return Banco(driver)
}
