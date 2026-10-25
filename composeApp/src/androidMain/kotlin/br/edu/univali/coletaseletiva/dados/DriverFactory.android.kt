package br.edu.univali.coletaseletiva.dados

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DriverFactory(private val context: Context) {
    actual fun criar(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
        val driver = AndroidSqliteDriver(
            schema = schema,
            context = context,
            name = "coleta-seletiva.db",
            callback = object : AndroidSqliteDriver.Callback(schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.execSQL("PRAGMA foreign_keys=ON;")
                }
            },
        )
        runCatching {
            schema.create(driver)
        }
        return driver
    }
}
