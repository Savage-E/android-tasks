package vsu.com

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rectangle = Rectangle("Orange", 3.0, 5.0)
        val circle = Circle("White", 2.0)
        val triangle = Triangle("Yellow", 4.0, 4.0, 6.0)

        val shapes = listOf(circle, rectangle, triangle)

        val totalAreas = calculateSumArea(shapes)

        val resultTextView: TextView = findViewById(R.id.total_area_textView)
        resultTextView.text = "Total area of figures: $totalAreas"
    }
}

