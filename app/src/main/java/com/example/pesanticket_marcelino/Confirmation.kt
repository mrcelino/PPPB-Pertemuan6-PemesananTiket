package com.example.pesanticket_marcelino

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.pesanticket_marcelino.databinding.ConfirmationBinding
import java.text.NumberFormat
import java.util.Locale

class Confirmation : AppCompatActivity() {

    private lateinit var binding: ConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val name = intent.getStringExtra("NAME") ?: "Tidak ada nama"
        val destination = intent.getStringExtra("DESTINATION") ?: "Tidak ada tujuan"
        val price = intent.getIntExtra("PRICE", 0)
        val date = intent.getStringExtra("DATE") ?: "Tidak ada tanggal"
        val time = intent.getStringExtra("TIME") ?: "Tidak ada jam"

        // Format harga ke format yang diinginkan
        val formattedPrice = NumberFormat.getNumberInstance(Locale("in", "ID")).format(price)

        // Tampilkan data di TextView
        binding.Nama.text = name
        binding.Jam.text = time
        binding.Tanggal.text = date
        binding.Tujuan.text = destination
        binding.Harga.text = "Rp $formattedPrice"
    }
}
