package com.gyumin.bimil.bimil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gyumin.bimil.R
import com.gyumin.bimil.data.Secret
import kotlinx.android.synthetic.main.secret_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var secretViewModel: SecretViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.title = "Create New Bimil"

        secretViewModel = ViewModelProviders.of(this).get(SecretViewModel::class.java)

        if(intent != null
            && intent.hasExtra(EXTRA_SECRET_NAME)
            && intent.hasExtra(EXTRA_SECRET_ID)
            ) {

            add_edittext_name.setText(intent.getStringExtra(EXTRA_SECRET_NAME))
            type_checkbox_number.isChecked = intent.getBooleanExtra(EXTRA_SECRET_TYPE_NUMBER, false)
            type_checkbox_capital.isChecked = intent.getBooleanExtra(EXTRA_SECRET_TYPE_CAPITAL, false)
            type_checkbox_specialcharacter.isChecked = intent.getBooleanExtra(
                EXTRA_SECRET_TYPE_SPECIALCHARACTER, false)
            add_edittext_address.setText(intent.getStringExtra(EXTRA_SECRET_ADDRESS))
            add_edittext_note.setText(intent.getStringExtra(EXTRA_SECRET_NOTE))
            id = intent.getLongExtra(EXTRA_SECRET_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString()
            val typeNumber = type_checkbox_number.isChecked
            val typeCapital = type_checkbox_capital.isChecked
            val typeSpecialCharacter = type_checkbox_specialcharacter.isChecked
            val address = add_edittext_address.text.toString()
            val note = add_edittext_note.text.toString()

            if(name.isEmpty()) {
                Toast.makeText(this, "At lest, Please enter name and PW type.", Toast.LENGTH_SHORT).show()
            } else {
                val secret = Secret(id, name, typeNumber, typeCapital, typeSpecialCharacter, address, note)
                secretViewModel.insert(secret)
                finish()
            }
        }

    }

    companion object {
        const val EXTRA_SECRET_NAME                  = "EXTRA_SECRET_NAME"
        const val EXTRA_SECRET_TYPE_NUMBER           = "EXTRA_TYPE_NUMBER"
        const val EXTRA_SECRET_TYPE_CAPITAL          = "EXTRA_TYPE_CAPITAL"
        const val EXTRA_SECRET_TYPE_SPECIALCHARACTER = "EXTRA_TYPE_SPECIALCHARACTER"
        const val EXTRA_SECRET_ADDRESS               = "EXTRA_SECRET_ADDRESS"
        const val EXTRA_SECRET_NOTE                  = "EXTRA_SECRET_NOTE"
        const val EXTRA_SECRET_ID                    = "EXTRA_SECRET_ID"
    }
}
