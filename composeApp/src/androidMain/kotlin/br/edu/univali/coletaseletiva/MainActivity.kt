package br.edu.univali.coletaseletiva

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.edu.univali.coletaseletiva.dados.DriverFactory
import br.edu.univali.coletaseletiva.di.AppContainer
import br.edu.univali.coletaseletiva.lembrete.AgendadorAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pedirPermissaoDeNotificacao()
        val container = AppContainer(
            driverFactory = DriverFactory(applicationContext),
            agendador = AgendadorAndroid(applicationContext),
        )
        setContent { App(container) }
    }

    /** Android 13+ pede permissão para mostrar o lembrete da coleta (RF02). Sem ela o app funciona igual, só não avisa. */
    private fun pedirPermissaoDeNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }
}
