package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_activity)

        val customerNameTextView: TextView = findViewById(R.id.customerNameValueTextView)
        val itemNameTextView: TextView = findViewById(R.id.itemNameValueTextView)
        val itemNameImageView: ImageView = findViewById(R.id.itemImageView)
        val itemPriceTextView: TextView = findViewById(R.id.itemPriceValueTextView)
        val itemQuantityTextView: TextView = findViewById(R.id.itemQuantityValueTextView)
        val totalPriceTextView: TextView = findViewById(R.id.totalPriceValueTextView)

        val name = intent.getStringExtra("name")
        val selectedPrice = intent.getIntExtra("selectedPrice", 0)
        val selectedItemImage = intent.getIntExtra("selectedItemImage", 0)
        val quantity = intent.getIntExtra("quantity", 0)

        customerNameTextView.text = name
        itemNameImageView.setImageResource(selectedItemImage)
        itemPriceTextView.text = selectedPrice.toString()
        itemQuantityTextView.text = quantity.toString()

        val itemManager = Item()
        val itemName = itemManager.itemNames[selectedItemImage] ?: ""
        itemNameTextView.text = itemName

        val totalPrice = selectedPrice * quantity
        totalPriceTextView.text = totalPrice.toString()
    }
}
