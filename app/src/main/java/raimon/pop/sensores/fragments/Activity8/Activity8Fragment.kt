package raimon.pop.sensores.fragments.Activity8

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.FragmentActivity8Binding


class Activity8Fragment : Fragment() {
    private var binding: FragmentActivity8Binding? = null
    private var uri: Uri? = null

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                uri = it.data?.data
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity8Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btSend.setOnClickListener {
                sendEmail()
            }
            btAttachment.setOnClickListener {
                openFolder()
            }

        }
    }

    private fun openFolder() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private fun sendEmail() {
        try {
            binding?.apply {
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.type = "plain/text"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, etTo.text.toString())
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, etSubject.text.toString())
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
                emailIntent.putExtra(Intent.EXTRA_TEXT, etMessage.text.toString())
                activity?.startActivity(Intent.createChooser(emailIntent, "Sending email..."))
            }
        } catch (t: Throwable) {
            Toast.makeText(this.context, "Request failed try again: $t", Toast.LENGTH_LONG).show()
            t.message?.let { Log.e("Sensores", it) }
        }
    }

}