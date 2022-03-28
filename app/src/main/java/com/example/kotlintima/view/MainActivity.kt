package com.example.kotlintima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintima.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container,
                MainFragment.newInstance()).commit()
            //реализуем появление view в контейнере (засовываем mainfragment)
        }
    }
}