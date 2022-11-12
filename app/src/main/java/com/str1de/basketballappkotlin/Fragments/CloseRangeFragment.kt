package com.str1de.basketballappkotlin.Fragments

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.str1de.basketballappkotlin.R
import com.str1de.basketballappkotlin.TableActivity
import com.str1de.basketballappkotlin.databinding.FragmentCloseRangeBinding

class CloseRangeFragment : Fragment() {

    private lateinit var binding: FragmentCloseRangeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.accuracyCloseButton.setOnClickListener {
                (activity as TableActivity).calculateAccuracy()
                if (binding.percentOfThrows.currentTextColor == (R.color.red)) {
                    binding.percentOfThrows.text =  " - Bad"
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCloseRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CloseRangeFragment()
    }


}