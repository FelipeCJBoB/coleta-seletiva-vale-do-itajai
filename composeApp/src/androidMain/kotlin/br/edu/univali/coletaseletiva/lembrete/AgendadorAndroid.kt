package br.edu.univali.coletaseletiva.lembrete

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import br.edu.univali.coletaseletiva.funcionalidades.lembrete.AgendadorDeLembrete
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

private const val CANAL_ID = "lembrete_coleta"
private const val PEDIDO_ALARME = 4201
private const val ID_NOTIFICACAO = 4201

/**
 * Notificação local de verdade no Android (RF02): AlarmManager agenda e o LembreteReceiver mostra o aviso.
 * Usa alarme não exato (setAndAllowWhileIdle): não precisa da permissão de alarme exato; o aviso pode atrasar
 * alguns minutos. O alarme não sobrevive a reiniciar o aparelho (limitação assumida: é só ligar de novo).
 */
class AgendadorAndroid(context: Context) : AgendadorDeLembrete {

    private val contexto = context.applicationContext
    private val alarmes = contexto.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun agendar(quando: LocalDateTime) {
        val momento = quando.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        alarmes.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, momento, pedido())
    }

    override fun cancelar() {
        alarmes.cancel(pedido())
    }

    private fun pedido(): PendingIntent = PendingIntent.getBroadcast(
        contexto,
        PEDIDO_ALARME,
        Intent(contexto, LembreteReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
}

/** Recebe o alarme e mostra a notificação. No Android 13+ só aparece se o morador permitiu notificações. */
class LembreteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val gerente = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        gerente.createNotificationChannel(
            NotificationChannel(CANAL_ID, "Lembrete da coleta", NotificationManager.IMPORTANCE_DEFAULT),
        )
        val abrirApp = PendingIntent.getActivity(
            context,
            0,
            context.packageManager.getLaunchIntentForPackage(context.packageName),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val notificacao = Notification.Builder(context, CANAL_ID)
            .setSmallIcon(context.applicationInfo.icon)
            .setContentTitle("Coleta amanhã")
            .setContentText("Amanhã tem coleta no seu setor. Separe o lixo e confira o calendário.")
            .setContentIntent(abrirApp)
            .setAutoCancel(true)
            .build()
        runCatching { gerente.notify(ID_NOTIFICACAO, notificacao) } // sem permissão de notificação: ignora
    }
}
