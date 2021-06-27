package com.example.binaracademy.gamesuit_challengechapter4.ui.landingpage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.binaracademy.gamesuit_challengechapter4.databinding.ActivityLandingPageBinding

class LandingPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()
        var currentPosition = 0
        val adapter = LandingPageAdapter(this)
        binding.let {
            it.lpContainer.adapter = adapter
            it.lpIndicator.setViewPager(it.lpContainer)
            it.lpContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPosition = position
                    it.lpContainer.setCurrentItem(currentPosition, true)
                }
            })

            binding.lpContainer.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when {
                        position == 0 -> {
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility = View.INVISIBLE
                            binding.tvPrevious.isEnabled = false
                        }
                        position < adapter.itemCount - 1 -> {
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility = View.VISIBLE
                            binding.tvPrevious.isEnabled = true
                        }
                        position == adapter.itemCount - 1 -> {
                            binding.tvNext.visibility = View.INVISIBLE
                            binding.tvNext.isEnabled = false
                            binding.tvPrevious.visibility = View.VISIBLE
                            binding.tvPrevious.isEnabled = true
                        }
                    }
                    super.onPageSelected(position)
                }
            })

            binding.lpIndicator.setViewPager(binding.lpContainer)
            binding.tvNext.setOnClickListener {
                if (getNextIndex() != -1) {
                    binding.lpContainer.setCurrentItem(getNextIndex(), true)
                }
            }
            binding.tvPrevious.setOnClickListener {
                if (getPreviousIndex() != -1) {
                    binding.lpContainer.setCurrentItem(getPreviousIndex(), true)
                }
            }
        }
    }

    private fun getPreviousIndex(): Int {
        val currentPageIndex = binding.lpContainer.currentItem
        return if (currentPageIndex - 1 >= 0) {
            currentPageIndex - 1
        } else {
            -1
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.lpContainer.adapter?.itemCount
        val currentPageIndex = binding.lpContainer.currentItem
        var selectedIndex = -1
        maxPages?.let {
            if (currentPageIndex + 1 < maxPages) {
                selectedIndex = currentPageIndex + 1
            }
        }
        return selectedIndex
    }
}