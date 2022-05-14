package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.di.MainViewModelFactory.make
import com.picpay.desafio.android.ui.adapter.UserListAdapter


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: MainViewModel by lazy { make() }


    private fun showContactsList() {
        showProgressBar(false)
        viewModel.users.observe(this) {
            adapter.users = it
        }
    }

    private fun showErrorMessage() {
        val message = getString(R.string.error)

        showProgressBar(false)
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showProgressBar(flag: Boolean) {
        progressBar.visibility = when (flag) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getContacts()
        viewModel.state.observe(this) {
            when (it) {
                State.SUCCESS -> showContactsList()
                State.FAILURE -> showErrorMessage()
                State.LOADING -> showProgressBar(true)
            }
        }
    }
}
