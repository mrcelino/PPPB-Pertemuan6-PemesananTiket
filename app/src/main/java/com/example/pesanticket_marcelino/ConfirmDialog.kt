package com.example.pesanticket_marcelino

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.pesanticket_marcelino.databinding.ConfirmDialogBinding
import java.text.NumberFormat
import java.util.Locale

class ConfirmDialog : DialogFragment() {
    companion object {
        fun newInstance(name: String, destination: String, price: Int, date: String, time: String) = ConfirmDialog().apply {
            arguments = Bundle().apply {
                putString("name", name)
                putString("destination", destination)
                putInt("price", price)
                putString("date", date)
                putString("time", time)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = ConfirmDialogBinding.inflate(requireActivity().layoutInflater)

        val name = arguments?.getString("name") ?: ""
        val destination = arguments?.getString("destination") ?: ""
        val price = arguments?.getInt("price") ?: 0
        val date = arguments?.getString("date") ?: ""
        val time = arguments?.getString("time") ?: ""

        binding.Harga.text = "Rp ${NumberFormat.getNumberInstance(Locale("in", "ID")).format(price)}"
        binding.btnBatal.setOnClickListener { dismiss() }
        binding.btnBeli.setOnClickListener {
            startActivity(Intent(requireContext(), Confirmation::class.java).apply {
                putExtra("NAME", name)
                putExtra("DESTINATION", destination)
                putExtra("PRICE", price)
                putExtra("DATE", date)
                putExtra("TIME", time)
            })
            dismiss()
        }

        return AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
            .setView(binding.root)
            .create()
    }
}


