package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.alarmclock.Utils.MySharedPrefarance
import java.text.SimpleDateFormat
import java.util.*
import android.os.Vibrator




class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        MySharedPrefarance.init(context)
        val list = MySharedPrefarance.alarmList

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val hh = SimpleDateFormat("HH").format(calendar.time).toInt()
        val mm = SimpleDateFormat("mm").format(calendar.time).toInt()
        val hafta = calendar[Calendar.DAY_OF_WEEK]
        val mediaPlayer = MediaPlayer.create(context, R.raw.alarm_music)
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        for (obekt in list) {

            println("$hh:$mm hafta: $hafta ${obekt.hafta[hafta-1]} now")

            if (hh == obekt.soat && mm == obekt.min && obekt.hafta[hafta-1] && obekt.isRun) {

                var handler = Handler(Looper.getMainLooper())
                val runnable = Runnable {
                    while (true){
                        Thread.sleep(500)
                        v.vibrate(500)
                        if (!mediaPlayer.isPlaying){
                            break
                        }
                    }
                }
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.start()
                } else {
                    mediaPlayer.start()
                }
                if (obekt.vibration) {
                    v.vibrate(500)
                    handler.postDelayed(runnable, 100)
                }
            }
        }
        Log.d("run", "mediPlayer")
    }
}