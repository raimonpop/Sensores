package raimon.pop.sensores.fragments.Activity4

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
import raimon.pop.sensores.databinding.FragmentActivity4Binding
import raimon.pop.sensores.fragments.Activity3.Activity3Fragment

class Activity4Fragment : Fragment(), SensorEventListener {
    private var binding: FragmentActivity4Binding? = null
    lateinit var fondo: Lienzo
    private var sensorManager: SensorManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity4Binding.inflate(inflater, container, false)
        fondo = Lienzo(this.requireContext())
        binding?.root?.addView(fondo)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity4, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sensors = getSensor()
        StartSensor(sensors)
        binding?.buttonReset?.setOnClickListener {
            fondo.posXCallibrated = -fondo.posx * 2
            fondo.posYCallibrated = -fondo.posy * 2
        }
    }
    class Lienzo(context: Context) : View(context) {


        var posx: Float = 0f
        var posy: Float = 0f
        var posXCallibrated = 0f
        var posYCallibrated = 0f

        override fun onDraw(canvas: Canvas) {
            canvas.drawRGB(10, 10, 10)
            val ancho = width / 20f
            val calibradoAncho = width + posXCallibrated
            val calibradoAlto = height + posYCallibrated
            val pincel1 = Paint()
            pincel1.setARGB(255, 255, 100, 100)

            canvas.drawCircle(
                (calibradoAncho / 2) + posx,
                (calibradoAlto / 2) + posy,
                ancho,
                pincel1
            )
        }
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
            fondo.posx = event.values[0]* -100
            fondo.posy = event.values[1]* 100
            fondo.invalidate()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}