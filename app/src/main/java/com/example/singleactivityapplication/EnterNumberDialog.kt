package com.example.singleactivityapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_dialog.*
import com.example.singleactivityapplication.databinding.FragmentDialogBinding

class EnterNumberDialog  (
    private val counterValue: Int,
    val saveCounter: (Int) -> Unit
) : DialogFragment(){

    private var _binding: FragmentDialogBinding? =null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_dialog, null, false)
        val editText = view.findViewById<TextInputEditText>(R.id.etEnterNumber)
        val layout = view.findViewById<TextInputLayout>(R.id.layout)
        layout.isErrorEnabled = true



            editText.doOnTextChanged { text, start, before, count ->
                if(text!!.toString().toInt() >100){
                    editText.error = "Invalid data format"
                }
             }

        layout.setEndIconOnClickListener{
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()

        }


        return AlertDialog.Builder(requireContext())
            .setTitle("Enter any number")
            .setView(view)
            .setPositiveButton("PlUS") { dialog, _ ->

                if (editText.text.toString().toInt() in 0..100) {
                    saveCounter(counterValue + editText.text.toString().toInt())
                }
            }

            .setNeutralButton("MINUS") { dialog, _ ->
                if (editText.text.toString().toInt() in 0..100) {
                    saveCounter(counterValue - editText.text.toString().toInt())
                }

            }
            .setNegativeButton("CLOSE") { dialog, _ ->
                dialog.dismiss()
            }

            .create()

    }





}