package com.itis.firstapp.ui

import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.itis.firstapp.R
import com.itis.firstapp.model.DbCreator
import com.itis.firstapp.model.TaskDb
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var taskDb:TaskDb
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDb = DbCreator().initDB(this)
       controller = findController(R.id.container)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            setTheme(R.style.Theme_Custom_Light)
        } else {
            setTheme(R.style.Theme_Custom_Dark)
        }

        //requestWindowFeature(Window.FEATURE_NO_TITLE)
     //setupUI()
    }
    /*override fun onBackPressed() {
        when(supportFragmentManager.backStackEntryCount){
            0 -> super.onBackPressed()
            else -> supportFragmentManager.popBackStack()
        }
    }*/

}
