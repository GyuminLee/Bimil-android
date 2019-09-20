package com.gyumin.bimil.bimil

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyumin.bimil.R
import com.gyumin.bimil.data.Secret
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var secretViewModel: SecretViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SecretAdapter(
            { secret ->
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_SECRET_NAME, secret.name)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE_NUMBER, secret.typeNumber)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE_CAPITAL, secret.typeCapital)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE_SPECIALCHARACTER, secret.typeSpecialCharacter)
                intent.putExtra(AddActivity.EXTRA_SECRET_ADDRESS, secret.address)
                intent.putExtra(AddActivity.EXTRA_SECRET_NOTE, secret.note)
                intent.putExtra(AddActivity.EXTRA_SECRET_ID, secret.id)
                startActivity(intent)
            }, { secret ->
                deleteDialog(secret)

            })
        val lm = LinearLayoutManager(this)
        main_recycleview.adapter = adapter
        main_recycleview.layoutManager = lm
        main_recycleview.setHasFixedSize(true)

        secretViewModel = ViewModelProviders.of(this).get(SecretViewModel::class.java)

        secretViewModel.getAll().observe(this, Observer<List<Secret>> { secrets ->
            adapter.setSecrets(secrets!!)
            if(!secrets.isEmpty()) {
                initial_welcome_iv.visibility = GONE
            } else {
                initial_welcome_iv.visibility = VISIBLE
            }
        })

        main_button.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(secret: Secret) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to delete?")
            .setNegativeButton("No") {_, _ -> }
            .setPositiveButton("Yes") {_, _ ->
                secretViewModel.delete(secret)
            }
        builder.show()
    }
}
