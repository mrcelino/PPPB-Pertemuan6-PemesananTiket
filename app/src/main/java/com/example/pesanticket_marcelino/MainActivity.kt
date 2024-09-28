package com.example.pesanticket_marcelino

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pesanticket_marcelino.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        // Setup spinner
        val destinations = listOf("Jakarta", "Semarang", "Solo", "Yogyakarta")
        binding.spinnerDestination.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, destinations
        )

        // Setup date picker
        binding.btnSelectDate.setOnClickListener { openDatePicker() }

        // Setup time picker
        binding.btnSelectTime.setOnClickListener { openTimePicker() }

        // Setup booking button
        binding.book.setOnClickListener { processBooking() }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, R.style.CustomDatePickerStyle, { _, year, month, day ->
            selectedDate = "$day/${month + 1}/$year"
            binding.btnSelectDate.text = selectedDate
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun openTimePicker() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(this, R.style.CustomTimePickerStyle, { _, hour, minute ->
            selectedTime = String.format("%02d:%02d", hour, minute)
            binding.btnSelectTime.text = selectedTime
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }

    private fun processBooking() {
        val name = binding.edtPemesan.text.toString().trim()
        val destination = binding.spinnerDestination.selectedItem.toString()
        val price = when (destination) {
            "Jakarta" -> 100000
            "Semarang" -> 75000
            "Solo" -> 150000
            "Yogyakarta" -> 125000
            else -> 0
        }

        if (name.isEmpty() || selectedDate.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "Lengkapi semua data pemesanan", Toast.LENGTH_SHORT).show()
        } else {
            ConfirmDialog.newInstance(name, destination, price, selectedDate, selectedTime)
                .show(supportFragmentManager, "ConfirmDialog")
        }
    }
}

