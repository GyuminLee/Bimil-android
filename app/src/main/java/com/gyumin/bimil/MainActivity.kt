package com.gyumin.bimil

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var secretViewModel: SecretViewModel



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        System.out.println("onSaveInstanceState Call")
        outState?.putString("stringKey", "String Test")
        outState?.putBoolean("booleanKey", true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null) {
            println(savedInstanceState.getString("stringKey"))
            println(savedInstanceState.getBoolean("booleanKey"))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SecretAdapter(
            { secret ->
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_SECRET_NAME, secret.name)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE, secret.type)
                intent.putExtra(AddActivity.EXTRA_SECRET_ADDRESS, secret.address)
                intent.putExtra(AddActivity.EXTRA_SECRET_NOTE, secret.note)
                intent.putExtra(AddActivity.EXTRA_SECRET_ID, secret.id)
                startActivity(intent)
                }, {secret -> deleteDialog(secret)

            })
        val lm = LinearLayoutManager(this)
        main_recycleview.adapter = adapter
        main_recycleview.layoutManager = lm
        main_recycleview.setHasFixedSize(true)

        secretViewModel = ViewModelProviders.of(this).get(SecretViewModel::class.java)

        secretViewModel.getAll().observe(this, Observer<List<Secret>> { secrets ->
            adapter.setSecrets(secrets!!)
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
