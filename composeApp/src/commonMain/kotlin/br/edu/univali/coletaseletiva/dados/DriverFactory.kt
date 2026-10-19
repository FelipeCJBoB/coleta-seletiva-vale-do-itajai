package br.edu.univali.coletaseletiva.dados

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

/** Cria o driver do SQLite de cada plataforma. Infraestrutura: não mexer sem avisar o Integrante 1. */
expect class DriverFactory {
    fun criar(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver
}
