package com.example.binaracademy.gamesuit_challengechapter4.ui.landingpage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LandingPageAdapter (landingActivity: LandingPageActivity) : FragmentStateAdapter(landingActivity) {
    private val fragments= arrayOf(LandingFragment1(), LandingFragment2(), LandingFragment3())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}