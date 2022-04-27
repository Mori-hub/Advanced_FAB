package com.src.advancedfab

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.src.tools.advfeb.Curve
import com.src.tools.advfeb.Rectangle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call Rectangle

        val rectangle = Rectangle(this, R.id.mainParent)

        rectangle.setItems(
            //Icons from Res
            arrayListOf(
                android.R.drawable.ic_media_play, android.R.drawable.ic_input_add
                , android.R.drawable.btn_star_big_on, android.R.drawable.ic_delete
            ),
            // Colors of row
            arrayListOf(
                Color.parseColor("black"),
                Color.parseColor("#8A351A"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#9C27B0")
            ),
            // Text of each row
            arrayListOf("One", " two", "see it", "fun"),
            // Text Color
            Color.WHITE,
            // OnClick must be in {}
            arrayListOf(
                null,
                {
                    Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                null,
                null
            )
        )

        // Call Curve - ConstraintLayout (mainParent)
        val curve = Curve(this, R.id.mainParent,findViewById(R.id.floatingActionButton))

        curve.setItems(
            //Icons from Res
            arrayListOf(
                android.R.drawable.ic_media_play, android.R.drawable.ic_input_add
                , android.R.drawable.btn_star_big_on, android.R.drawable.ic_delete
            ),
            // Colors of background icons
            arrayListOf(Color.parseColor("black"),
                Color.parseColor("#8A351A"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#9C27B0")),
            // OnClick must be in {}
            arrayListOf(null,
                { Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                null,
                null))


    }
}