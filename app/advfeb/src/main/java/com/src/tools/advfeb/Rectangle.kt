package com.src.tools.advfeb

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel


class Rectangle(activity: Activity, parentLayout: Int) {
    private var view: View
    var res: Resources = activity.resources
    private var modelFeb: RelativeLayout
    private var ivBack: ImageView
    private var febModView = ArrayList<MaterialCardView>()
    private var tableRows = ArrayList<TableRow>()
    private var textBox = ArrayList<TextView>()
    private var imageBox = ArrayList<ImageView>()
    private var icons = ArrayList<Int>()
    private var colors = ArrayList<Int>()
    private var textIn = ArrayList<String>()
    private var runningFunctions = ArrayList<(() -> Unit)?>()


    private var textColor: Int = Color.BLACK

    init {
        val rootLayout = activity.window.decorView.rootView
        val parent: ConstraintLayout = rootLayout.findViewById(parentLayout)
        view =
            LayoutInflater.from(activity).inflate(R.layout.rectangle_feb, parent, false)

        modelFeb = view.findViewById(R.id.modelFeb)
        ivBack = view.findViewById(R.id.ivBack)


        febModView = arrayListOf(
            view.findViewById(R.id.fbC1),
            view.findViewById(R.id.fbC2),
            view.findViewById(R.id.fbC3),
            view.findViewById(R.id.fbC4)
        )
        tableRows = arrayListOf(
            view.findViewById(R.id.tr0),
            view.findViewById(R.id.tr1),
            view.findViewById(R.id.tr2),
            view.findViewById(R.id.tr3)
        )
        textBox = arrayListOf(
            view.findViewById(R.id.tvC1),
            view.findViewById(R.id.tvC2),
            view.findViewById(R.id.tvC3),
            view.findViewById(R.id.tvC4)
        )
        imageBox = arrayListOf(
            view.findViewById(R.id.ivC1),
            view.findViewById(R.id.ivC2),
            view.findViewById(R.id.ivC3),
            view.findViewById(R.id.ivC4)
        )
        var cc = true
        val fbBtn = view.findViewById<MaterialCardView>(R.id.fbBtn)
        fbBtn.setOnClickListener {
            if (cc) {

                modelFeb.visibility = View.VISIBLE
                fbModel()
                cc = false
            } else {

                fbModelBack()
                cc = true
            }

        }
        this.icons = arrayListOf(
            android.R.drawable.ic_media_play, android.R.drawable.ic_input_add
            , android.R.drawable.btn_star_big_on, android.R.drawable.ic_delete
        )
        this.colors = arrayListOf(
            Color.parseColor("#F10086"),
            Color.parseColor("#FD5D5D"),
            Color.parseColor("#8BDB81"),
            Color.parseColor("#333C83")
        )
        this.textIn = arrayListOf("One", " two", "see it", "fun")
        this.textColor =Color.WHITE
        this.runningFunctions= arrayListOf(null,null,null,null)
        fillAll()
        parent.apply { addView(view) }
    }

    fun setItems(
        icons: ArrayList<Int>,
        colors: ArrayList<Int>,
        textIn: ArrayList<String>,
        textColor: Int,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) {
        this.icons = icons
        this.colors = colors
        this.textIn = textIn
        this.textColor = textColor
        this.runningFunctions = runningFunctions
        fillAll()


    }

    private fun fillAll(){
        for ((rc, rowColor) in tableRows.withIndex()) {
            rowColor.setBackgroundColor(colors[rc])
        }
        for ((t, note) in textBox.withIndex()) {
            note.text = textIn[t]
        }
        for (note in textBox) {
            note.setTextColor(textColor)
        }
        for ((ico, note) in imageBox.withIndex()) {
            note.setImageResource(icons[ico])
        }
        for ((dt, doIt) in febModView.withIndex()) {
            doIt.setOnClickListener {
                runningFunctions[dt]?.invoke()

            }
        }
    }
    private fun fbModel() {
        modelFeb.visibility = View.VISIBLE
        ivBack.visibility = View.INVISIBLE

        val shapeAppearanceModel: ShapeAppearanceModel.Builder = ShapeAppearanceModel().toBuilder()
        shapeAppearanceModel.setAllCorners(
            CornerFamily.ROUNDED,
            15F
        )
        for (core in febModView) core.shapeAppearanceModel = shapeAppearanceModel.build()

        val colorFeb = colors //arrayListOf(Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED)
        var l = 100
        for ((k, a) in febModView.withIndex()) {
            colorCardChanging(a, Color.WHITE, colorFeb[k])

            ValueAnimator.ofFloat(2500f - l, 0f).apply {
                repeatCount = 0
                interpolator = AccelerateDecelerateInterpolator()
                duration = (800 + (l)).toLong()
                addUpdateListener { animation ->
                    val progress = animation.animatedValue as Float

                    a.scaleX = 1 - (progress / 2000)
                    a.scaleY = 1 - (progress / 2000)
                    a.translationY = progress / 2
                    a.translationX = progress / 5

                }
            }.start()
            l += 500

        }

        ValueAnimator.ofFloat(0f, 260f).apply {
            //startDelay = 1000
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            //  repeatMode = REVERSE
            duration = 2000
            addUpdateListener { animation ->
                val progressEndF = animation.animatedValue as Float
                modelFeb.alpha = 1 - ((260 - progressEndF) / 260)

            }
            doOnEnd {

                // binding.modelFeb.visibility = View.GONE
            }
        }.start()


        ValueAnimator.ofFloat(0f, 45f).apply {
            //startDelay = 1000
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            //  repeatMode = REVERSE
            duration = 800
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                tableRows[0].rotation = progress
                tableRows[1].rotation = progress * -1
                tableRows[2].rotation = progress
                tableRows[3].rotation = progress * -1

            }
            doOnEnd {

                // binding.modelFeb.visibility = View.GONE
            }
        }.start()

    }

    private fun fbModelBack() {
        modelFeb.visibility = View.VISIBLE
        val colorFeb = colors//arrayListOf(Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED)
        var l = 100
        val lastPos = view.findViewById<MaterialCardView>(R.id.fbBtn).y

        for ((k, a) in febModView.withIndex()) {
            colorCardChanging(a, colorFeb[k], Color.WHITE)

            ValueAnimator.ofFloat(0f, lastPos - l).apply {
                repeatCount = 0
                interpolator = AccelerateDecelerateInterpolator()
                duration = (900 - (l / 4)).toLong()
                addUpdateListener { animation ->
                    val progress = animation.animatedValue as Float

                    a.scaleX = 1 - (progress / 2000)
                    a.scaleY = 1 - (progress / 2000)
                    a.translationY = progress / 2
                    a.translationX = progress / 5


                    animation.doOnEnd {
                        ValueAnimator.ofFloat(2f, 10f).apply {
                            repeatCount = 0
                            interpolator = AccelerateDecelerateInterpolator()
                            duration = 100
                            addUpdateListener { animation ->
                                val progressEnd = animation.animatedValue as Float
//                            for (a in febModView )

                                a.scaleX = 1f / progressEnd
                                a.scaleY = 1f / progressEnd
                                a.translationX = progressEnd + 350
                                //  println(progressEnd)
                                animation.doOnEnd {


                                }
                            }
                        }.start()

                    }
                }
            }.start()
            l += 500
        }
        ValueAnimator.ofFloat(0f, 160f).apply {
            startDelay = 1000
            repeatCount = 0
            interpolator = AccelerateDecelerateInterpolator()
            repeatMode = ValueAnimator.REVERSE
            duration = 500
            addUpdateListener { animation ->
                val progressEndA = animation.animatedValue as Float
                modelFeb.alpha = (160 - progressEndA) / 160

            }
            doOnEnd {
                modelFeb.visibility = View.GONE
                ivBack.visibility = View.GONE
            }
        }.start()

        for (t in tableRows) {
            ValueAnimator.ofFloat(70f, 0f).apply {
                //startDelay = 1000
                repeatCount = 0
                interpolator = AccelerateDecelerateInterpolator()
                //  repeatMode = REVERSE
                duration = 800
                addUpdateListener { animation ->
                    val progress = animation.animatedValue as Float
                    t.rotationX = progress
                    t.rotation = progress / 2
                }
                doOnEnd {

                    // binding.modelFeb.visibility = View.GONE
                }
            }.start()
        }
    }

    // CHANGE Card View color
    private fun colorCardChanging(
        frameLayout: MaterialCardView,
        background_from: Int,
        background_to: Int
    ) {
        val objectAnimator = ObjectAnimator.ofObject(
            frameLayout,
            "cardBackgroundColor",
            ArgbEvaluator(),
            background_from,
            background_to
        )

        objectAnimator.duration = 1000
        objectAnimator.start()
    }

}