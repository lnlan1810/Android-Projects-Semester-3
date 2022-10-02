package com.example.profilefromfacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.profilefromfacebook.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var bt:Button? =null
    private var imAvatar :ImageView? =null
    private var tvMessage: TextView? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMessage = findViewById<TextView>(R.id.tvName)
        tvMessage?.setOnClickListener{

        }

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.avatar?.setImageResource(R.drawable.pic)


        bt =findViewById<Button>(R.id.button)
        imAvatar =findViewById<ImageView>(R.id.avatar)

        bt?.setOnClickListener {
            var isvisible = avatar.visibility;
            if (isvisible == View.VISIBLE){
                avatar.visibility =View.GONE
            }else{
                avatar.visibility =View.VISIBLE
            }

        }

        //bt?.setOnClickListener(new View.OnClickListener());



    }
}