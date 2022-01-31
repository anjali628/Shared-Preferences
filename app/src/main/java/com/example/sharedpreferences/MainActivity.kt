package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readDataFromSharedPreferences()

        binding.btnSaveData.setOnClickListener {
            if(binding.etAge.text.toString().isEmpty() || binding.etName.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext,"Something is Empty",Toast.LENGTH_SHORT).show()

            }

            else
            {
                saveDataInSharedPreferences(binding.etAge.text.toString().trim(),binding.etName.text.toString().trim())
            }
        }


    }

    private fun readDataFromSharedPreferences() {
        var sh:SharedPreferences=getSharedPreferences("MySharedPref",Context.MODE_PRIVATE)
        var name: String? =sh.getString("NAME","")
        var age:String?=sh.getString("AGE","")

        if (name != null) {
            if (age != null) {
                if (name.isEmpty() || age.isEmpty())
                {
                    binding.tvAge.setText("No data present")
                    binding.tvName.setText("No data present")
                    binding.btnSaveData.setText("No data present")
                }
                else
                {
                    binding.tvAge.setText(age)
                    binding.tvName.setText(name)
                    binding.btnSaveData.setText("Update Data")

                }

        }
    }
}



    private fun saveDataInSharedPreferences(age: String, name: String) {

        var sharedPreferences:SharedPreferences=getSharedPreferences("MySharedPref",Context. MODE_PRIVATE)
        var myEdit: SharedPreferences.Editor? =sharedPreferences.edit()
        myEdit?.putString("NAME",name)
        myEdit?.putString("AGE",age)
        myEdit?.commit()
        Toast.makeText(applicationContext,"Saved",Toast.LENGTH_SHORT).show()

        binding.etAge.setText(null)
        binding.etName.setText(null)
        binding.tvName.setText(name)
        binding.tvAge.setText(age)


    }
}