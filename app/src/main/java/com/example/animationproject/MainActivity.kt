package com.example.animationproject

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var animateButton: Button
    private lateinit var stars1View: ImageView
    private lateinit var stars2View: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stars1View = findViewById(R.id.stars_1)
        stars2View = findViewById(R.id.stars_2)
        animateButton = findViewById(R.id.animate_button)

        animateButton.setOnClickListener {
            startAnimation(stars1View)
            startAnimation(stars2View)
        }
    }

    private fun startAnimation(view: View) {
        ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0.1f).apply {
            duration = 1500
            startDelay = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }
}