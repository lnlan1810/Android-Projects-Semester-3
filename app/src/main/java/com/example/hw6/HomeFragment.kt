package com.example.hw6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.hw6.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val options = navOptions {
            launchSingleTop = false
            popUpTo(R.id.homeFragment) {
                inclusive = true
            }
            anim {
                enter = android.R.anim.slide_in_left
                exit = android.R.anim.slide_out_right
                popEnter = android.R.anim.slide_out_right
                exit = android.R.anim.slide_in_left
            }
        }

        with(binding) {
            btOne.setOnClickListener {
                val bundle = Bundle()
                findNavController().navigate(R.id.action_homeFragment_to_fistFragment2, bundle)
            }
        }

        with(binding) {
            btTwo.setOnClickListener {
                val bundle = Bundle()
                findNavController().navigate(R.id.action_homeFragment_to_secondFragment2, bundle)
            }
        }

        with(binding) {
            btThree.setOnClickListener {
                val bundle = Bundle()
                findNavController().navigate(R.id.action_homeFragment_to_thirdFragment2, bundle)
            }
        }

        with(binding) {
            btFour.setOnClickListener {
                val bundle = Bundle()
                findNavController().navigate(R.id.action_homeFragment_to_fourFragment2, bundle)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}