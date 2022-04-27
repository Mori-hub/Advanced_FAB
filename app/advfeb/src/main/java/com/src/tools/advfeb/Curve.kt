package com.src.tools.advfeb

import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams

class Curve(activity: Activity, parentLayout: Int, targetButton: View) {
    private var view: View
    private var icons = ArrayList<Int>()
    private var colors = ArrayList<Int>()
    private var rollCard = ArrayList<CardView>()
    private var imageBox = ArrayList<ImageView>()
    private var mainRoll: ConstraintLayout
    private var runningFunctions = ArrayList<(() -> Unit)?>()

    init {
        val rootLayout = activity.window.decorView.rootView
        val parent: ConstraintLayout = rootLayout.findViewById(parentLayout)
        view =
            LayoutInflater.from(activity).inflate(R.layout.curve_feb, parent, false)

        mainRoll = view.findViewById(R.id.mainRoll)

        rollCard = arrayListOf(
            view.findViewById(R.id.rollC1),
            view.findViewById(R.id.rollC2),
            view.findViewById(R.id.rollC3),
            view.findViewById(R.id.rollC4)
        )
        imageBox = arrayListOf(
            view.findViewById(R.id.ivRollC1),
            view.findViewById(R.id.ivRollC2),
            view.findViewById(R.id.ivRollC3),
            view.findViewById(R.id.ivRollC4)
        )

        targetButton.setOnClickListener {if (!mainRoll.isVisible) { fbRoll()} else {fbRollBack() } }

        val tooLayout = view.findViewById<ConstraintLayout>(R.id.too)
        parent.apply { addView(view)}

       // Set on View
        tooLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToStart = targetButton.id
            bottomToBottom = targetButton.id
            marginEnd = -170
               }
        this.icons = arrayListOf(
            android.R.drawable.ic_media_play, android.R.drawable.ic_input_add
            , android.R.drawable.btn_star_big_on, android.R.drawable.ic_delete
        )
        this.colors = arrayListOf(
            Color.parseColor("#4D4C7D"),
            Color.parseColor("#FF6363"),
            Color.parseColor("#E0D8B0"),
            Color.parseColor("#F8CB2E"))
        this.runningFunctions= arrayListOf(null,null,null,null)
        fillAll()
    }

    fun setItems(
        icons: ArrayList<Int>,
        colors: ArrayList<Int>,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) {
        this.icons = icons
        this.colors = colors
        this.runningFunctions = runningFunctions
        fillAll()

    }

    private fun fillAll(){
        for ((ico, note) in imageBox.withIndex()) {
            note.setImageResource(icons[ico])
        }
        for ((dt, doIt) in rollCard.withIndex()) {
            doIt.setCardBackgroundColor(colors[dt])
            doIt.setOnClickListener {
                runningFunctions[dt]?.invoke()
                clickRoll(doIt)
            }
        }
    }

    private fun fbRoll() {
        mainRoll.visibility = View.VISIBLE
        ValueAnimator.ofFloat(360f, 0f).apply {
            repeatCount = 0
            interpolator = LinearInterpolator()
            duration = 500
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                rollCard[0].rotation = progress
                rollCard[0].translationX = progress
                rollCard[1].rotation = progress
                rollCard[1].translationX = (progress / 1.5).toFloat()
                rollCard[1].translationY = progress / 2
                rollCard[2].rotation = progress
                rollCard[2].translationX = progress / 2
                rollCard[2].translationY = (progress / 1.2).toFloat()
                rollCard[3].rotation = progress
                rollCard[3].translationY = progress
                animation.doOnEnd {

                }
            }
        }.start()


    }

    private fun fbRollBack() {

        ValueAnimator.ofFloat(0f, 360f).apply {
            repeatCount = 0
            interpolator = LinearInterpolator()
            duration = 500
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                rollCard[0].rotation = progress
                rollCard[0].translationX = progress
                rollCard[1].rotation = progress
                rollCard[1].translationX = (progress / 1.2).toFloat()
                rollCard[1].translationY = progress / 2
                rollCard[2].rotation = progress
                rollCard[2].translationX = progress / 2
                rollCard[2].translationY = (progress / 1.2).toFloat()
                rollCard[3].rotation = progress
                rollCard[3].translationY = progress
                animation.doOnEnd {

                   mainRoll.visibility = View.GONE

                }
            }
        }.start()
    }

    private fun clickRoll(view: View) {
        ValueAnimator.ofFloat(1f, 0f).apply {
            repeatCount = 0
            interpolator = LinearInterpolator()
            duration = 500
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                view.scaleX = (progress * 2)
                view.scaleY = (progress * 2)
                animation.doOnEnd {
                    fbRollBack()
                    view.animate().scaleX(1f).scaleY(1f).startDelay = 340

                }
            }
        }.start()
    }

}