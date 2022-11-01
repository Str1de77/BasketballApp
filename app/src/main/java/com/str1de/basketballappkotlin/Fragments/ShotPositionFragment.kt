package com.str1de.basketballappkotlin.Fragments

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.str1de.basketballappkotlin.PositionThrowsActivity
import com.str1de.basketballappkotlin.R
import com.str1de.basketballappkotlin.ViewModel
import com.str1de.basketballappkotlin.databinding.FragmentShotPositionBinding
import java.util.*

class ShotPositionFragment : Fragment() {

    private val dataModel: ViewModel by activityViewModels()

    private lateinit var binding: FragmentShotPositionBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var timer: Timer? = null


        binding.hittingButton.visibility = View.GONE
        binding.missButton.visibility = View.GONE


        binding.positionButton.setOnClickListener {
            if (binding.positionButton.text == getString(R.string.start_button)) {
                animStartButton(0f, 200f)
                binding.positionButton.text = getString(R.string.finish_button)
                animThrowsAndTextButtons(0f, 1f, 3000)
                binding.hittingButton.visibility = View.VISIBLE
                binding.missButton.visibility = View.VISIBLE
                binding.positionText.visibility = View.VISIBLE
            } else {
                showSaveDialog()
            }
        }

        binding.missButton.setOnClickListener {
            checkingForThrows()
            dataModel.messageOfMissShot.value = (activity as PositionThrowsActivity).missCount()
            dataModel.messageOfAllShots.value = (activity as PositionThrowsActivity).countAllThrows()
            (activity as PositionThrowsActivity).randomPosition()
        }

        binding.hittingButton.setOnClickListener {
            checkingForThrows()
            dataModel.messageOfMadeShot.value = (activity as PositionThrowsActivity).hitsCount()
            dataModel.messageOfAllShots.value = (activity as PositionThrowsActivity).countAllThrows()
            (activity as PositionThrowsActivity).randomPosition()
        }
        binding.shotsPositionImageView.setImageBitmap(
            (activity as PositionThrowsActivity).getBitmapFromResources(
                resources,
                R.mipmap.ic_shot_positions_foreground
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShotPositionBinding.inflate(inflater, container, false)
        return binding.root
    }


    fun animStartButton(initialValue: Float, finalValue: Float) {
        val animator = ValueAnimator.ofFloat(initialValue, finalValue)
        animator.duration = 1000
        animator.start()

        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue = animation?.animatedValue as Float
                binding.positionButton.translationY = animatedValue
            }
        })
    }

    fun animThrowsAndTextButtons(initialValue: Float, finalValue: Float, durat: Long) {
        val animator = ValueAnimator.ofFloat(initialValue, finalValue)
        animator.duration = durat
        animator.start()

        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue = animation?.animatedValue as Float
                binding.hittingButton.alpha = animatedValue
                binding.missButton.alpha = animatedValue
                binding.positionText.alpha = animatedValue
            }

        })
    }

    fun showSaveDialog() {
        var timer: Timer? = null
        val saveDialog = AlertDialog.Builder(activity as PositionThrowsActivity)
        saveDialog.create()
        saveDialog.setTitle("Do you want to save your result?")
        saveDialog.setMessage("Your result will be saved to a text document.")
        saveDialog.setPositiveButton("Yes") { dialog, id ->
            dialog.dismiss()
        }
        saveDialog.setNegativeButton("No") { dialog, id ->
            timer = Timer()
            resetStats()
            binding.positionButton.text = getString(R.string.start_button)
            animThrowsAndTextButtons(1f, 0f, 1000)
            animStartButton(200f, 0f)
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    (activity as PositionThrowsActivity).timerTick()
                }
            }, 1000)
        }
        saveDialog.show()
    }

    fun resetStats() {
        (activity as PositionThrowsActivity).allHitsCounter = 0
        dataModel.messageOfAllShots.value = (0).toString()
        (activity as PositionThrowsActivity).missCounter = 0
        dataModel.messageOfMissShot.value = (0).toString()
        (activity as PositionThrowsActivity).hitsCounter = 0
        dataModel.messageOfMadeShot.value = (0).toString()
    }

    fun checkingForThrows() {
            if ((dataModel.messageOfAllShots.value)?.toInt() == 9) {
                val alertBuilder = AlertDialog.Builder(activity)
                alertBuilder.create()
                alertBuilder.setTitle("You made " + (dataModel.messageOfAllShots.value).toString() + " throws!")
                alertBuilder.setMessage("Do you save this result?")
                alertBuilder.setPositiveButton("Yes") { dialog, id ->
                        val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
                    with (sharedPref?.edit()) {
                        this?.putString("allShots", dataModel.messageOfAllShots.value)
                        this?.putString("madeShots", dataModel.messageOfMadeShot.value)
                        this?.putString("missShots", dataModel.messageOfMissShot.value)
                        this?.apply()
                    }
                }
                alertBuilder.setNegativeButton("No") {dialog, id ->
                    dialog.dismiss()
                }
                alertBuilder.show()
            }
        }

    companion object {

        @JvmStatic
        fun newInstance() = ShotPositionFragment()

    }
}