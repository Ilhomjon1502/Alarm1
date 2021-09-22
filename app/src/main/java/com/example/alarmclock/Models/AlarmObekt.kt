package com.example.alarmclock.Models

class AlarmObekt {
    var name:String? = null
    var soat:Int? = null
    var min:Int? = null
    var isRun:Boolean = true
    var vibration = false
    var hafta = arrayListOf<Boolean>(false, false, false, false, false, false, false)



    constructor()
    constructor(
        name: String?,
        soat: Int?,
        min: Int?,
        isRun: Boolean,
        vibration: Boolean,
        hafta: ArrayList<Boolean>
    ) {
        this.name = name
        this.soat = soat
        this.min = min
        this.isRun = isRun
        this.vibration = vibration
        this.hafta = hafta
    }


    override fun toString(): String {
        return "AlarmObekt(name=$name, soat=$soat, min=$min)"
    }
}