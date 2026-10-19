package br.edu.univali.coletaseletiva.dados

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun criar(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver =
        NativeSqliteDriver(schema, "coleta-seletiva.db")
}
