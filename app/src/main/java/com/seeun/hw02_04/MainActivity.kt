package com.seeun.hw02_04

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var resultButton: Button
    lateinit var nameEditText: EditText
    lateinit var weightEditText: EditText
    lateinit var heightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultButton = findViewById<Button>(R.id.resultButton)
        nameEditText = findViewById<EditText>(R.id.nameEditText)
        weightEditText = findViewById<EditText>(R.id.weightEditText)
        heightEditText = findViewById<EditText>(R.id.heightEditText)

        loadData()

        resultButton.setOnClickListener{
            saveData(nameEditText.text.toString(),
                        heightEditText.text.toString().toInt(),
                        weightEditText.text.toString().toInt())
            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", heightEditText.text.toString())
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("name", nameEditText.text.toString())
            startActivity(intent)
        }
    }

    private fun loadData() {
        var pref = this.getPreferences(0)
        var name = pref.getString("KEY_NAME", "")
        var height = pref.getInt("KEY_HEIGHT", 0)
        var weight = pref.getInt("KEY_WEIGHT", 0)

        if(name != "" && height != 0 && weight != 0){
            nameEditText.setText(name.toString())
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }


    private fun saveData(name: String, height: Int, weight: Int){
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putString("KEY_NAME",
            nameEditText.text.toString()).apply()
        editor.putInt("KEY_HEIGHT",
            heightEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_WEIGHT",
            weightEditText.text.toString().toInt()).apply()
    }

}