package com.example.alarmclock.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.Models.AlarmObekt
import com.example.alarmclock.R
import kotlinx.android.synthetic.main.item_rv.view.*

class RvAdapter(var list: List<AlarmObekt>, val rvClick: RvClick) : RecyclerView.Adapter<RvAdapter.Vh>(){

    inner class Vh(var itemRv:View):RecyclerView.ViewHolder(itemRv){
        fun onBind(alarmObekt: AlarmObekt, position: Int){
            itemRv.time_0.text = alarmObekt.soat.toString()

            if (alarmObekt.min.toString().length == 2)
            itemRv.time_1.text = alarmObekt.min.toString()
            else itemRv.time_1.text = "0${alarmObekt.min}"

            if (alarmObekt.isRun){
                itemRv.switch_compat.isChecked = true
            }

                if (alarmObekt.hafta[1]){
                    itemRv.hafta_1.setBackgroundResource(R.drawable.shape)
                }
            if (alarmObekt.hafta[2]){
                itemRv.hafta_2.setBackgroundResource(R.drawable.shape)
            }
            if (alarmObekt.hafta[3]){
                itemRv.hafta_3.setBackgroundResource(R.drawable.shape)
            }
            if (alarmObekt.hafta[4]){
                itemRv.hafta_4.setBackgroundResource(R.drawable.shape)
            }
            if (alarmObekt.hafta[5]){
                itemRv.hafta_5.setBackgroundResource(R.drawable.shape)
            }
            if (alarmObekt.hafta[6]){
                itemRv.hafta_6.setBackgroundResource(R.drawable.shape)
            }
            if (alarmObekt.hafta[0]){
                itemRv.hafta_7.setBackgroundResource(R.drawable.shape)
            }

            itemRv.rv_root.setOnLongClickListener{
                println("position: $position")
                rvClick.longClick(alarmObekt, position)
                true
            }
            itemRv.switch_compat.setOnCheckedChangeListener { button, b ->
                alarmObekt.isRun = b
                rvClick.switchOnOff(alarmObekt, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun longClick(alarmObekt: AlarmObekt, position: Int)
        fun switchOnOff(alarmObekt: AlarmObekt, position: Int)
    }
}