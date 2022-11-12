package com.str1de.basketballappkotlin

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.str1de.basketballappkotlin.Fragments.CloseRangeFragment
import com.str1de.basketballappkotlin.Fragments.LongRangeFragment
import com.str1de.basketballappkotlin.Fragments.MediumRangeFragment
import com.str1de.basketballappkotlin.databinding.ActivityTableBinding
import java.text.DecimalFormat

class TableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTableBinding

    private val dataModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rangeTab = findViewById<TabLayout>(R.id.rangeTab)
        val vp2 = findViewById<ViewPager2>(R.id.vp2)
        val fragmentList = listOf(CloseRangeFragment.newInstance(), MediumRangeFragment.newInstance(), LongRangeFragment.newInstance())
        val navigationView = findViewById<NavigationView>(R.id.nav_view)


        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.percent_of_throws_made -> startActivity(Intent(this, TableActivity::class.java))
                R.id.position_throws -> startActivity(Intent(this, PositionThrowsActivity::class.java))
            }
            true
        }


        val fragmentListTitles = listOf<String>("Close range", "Medium range", "Long range")

        val adapter = VpAdapter(this, fragmentList)
        vp2.adapter = adapter
        TabLayoutMediator(rangeTab, vp2){
                tab, pos -> tab.text = fragmentListTitles[pos]
        }.attach()

        /*accuracyButton.setOnClickListener {
            calculateAccuracy()
        }
*/
    }


        fun calculateAccuracy() {
        val numberOfThrows = findViewById<EditText>(R.id.number_of_throws)
        val specialRange = findViewById<EditText>(R.id.specialRange)
        val percentHitOfSpecialThrows = findViewById<TextView>(R.id.percent_of_throws)
        val numOfMyAllThrowsString = numberOfThrows.text.toString()
        val numOfMySpecialThrowsString = specialRange.text.toString()
        if (numOfMyAllThrowsString.isEmpty() || numOfMySpecialThrowsString.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Missing number of throws! Try again.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val numOfMyAllThrows = numOfMyAllThrowsString.toDouble()
            val numOfMySpecialThrows = numOfMySpecialThrowsString.toDouble()
            val percentOfMySpecialThrows = numOfMySpecialThrows / numOfMyAllThrows * 100
            if (numOfMyAllThrows < numOfMySpecialThrows) {
                val alertBuild = AlertDialog.Builder(this)
                alertBuild.create()
                alertBuild.setTitle("The number of all throws cannot be less than completed!")
                alertBuild.setMessage("Try again please!")
                alertBuild.setPositiveButton("OK") {dialog, id -> dialog.dismiss()}
                alertBuild.show()
            } else {
                startAnimation(percentHitOfSpecialThrows, percentOfMySpecialThrows, percentOfMySpecialThrows)


            /*val decimalPercent = DecimalFormat("#.#").format(percentOfMySpecialThrows)
            var resultOfPercentOfMySpecialThrows = decimalPercent.toString()
            if (percentOfMySpecialThrows <= 30) {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.red))
                startAnimation(percentHitOfSpecialThrows, percentOfMySpecialThrows, percentOfMySpecialThrows)
                animateResultOfPercent()
            } else if (percentOfMySpecialThrows > 30 && percentOfMySpecialThrows <= 70) {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.basketball_yellow))
                startAnimation(percentHitOfSpecialThrows, percentOfMySpecialThrows, percentOfMySpecialThrows)
                animateResultOfPercent()
            } else {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.green))
                startAnimation(percentHitOfSpecialThrows, percentOfMySpecialThrows, percentOfMySpecialThrows)
                animateResultOfPercent()
                }
            }

             */
            }
        }
        }

    fun startAnimation(textView: TextView, doubleValue: Double, percentOfMySpecialThrows : Double) {
        val animator = ValueAnimator.ofInt(0, doubleValue.toInt())
        animator.duration = 1500
        animator.addUpdateListener { animation ->
            val percentHitOfSpecialThrows = findViewById<TextView>(R.id.percent_of_throws)
            val decimalPercent = DecimalFormat("#.#").format(percentOfMySpecialThrows)
            var resultOfPercentOfMySpecialThrows = decimalPercent.toString()
            if (percentOfMySpecialThrows <= 30) {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.red))
                animTransition()
                textView.text = animation.animatedValue.toString() + " - bad!"
                animateResultOfPercent(percentHitOfSpecialThrows)
            } else if (percentOfMySpecialThrows > 30 && percentOfMySpecialThrows <= 70) {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.basketball_yellow))
                animTransition()
                textView.text = animation.animatedValue.toString() + " - normal!"
                animateResultOfPercent(percentHitOfSpecialThrows)
            } else {
                percentHitOfSpecialThrows.setTextColor(getColor(R.color.green))
                animTransition()
                textView.text = animation.animatedValue.toString() + " - good!"
                animateResultOfPercent(percentHitOfSpecialThrows)
                }
            }
        animator.start()
        }

    fun animateResultOfPercent(animatedTextView: TextView) {
        val animator = ValueAnimator.ofFloat(0f, -50f)
        animator.duration = 1000
        animator.start()

        animator.addUpdateListener (object: ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue = animation?.animatedValue as Float
                animatedTextView.translationX = animatedValue

            }

        })
    }

    fun animTransition() {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        val percentHitOfSpecialThrows = findViewById<TextView>(R.id.percent_of_throws)
        animator.duration = 1000
        animator.start()
        animator.addUpdateListener ( object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue = animation?.animatedValue as Float
                percentHitOfSpecialThrows.alpha = animatedValue
            }

        })
        }
    }

