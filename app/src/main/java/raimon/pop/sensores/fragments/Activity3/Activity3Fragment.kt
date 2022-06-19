package raimon.pop.sensores.fragments.Activity3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.FragmentActivity3Binding


class Activity3Fragment : Fragment(), SensorEventListener {

    private var binding: FragmentActivity3Binding? = null
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity3Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sensors = getSensor()

        if (sensors != null) {
            StartSensor(sensors)
            binding?.sensorsText!!.text = sensors.name
        }

    }


    private fun getSensor(): Sensor? {

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var sensor: Sensor? = sensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            ?: return null
        return sensor
    }

    private fun StartSensor(sensor: Sensor) {

        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            binding?.let {
                it.sensorsTemperatureText.text = String.format("Temperatura: %.3f", event.values[0])
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}