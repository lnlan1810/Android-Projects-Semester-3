package com.example.hw5.second

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.hw5.databinding.DialogBinding

class SingerDialog  : DialogFragment() {

    private lateinit var binding: DialogBinding

    var positiveCallback: ((List<String>) -> Unit)? = null
    //var positiveCallback: ((String) -> Unit)? = null


    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog = AlertDialog.Builder(requireContext())
        .setView(DialogBinding.inflate(layoutInflater).let {
            binding = it
            it.root
        })
        .setPositiveButton("OK") { _, _ ->
            positiveCallback?.invoke(  arrayListOf("${binding.etTitle.text}",
                "${binding.etDesc.text}",
                "${binding.etPos.text}"))
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        .create()

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positive: (List<String?>) -> Unit
            //positive: (String) -> Unit
        ) {
            SingerDialog().apply {
                positiveCallback = positive
                show(fragmentManager, SingerDialog::class.java.name)
            }
        }
    }
}
