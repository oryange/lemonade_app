package com.example.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewElements()
    }

    private var lemonSize: Int = pick(10)
    private var squeezeCount: Int = 0
    var lemonadeState = "SQUEEZE"


    /**
     * Set up the view elements according to the state
     */
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.textview)
        val imageView: ImageView = findViewById(R.id.imageView)
        clickLemonImage(imageView, textAction)
    }

    private fun clickLemonImage(imageView: ImageView, textAction: TextView) {
        imageView.setOnClickListener {
            when (lemonadeState) {
                "SELECT" -> {
                    textAction.text = this.getString(R.string.click_to_select)
                    imageView.setImageResource(R.drawable.lemon_tree)
                    lemonSize = pick(10)
                    squeezeCount = 0
                    lemonadeState = "SQUEEZE"
                }
                "SQUEEZE" -> {
                    textAction.text = this.getString(R.string.click_to_juice)
                    imageView.setImageResource(R.drawable.lemon_squeeze)
                    showSnackbar(imageView)
                    if (lemonSize == 0) lemonadeState = "DRINK"
                    squeezeCount++
                    lemonSize--
                }
                "DRINK" -> {
                    textAction.text = this.getString(R.string.click_to_drink)
                    imageView.setImageResource(R.drawable.lemon_drink)
                    lemonadeState = "RESTART"
                    lemonSize = -1
                }
                "RESTART" -> {
                    textAction.text = this.getString(R.string.click_to_restart)
                    imageView.setImageResource(R.drawable.lemon_restart)
                    lemonadeState = "SELECT"
                }
            }
        }
    }

    private fun pick(number: Int) = (2..number).random()

    private fun showSnackbar(imageView: ImageView) {
        imageView.setOnLongClickListener {
            Snackbar.make(it, "Squeeze count: $squeezeCount, keep squeezing!", Snackbar.LENGTH_LONG)
                .setAction("ACTION", null).show()
            return@setOnLongClickListener true
        }
    }
}


