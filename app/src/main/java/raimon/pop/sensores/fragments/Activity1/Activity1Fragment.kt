package raimon.pop.sensores.fragments.Activity1

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.RingtoneManager.TYPE_ALL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.FragmentActivity1Binding
import java.lang.StringBuilder
import android.hardware.Sensor.TYPE_ALL as TYPE_ALL1


class Activity1Fragment : Fragment() {
    private var binding: FragmentActivity1Binding? = null
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity1Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sensors = getSensor()
        val sensorText = StringBuilder()
        for (sensor in sensors){
            sensorText.append(sensor.name).append(
                System.getProperty("line.separator")
            )
        }

        binding?.sensorsText!!.text = sensorText
    }

    private fun getSensor(): List<Sensor> {
        sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager
        return sensorManager!!.getSensorList(Sensor.TYPE_ALL)
    }

}