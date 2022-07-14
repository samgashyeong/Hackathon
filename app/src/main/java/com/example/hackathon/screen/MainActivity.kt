package com.example.hackathon.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hackathon.R
import com.example.hackathon.databinding.ActivityMainBinding
import com.example.hackathon.screen.one.FirstFragment
import com.example.hackathon.screen.three.ThirdFragment
import com.example.hackathon.screen.two.SecondFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val mainFragment1 by lazy { FirstFragment() }
    private val mainFragment2 by lazy { SecondFragment() }
    private val mainFragment3 by lazy { ThirdFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        changeFragment(mainFragment2)
        binding.bottomView.selectedItemId = R.id.two
        binding.bottomView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.one ->{
                    changeFragment(mainFragment1)
                    true
                }
                R.id.two ->{
                    changeFragment(mainFragment2)
                    true
                }
                R.id.three ->{
                    changeFragment(mainFragment3)
                    true
                }
                else->{
                    changeFragment(mainFragment2)
                    true
                }
            }
        }
    }

    private fun changeFragment(fragment: androidx.fragment.app.Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}