package com.project.fgapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.project.fgapp.databinding.ActivityMainBinding
import com.project.joystick.JoyStick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this)
            .get(ViewModel::class.java)
        //val joystick = JoyStickView(this, 300F, 300F, 300F, 300F)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel = viewModel
        }
        val joystick : JoyStick = findViewById(R.id.joyStick)
        val image:ImageView =findViewById(R.id.imageView5)
        val switch:Switch=findViewById(R.id.switch1)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                image.setImageResource(R.drawable.night)
                joystick.nightColor()
            } else {
                // The switch is disabled
                image.setImageResource(R.drawable.cock)
                joystick.dayColor()
            }
        }

        joystick.OnChangeJ = { e: Float, a: Float ->
            viewModel.VM_Elevator(e)
            viewModel.VM_Aileron(a)
        }
    }
}

