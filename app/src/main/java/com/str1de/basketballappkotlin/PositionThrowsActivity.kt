package com.str1de.basketballappkotlin

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar.Tab
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.str1de.basketballappkotlin.Fragments.ShotPositionFragment
import com.str1de.basketballappkotlin.Fragments.TableShotFragment
import com.str1de.basketballappkotlin.databinding.ActivityPositionThrowsBinding

class PositionThrowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPositionThrowsBinding

    val tableShotFr = TableShotFragment()
    val shotPosFr = ShotPositionFragment()
    var activeFragment : Fragment = shotPosFr

    var hitsCounter = 0
    var missCounter = 0
    var allHitsCounter = 0
    val listOfPosition = listOf<String>(
        "Left corner",
        "Left wing",
        "Top of the key",
        "Right wing",
        "Right corner",
        "Free-throw line / nail",
        "Left elbow",
        "Right elbow",
        "Left lane line",
        "Right lane line",
        "Left block",
        "Right block"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPositionThrowsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.percent_of_throws_made -> startActivity(Intent(this, TableActivity::class.java))
                R.id.position_throws -> startActivity(Intent(this, PositionThrowsActivity::class.java))
            }
            true
        }

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, tableShotFr).hide(tableShotFr)
            add(R.id.container, shotPosFr).commit()
        }


        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.results_table -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(tableShotFr).commit()
                    activeFragment = tableShotFr
                    true
                }
                R.id.position_throws -> {
                    supportFragmentManager.beginTransaction().hide(tableShotFr).show(shotPosFr).commit()
                    activeFragment = shotPosFr
                    true
                }
                else -> false
            }

        }
    }

        fun getBitmapFromResources(resources: Resources, resImage: Int): Bitmap {
            val options = BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inSampleSize = 1;
            options.inScaled = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            return BitmapFactory.decodeResource(resources, resImage, options);
        }

    fun timerTick() {
        this.runOnUiThread {
            val hitButton = findViewById<Button>(R.id.hitting_button)
            val missButton = findViewById<Button>(R.id.miss_button)
            val positionText = findViewById<TextView>(R.id.position_text)
            positionText?.text = ""
            positionText?.visibility = View.INVISIBLE
            hitButton.visibility = View.GONE
            missButton.visibility = View.GONE

        }
    }

        fun randomPosition() {
        val textPosition = findViewById<TextView>(R.id.position_text)
        val randomPosition = listOfPosition.random()
        val numberOfPosition = listOfPosition.indexOf(randomPosition) + 1
        textPosition.setText(numberOfPosition.toString() + " - " + randomPosition)
        }

    fun hitsCount() : String {
        val successHits = ++hitsCounter
        return successHits.toString()
    }

    fun missCount() : String {
        val missHits = ++missCounter
        return missHits.toString()
    }

    fun countAllThrows(): String {
        val allThrows = ++allHitsCounter
        return allThrows.toString()
    }




}