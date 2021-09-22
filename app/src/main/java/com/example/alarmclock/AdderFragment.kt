package com.example.alarmclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.alarmclock.Models.AlarmObekt
import com.example.alarmclock.Utils.MySharedPrefarance
import kotlinx.android.synthetic.main.fragment_adder.*
import kotlinx.android.synthetic.main.fragment_adder.view.*
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AdderFragment : Fragment() {

    lateinit var root: View
    var haftaArray = arrayListOf<Boolean>(false, false, false, false, false, false, false)

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_adder, container, false)

        root.num_picker1.minValue = 0
        root.num_picker1.maxValue = 23

        root.num_picker2.minValue = 0
        root.num_picker2.maxValue = 59

        val soat = SimpleDateFormat("HH").format(Date())
        val min = SimpleDateFormat("mm").format(Date())

        root.num_picker1.value = soat.toInt()
        root.num_picker2.value = min.toInt()

        root.cacel_btn.setOnClickListener {

            requireActivity().onBackPressed()

        }

        root.haf_1.setOnClickListener {
            if (!haftaArray[1]){
                root.haf_1.setBackgroundResource(R.drawable.shape)
                haftaArray[1] = true
            }else{
                root.haf_1.setBackgroundResource(R.drawable.shape_2)
                haftaArray[1] = false
            }
        }

        root.haf_2.setOnClickListener {
            if (!haftaArray[2]){
                root.haf_2.setBackgroundResource(R.drawable.shape)
                haftaArray[2] = true
            }else{
                root.haf_2.setBackgroundResource(R.drawable.shape_2)
                haftaArray[2] = false
            }
        }
        root.haf_3.setOnClickListener {
            if (!haftaArray[3]){
                root.haf_3.setBackgroundResource(R.drawable.shape)
                haftaArray[3] = true
            }else{
                root.haf_3.setBackgroundResource(R.drawable.shape_2)
                haftaArray[3] = false
            }
        }
        root.haf_4.setOnClickListener {
            if (!haftaArray[4]){
                root.haf_4.setBackgroundResource(R.drawable.shape)
                haftaArray[4] = true
            }else{
                root.haf_4.setBackgroundResource(R.drawable.shape_2)
                haftaArray[4] = false
            }
        }
        root.haf_5.setOnClickListener {
            if (!haftaArray[5]){
                root.haf_5.setBackgroundResource(R.drawable.shape)
                haftaArray[5] = true
            }else{
                root.haf_5.setBackgroundResource(R.drawable.shape_2)
                haftaArray[5] = false
            }
        }
        root.haf_6.setOnClickListener {
            if (!haftaArray[6]){
                root.haf_6.setBackgroundResource(R.drawable.shape)
                haftaArray[6] = true
            }else{
                root.haf_6.setBackgroundResource(R.drawable.shape_2)
                haftaArray[6] = false
            }
        }
        root.haf_7.setOnClickListener {
            if (!haftaArray[0]){
                root.haf_7.setBackgroundResource(R.drawable.shape)
                haftaArray[0] = true
            }else{
                root.haf_7.setBackgroundResource(R.drawable.shape_2)
                haftaArray[0] = false
            }
        }

        val bottomsheetFragment = BottomSheet_Fragment()

        root.music_add.setOnClickListener {
            bottomsheetFragment.show(childFragmentManager, "BottomSheet Dialog")
        }

        root.tebranish_btn.setOnClickListener {

            if (switch_compat2.isChecked) {
                switch_compat2.isChecked = isDetached
            } else {
                switch_compat2.isChecked = isAdded
            }
        }

        var time = SystemClock.elapsedRealtime() + 1000
        val intent = Intent(context, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            time,
            10000,
            pendingIntent
        )

        MySharedPrefarance.init(context)

        root.save_btn.setOnClickListener {
            
            if (haftaArray.contains(true)) {
                val soat = root.num_picker1.value
                val min = root.num_picker2.value

                println("$soat:$min")

                val list = MySharedPrefarance.alarmList
                list.add(AlarmObekt("", soat, min, true, root.switch_compat2.isChecked, haftaArray))
                MySharedPrefarance.alarmList = list
                Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()

//            val c = Calendar.getInstance()
//            c.set(Calendar.HOUR_OF_DAY, soat)
//            c.set(Calendar.MINUTE, min)
//            c.set(Calendar.SECOND, 0)
//            alarmManager.set(AlarmManager.ELAPSED_REALTIME, c.timeInMillis, pendingIntent)
                findNavController().popBackStack()
            }else{
                Toast.makeText(context, "Avval hafta kunini belgilang...", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}