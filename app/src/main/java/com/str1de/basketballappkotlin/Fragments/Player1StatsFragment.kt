package com.str1de.basketballappkotlin.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.str1de.basketballappkotlin.databinding.FragmentPlayer1StatsBinding

class Player1StatsFragment : Fragment() {

    private lateinit var binding: FragmentPlayer1StatsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        binding.showAllThrows.text = (sharedPref?.getString("allShots", "0"))
        binding.showThrowsHit.text = (sharedPref?.getString("madeShots", "0"))
        binding.showThrowsMiss.text = (sharedPref?.getString("missShots", "0"))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayer1StatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = Player1StatsFragment()
            }
    }