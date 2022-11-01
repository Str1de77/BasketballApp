package com.str1de.basketballappkotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.str1de.basketballappkotlin.Fragments.Player1StatsFragment
import com.str1de.basketballappkotlin.Fragments.Player2StatsFragment
import com.str1de.basketballappkotlin.databinding.PlayerResultsBinding

class PlayerResultsActivity: AppCompatActivity() {

    private lateinit var binding: PlayerResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerList = listOf<Fragment>(Player1StatsFragment.newInstance(), Player2StatsFragment.newInstance())

        val adapter = VP2ResultsAdapter(this, playerList)

        val listOfTab = listOf<String>("Player 1 Stats", "Player 2 Stats")

        binding.resultsViewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.resultsViewPager) {
            tab, pos ->
            tab.text = listOfTab[pos]
        }.attach()
    }
}