package com.example.singleactivityapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.singleactivityapplication.databinding.FragmentBlankBinding
import com.example.singleactivityapplication.databinding.FragmentFirstBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {


    private var _binding: FragmentBlankBinding? =null
    private val binding get() = _binding!!

    private var count :  Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBlankBinding.bind(view)

        val count = arguments?.getString(ARG_COUNTER)


        with(binding) {


            btBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                    )
                    .replace(
                        R.id.fragment,
                        FirstFragment.newInstance(count.toString()),
                        FirstFragment.FIRST_FRAGMENT_TAG
                    )
                    .addToBackStack(FirstFragment.FIRST_FRAGMENT_TAG)
                    .commit()
            }


            tv.text = FirstFragment.showCount(count!!.toInt())
            when (count!!.toInt()) {
                in 0 .. 30 -> blankFragment.setBackgroundColor(Color.parseColor("#F1729D"));
                in 31 .. 70 -> blankFragment.setBackgroundColor(Color.parseColor("#FF03DAC5"));
                in 71 .. 100 -> blankFragment.setBackgroundColor(Color.parseColor("#FF6200EE"));
                in 101 .. Int.MAX_VALUE -> blankFragment.setBackgroundColor(Color.parseColor("#1DF004"));
            }

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_COUNTER,count)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val ARG_COUNTER = "ARG_COUNTER"

        const val BLANK_FRAGMENT_TAG = "BLANK_FRAGMENT_TAG"

        fun newInstance(name: String) = BlankFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_COUNTER, name)
            }
        }
        fun showCount(count: Int): String {
            return if (count > 100 ) {
                "Invalid data format"
            } else
                "Counter value: $count"

        }


    }
}