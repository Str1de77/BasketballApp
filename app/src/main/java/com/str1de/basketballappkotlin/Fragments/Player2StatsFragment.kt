package com.str1de.basketballappkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.str1de.basketballappkotlin.R
import com.str1de.basketballappkotlin.databinding.FragmentPlayer1StatsBinding
import com.str1de.basketballappkotlin.databinding.FragmentPlayer2StatsBinding


class Player2StatsFragment : Fragment() {

    private lateinit var binding: FragmentPlayer2StatsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayer2StatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = Player2StatsFragment()
            }
    }
