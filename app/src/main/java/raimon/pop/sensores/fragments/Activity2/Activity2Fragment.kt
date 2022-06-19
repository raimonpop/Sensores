package raimon.pop.sensores.fragments.Activity2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raimon.pop.sensores.databinding.FragmentActivity2Binding


class Activity2Fragment : Fragment(), SensorEventListener {

    private var binding: FragmentActivity2Binding? = null
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity2Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sensors = getSensor()
        StartSensor(sensors)
        binding?.sensorsText!!.text = sensors.name
    }

    private fun getSensor(): Sensor {
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }


    private fun StartSensor(sensor: Sensor) {
        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            binding?.let {
                it.sensorsXText.text = String.format("%.3f", event.values[0])
                it.sensorsYText.text = String.format("%.3f", event.values[1])
                it.sensorsZText.text = String.format("%.3f", event.values[2])

            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }

}