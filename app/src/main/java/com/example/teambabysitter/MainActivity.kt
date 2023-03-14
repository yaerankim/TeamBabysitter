package com.example.teambabysitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.teambabysitter.navigation.DetailViewFragment
import com.example.teambabysitter.navigation.GridFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.action_home -> {
                var detailViewFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, detailViewFragment).commit()
                return true
            }
            R.id.action_map -> {
                var gridFragment = GridFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, gridFragment).commit()
                return true
            }
            R.id.action_community -> {

                return true
            }
            R.id.action_diary -> {
                var alarmFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, alarmFragment).commit()
                return true
            }
            R.id.action_mypage -> {
                var userFragment = DetailViewFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, userFragment).commit()
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)

    }
}
