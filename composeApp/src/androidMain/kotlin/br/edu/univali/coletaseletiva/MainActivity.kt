package br.edu.univali.coletaseletiva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.edu.univali.coletaseletiva.dados.DriverFactory
import br.edu.univali.coletaseletiva.di.AppContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = AppContainer(DriverFactory(applicationContext))
        setContent { App(container) }
    }
}
