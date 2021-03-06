package com.gyumin.bimil.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyumin.bimil.R
import com.gyumin.bimil.adapter.SecretAdapter
import com.gyumin.bimil.data.Secret
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var secretViewModel: SecretViewModel
    private lateinit var adapter: SecretAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = SecretAdapter(
            { secret ->
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_SECRET_NAME, secret.name)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE_NUMBER, secret.typeNumber)
                intent.putExtra(AddActivity.EXTRA_SECRET_TYPE_CAPITAL, secret.typeCapital)
                intent.putExtra(
                    AddActivity.EXTRA_SECRET_TYPE_SPECIALCHARACTER,
                    secret.typeSpecialCharacter
                )
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

        //Init ViewModel by ViewModelProviders
        secretViewModel = ViewModelProviders.of(this).get(SecretViewModel::class.java)

        secretViewModel.allSecrets.observe(this, Observer { secrets ->
            secrets?.let { adapter.setSecrets(it) }
            if (secrets.isNotEmpty()) {
                initial_welcome_iv.visibility = GONE
            } else {
                initial_welcome_iv.visibility = VISIBLE
            }
        })


        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getItemsFromDb(searchText: String) {
        var searchText = searchText
        searchText = "%$searchText%"
        secretViewModel.searchForSecret(queryMsg = searchText)
            .observe(this, Observer { list ->
                list?.let {
                    Log.e("List = ", list.toString())
                    adapter.setSecrets(it)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.main_option_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search Bimil")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                getItemsFromDb(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                getItemsFromDb(query)
                return true
            }

        })

        return true

    }

    private fun deleteDialog(secret: Secret) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to delete?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                secretViewModel.delete(secret)
            }
        builder.show()
    }
}
