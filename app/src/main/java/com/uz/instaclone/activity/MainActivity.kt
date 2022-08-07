package com.uz.instaclone.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.uz.instaclone.R
import com.uz.instaclone.adapter.ViewPagerAdapter
import com.uz.instaclone.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val TAG = MainActivity::class.java.simpleName
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager.currentItem = 0
                R.id.navigation_search -> viewPager.currentItem = 1
                R.id.navigation_upload -> viewPager.currentItem = 2
                R.id.navigation_favorite -> viewPager.currentItem = 3
                R.id.navigation_profile -> viewPager.currentItem = 4
            }
            true
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                index = position
                bottomNavigationView.menu.getItem(index).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment())
        adapter.addFragment(SearchFragment())
        adapter.addFragment(UploadFragment())
        adapter.addFragment(FavoriteFragment())
        adapter.addFragment(ProfileFragment())
        viewPager.adapter = adapter
    }
}