package com.str1de.basketballappkotlin


import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.str1de.basketballappkotlin.databinding.LaunchscreenBinding
import java.util.Timer
import java.util.TimerTask

class LaunchScreenActivity: AppCompatActivity() {
    private lateinit var binding: LaunchscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = LaunchscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.linearLayout.visibility = View.INVISIBLE
        val timer: Timer? = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        },3000)
        animateLogo()
        animLinearLayout()
        binding.linearLayout.visibility = View.VISIBLE
    }

    fun animateLogo() {
        var animate = ValueAnimator.ofFloat(0f, 1f)
        animate.duration = 1500
        animate.start()

        animate.addUpdateListener ( object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animator = animation?.animatedValue as Float
                binding.liveBallersLogo.scaleX = animator
                binding.liveBallersLogo.scaleY = animator
            }
        })
    }

    fun animLinearLayout() {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 2000
        animator.start()

        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animate = animation?.animatedValue as Float
                binding.linearLayout.alpha = animate
            }

        })
    }
}