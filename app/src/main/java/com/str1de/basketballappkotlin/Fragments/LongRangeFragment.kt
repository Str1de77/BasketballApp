package com.str1de.basketballappkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.str1de.basketballappkotlin.R
import com.str1de.basketballappkotlin.TableActivity

class LongRangeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accuracyButton : Button = view.findViewById(R.id.accuracyLongButton)
        accuracyButton.setOnClickListener {
            (activity as TableActivity).calculateAccuracy()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_long_range, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LongRangeFragment()
    }
}