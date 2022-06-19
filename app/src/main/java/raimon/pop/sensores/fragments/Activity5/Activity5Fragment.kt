package raimon.pop.sensores.fragments.Activity5

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.FragmentActivity5Binding
import java.util.*


class Activity5Fragment : Fragment(), TextToSpeech.OnInitListener {
    private var binding: FragmentActivity5Binding? = null
    private lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableUI(false)

        tts = TextToSpeech(context, this)

        val speechListener = object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
                binding?.root?.post{
                    enableUI(false)
                }
            }

            override fun onDone(utteranceId: String?) {
                binding?.root?.post {
                    enableUI(true)
                }
            }

            override fun onError(utteranceId: String?) {
                binding?.root?.post {
                    enableUI(true)
                }
            }
        }
        tts.setOnUtteranceProgressListener(speechListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity5Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            speechButton.setOnClickListener {
                speak(binding?.speechTextInput?.text.toString())
            }
            helloButton.setOnClickListener {
                speak(binding?.helloButton?.text.toString())
            }
            goodbyeButton.setOnClickListener {
                speak(binding?.goodbyeButton?.text.toString())
            }
            stopButton.setOnClickListener {
                speak(binding?.stopButton?.text.toString())
            }
            startButton.setOnClickListener {
                speak(binding?.startButton?.text.toString())
            }

        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.getDefault())
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                tts.language = Locale.US
            }else{
               enableUI(true)
            }
        }
    }

    private fun enableUI(enable: Boolean) {
        binding?.apply {
            speechButton.isEnabled = enable
            helloButton.isEnabled = enable
            goodbyeButton.isEnabled = enable
            startButton.isEnabled = enable
            stopButton.isEnabled = enable
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}