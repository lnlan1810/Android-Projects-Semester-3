package com.example.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.intent.databinding.ActivitySecondBinding
class SecondActivity : AppCompatActivity() {

    private var binding: ActivitySecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        getSupportActionBar()!!.hide();

        val intent = intent

        val uri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri
        binding?.imageView!!.setImageURI(uri)

    }
}