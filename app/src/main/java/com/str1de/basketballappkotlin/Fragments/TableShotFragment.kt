package com.str1de.basketballappkotlin.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.str1de.basketballappkotlin.PlayerResultsActivity
import com.str1de.basketballappkotlin.PositionThrowsActivity
import com.str1de.basketballappkotlin.R
import com.str1de.basketballappkotlin.ViewModel
import com.str1de.basketballappkotlin.databinding.FragmentTableShotBinding
import org.w3c.dom.Text

class TableShotFragment : Fragment() {

    private val dataModel: ViewModel by activityViewModels()

    private lateinit var binding: FragmentTableShotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.messageOfMissShot.observe(activity as LifecycleOwner) {
            binding.showThrowsMiss.text = it
        }
        dataModel.messageOfMadeShot.observe(activity as LifecycleOwner) {
            binding.showThrowsHit.text = it
        }
        dataModel.messageOfAllShots.observe(activity as LifecycleOwner) {
            binding.showAllThrows.text = it
        }

        binding.playerResultsButton.setOnClickListener {
            startActivity(Intent(activity as PositionThrowsActivity, PlayerResultsActivity::class.java))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTableShotBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = TableShotFragment()
    }
}