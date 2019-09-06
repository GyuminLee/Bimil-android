package com.gyumin.bimil

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.secret_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var secretViewModel: SecretViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        secretViewModel = ViewModelProviders.of(this).get(SecretViewModel::class.java)

        if(intent != null
            && intent.hasExtra(EXTRA_SECRET_NAME)
            && intent.hasExtra(EXTRA_SECRET_TYPE)
            && intent.hasExtra(EXTRA_SECRET_ID)
            ) {
            add_edittext_name.setText(intent.getStringExtra(EXTRA_SECRET_NAME))
            add_edittext_type.setText(intent.getStringExtra(EXTRA_SECRET_TYPE))
            add_edittext_address.setText(intent.getStringExtra(EXTRA_SECRET_ADDRESS))
            add_edittext_note.setText(intent.getStringExtra(EXTRA_SECRET_NOTE))
            id = intent.getLongExtra(EXTRA_SECRET_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString() //.trim()
            val type = add_edittext_type.text.toString()
            val address = add_edittext_address.text.toString()
            val note = add_edittext_note.text.toString()

            if(name.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "At lest, Please enter name and PW type.", Toast.LENGTH_SHORT).show()
            } else {
                val secret = Secret(id, name, type, address, note)
                secretViewModel.insert(secret)
                finish()
            }
        }

    }

    companion object {
        const val EXTRA_SECRET_NAME     = "EXTRA_SECRET_NAME"
        const val EXTRA_SECRET_TYPE     = "EXTRA_SECRET_TYPE"
        const val EXTRA_SECRET_ADDRESS  = "EXTRA_SECRET_ADDRESS"
        const val EXTRA_SECRET_NOTE     = "EXTRA_SECRET_NOTE"
        const val EXTRA_SECRET_ID        = "EXTRA_SECRET_ID"
    }
}
