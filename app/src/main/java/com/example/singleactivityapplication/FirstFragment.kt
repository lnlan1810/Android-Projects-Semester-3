package com.example.singleactivityapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.singleactivityapplication.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.number_dialog.view.*


class FirstFragment : Fragment(R.layout.fragment_first) {


     private var _binding: FragmentFirstBinding? =null
     private val binding get() = _binding!!
    private var count :  Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        if (savedInstanceState != null)
            count = savedInstanceState.getInt(ARG_COUNTER)


        with(binding) {

            tvCount.text = showCount(count)

            tvCount.text = BlankFragment.showCount(count!!.toInt())

            button1.setOnClickListener {

                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                    )
                    .replace(
                        R.id.fragment,
                        BlankFragment.newInstance(count.toString()),
                        BlankFragment.BLANK_FRAGMENT_TAG
                    )
                    .addToBackStack(BlankFragment.BLANK_FRAGMENT_TAG)
                    .commit()
            }

            button2.setOnClickListener {

                count ++
                tvCount.text = showCount(count)
            }

            button3.setOnClickListener{

                val dialog = EnterNumberDialog(count) {
                    count = it
                    tvCount.text = showCount(count)
                }
                dialog.show(parentFragmentManager,"enter number dialog")

            }


        }





        /*
        with(binding){
            button3.setOnClickListener{
                val view =
                    LayoutInflater.from(requireContext()).inflate(R.layout.number_dialog, null, false)

                val mBuilder = AlertDialog.Builder(requireContext())
                    .setView(view)
                    .setTitle("Enter any number")

                val mAlertDialog = mBuilder.show()

                view.btPlus.setOnClickListener {
                    mAlertDialog.dismiss()

                    val number = view.etNumber.text.toString()
                    number.toInt()

                    if(number.toInt()> 100 || number.toInt() == null  ){
                        val textView = binding.tvCount as TextView
                        textView.text = "Invalid data format"
                    }else{
                        val count = count.toInt() + number.toInt()
                        val textView = binding.tvCount as TextView
                        textView.text = "Counter value: $count"
                    }

                }

                view.btMinus.setOnClickListener {
                    mAlertDialog.dismiss()

                    val number2 = view.etNumber.text.toString()
                    if(number2.toInt()> 100 || number2.toInt() == null  ){
                        val textView = binding.tvCount as TextView
                        textView.text = "Invalid data format"
                    }else{
                        val count = count.toInt() - number2.toInt()
                        val textView = binding.tvCount as TextView
                        textView.text = "Counter value: $count"
                    }
                }

                view.btClose.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }
         */
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
        private const val ARG_COUNTER = "COUNTER"

        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"

        fun newInstance(name : String) = FirstFragment().apply {
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