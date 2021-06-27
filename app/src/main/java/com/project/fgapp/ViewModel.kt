package com.project.fgapp


import android.widget.ImageView
import android.widget.SeekBar
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModel  : ViewModel() {
    private var model = Model()
    private var flag = 0
    @field:Bindable
    val ipContent = MutableLiveData<String>()

    @field:Bindable
    val portContent = MutableLiveData<String>()

    @field:Bindable
    val rudder = MutableLiveData<String>()

    @field:Bindable
    val throttle = MutableLiveData<String>()


    fun onDisplayContentClick() {
        //print("after click ${ipContent.value} and ${portContent.value}\n")
        var ip = ipContent.value
        var port = portContent.value
        model.setStart(ip, port)
        flag = 1
    }
    fun onRudderBarChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        var theProgress=progress.toFloat()/100
        rudder.value=theProgress.toString()
        model.setFlightVar("set /controls/flight/rudder ", theProgress)
    }
    fun onthrottleBarChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        var theProgress=progress.toFloat()/100
        throttle.value=theProgress.toString()
        model.setFlightVar("set /controls/engines/current-engine/throttle ", theProgress)
    }
    fun VM_Elevator(e : Float) {
        model.setFlightVar("set /controls/flight/elevator ", e)
    }
    fun VM_Aileron(a : Float) {
        model.setFlightVar("set /controls/flight/aileron ", a)
    }



}