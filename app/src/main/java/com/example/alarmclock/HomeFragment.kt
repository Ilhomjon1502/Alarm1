package com.example.alarmclock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.alarmclock.Adapters.RvAdapter
import com.example.alarmclock.Models.AlarmObekt
import com.example.alarmclock.Utils.MySharedPrefarance
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    lateinit var root:View
    lateinit var rvAdapter: RvAdapter

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        root.add_time.setOnClickListener {
            root.findNavController().navigate(R.id.adderFragment2)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        MySharedPrefarance.init(context)
        val list = MySharedPrefarance.alarmList
        rvAdapter = RvAdapter(list, object : RvAdapter.RvClick{
            override fun longClick(alarmObekt: AlarmObekt, position: Int) {
                MySharedPrefarance.init(context)
                val mL = MySharedPrefarance.alarmList
                mL.removeAt(position)
                MySharedPrefarance.alarmList = mL
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                onResume()
            }
            override fun switchOnOff(alarmObekt: AlarmObekt, position: Int) {
                MySharedPrefarance.init(context)
                val ml = MySharedPrefarance.alarmList
                ml[position] = alarmObekt
                MySharedPrefarance.alarmList = ml
                onResume()
            }
        })
        root.rv.adapter = rvAdapter
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}