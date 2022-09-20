package com.pincrux.offerwall.kotlinsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    private lateinit var edit: AppCompatEditText
    private lateinit var next: AppCompatButton

    private val preferences: Preference by lazy {
        Preference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findId()
        clickAction()
    }

    private fun findId() {
        edit = findViewById(R.id.edit)
        next = findViewById(R.id.next)

        edit.setText(preferences.pubkey)
    }

    private fun clickAction() {
        next.setOnClickListener {
            if (edit.text != null) {
                preferences.pubkey = edit.text.toString()
                startActivity(Intent(this, OptionActivity::class.java))
            } else {
                Toast.makeText(this, R.string.pub_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
}