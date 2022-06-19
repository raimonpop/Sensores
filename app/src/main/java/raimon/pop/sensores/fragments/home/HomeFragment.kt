package raimon.pop.sensores.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import raimon.pop.sensores.R
import raimon.pop.sensores.databinding.ActivityMainBinding
import raimon.pop.sensores.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.let {
          return it.root
        }
        navController = findNavController()

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {binding->
            binding.button.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity1()
                it.findNavController().navigate(action)
            }
            binding.button2.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity2()
                it.findNavController().navigate(action)
            }
            binding.button3.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity3()
                it.findNavController().navigate(action)
            }
            binding.button4.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity4()
                it.findNavController().navigate(action)
            }
            binding.button5.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity5()
                it.findNavController().navigate(action)
            }
            binding.button6.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity6()
                it.findNavController().navigate(action)
            }
            binding.button7.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity7()
                it.findNavController().navigate(action)
            }
            binding.button8.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity8()
                it.findNavController().navigate(action)
            }
            binding.button9.setOnClickListener {
                val action = HomeFragmentDirections.actionActivity9()
                it.findNavController().navigate(action)
            }
        }
    }


}