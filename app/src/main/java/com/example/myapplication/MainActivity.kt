package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.widget.EditText
import android.widget.Button
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.view.View
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var countTextView: TextView
    private lateinit var spinner: Spinner
    private lateinit var priceTextView: TextView
    private var totalPrice = 0
    private lateinit var imageView: ImageView
    private var selectedPrice = 0
    private var selectedItemImage = 0
    private val itemManager = Item()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countTextView = findViewById(R.id.count_textview)
        val plusButton: Button = findViewById(R.id.plus_button)
        val minusButton: Button = findViewById(R.id.minus_button)

        plusButton.setOnClickListener {
            count++
            countTextView.text = count.toString()
        }

        minusButton.setOnClickListener {
            if (count > 0) {
                count--
                countTextView.text = count.toString()
            }
        }

        spinner = findViewById(R.id.spinner)
        priceTextView = findViewById(R.id.textViewNumber2)
        imageView = findViewById(R.id.imageView)

        val itemsArray = itemManager.itemPrices.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = itemsArray[position]
                totalPrice = itemManager.itemPrices[selectedItem] ?: 0
                selectedPrice = totalPrice
                priceTextView.text = totalPrice.toString()

                selectedItemImage = itemManager.itemImages[selectedItem] ?: R.drawable.zxc
                imageView.setImageResource(selectedItemImage)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        buttonAdd.setOnClickListener {
            val name = nameEditText.text.toString()
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("selectedPrice", selectedPrice)
            intent.putExtra("selectedItemImage", selectedItemImage)
            startActivity(intent)
        }
        buttonAdd.setOnClickListener {
            val name = nameEditText.text.toString()

            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("selectedPrice", selectedPrice)
            intent.putExtra("selectedItemImage", selectedItemImage)
            intent.putExtra("quantity", count)

            startActivity(intent)
        }

    }
}

