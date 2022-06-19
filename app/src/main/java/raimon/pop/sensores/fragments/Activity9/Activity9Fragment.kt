package raimon.pop.sensores.fragments.Activity9

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.FragmentActivity9Binding


class Activity9Fragment : Fragment() {
    private var binding: FragmentActivity9Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivity9Binding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnSchool.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:41.5696852,1.9965175,15?q=Instituto Nicolau Copèrnic")
                    `package` = "com.google.android.apps.maps"

                }
                activity?.startActivity(intent)

            }

        }
    }


}