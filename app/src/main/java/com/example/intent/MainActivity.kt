package com.example.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ShareCompat
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

   // private lateinit var edWebsite: EditText
   // private lateinit var edPhone: EditText
   // private lateinit var edLocation: EditText
  //  private lateinit var edText: EditText
    //edText
    //private var btExplicit: Button?= null
   // private var btImplicit: Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()!!.hide();

        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }

        binding?.run{
            button.setOnClickListener(){

                val intentWeb = Intent(Intent.ACTION_VIEW).apply{
                    data = Uri.parse(edWebsite.text.toString())
                }
                startActivity(intentWeb)


            }

            button2.setOnClickListener(){
                val intent = Intent(Intent.ACTION_DIAL).apply{
                   data = Uri.parse( "tel: "+ edPhone.text.toString())
                }
                startActivity(intent)

            }

            button4.setOnClickListener(){
                ShareCompat.IntentBuilder.from(this@MainActivity)
                    .setText(edText.text.toString())
                    .setChooserTitle("Sharing Text")
                    .setType("text/plain")
                    .startChooser()

            }




        }


       // edWebsite = findViewById(R.id.edWebsite)
     //   edPhone = findViewById(R.id.edPhone)
    //    edLocation = findViewById(R.id.edLocation)
     //   edText = findViewById(R.id.edText)


    }
    /*
    fun openwebsite(view: View) {
        var uri : Uri = Uri.parse(edWebsite.text.toString())
        startActivity(Intent(Intent.ACTION_VIEW, uri))

    }

    fun opendialpad(view: View) {
        var uri : Uri = Uri.parse( "tel: "+ edWebsite.text.toString())
        startActivity(Intent(Intent.ACTION_DIAL, uri))

    }
/*
    fun openmap(view: View) {
        var uri : Uri = Uri.parse("geo:0,0?q="+ edLocation.text.toString())
        startActivity(Intent(Intent.ACTION_VIEW, uri))

    }

 */

    fun shareText(view: View) {
        ShareCompat.IntentBuilder.from(this)
            .setText(edText.text.toString())
            .setChooserTitle("Sharing Text")
            .setType("text/plain")
            .startChooser()
    } */



}