package com.kotlingithubapiapplication.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlingithubapiapplication.R
import com.kotlingithubapiapplication.activity.adapter.RepositoriesAdapter
import com.kotlingithubapiapplication.activity.model.Repository
import com.kotlingithubapiapplication.activity.network.RetrofitClient
import com.kotlingithubapiapplication.activity.network.NetworkApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RepositoriesAdapter

    var reposList: List<Repository> = mutableListOf()

    private val retrofitService: NetworkApi by lazy {
        RetrofitClient.reposService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = recyclerRepos
        val layoutLinearManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutLinearManager
        recycler.setVerticalScrollBarEnabled(true);

        button_search.setOnClickListener {
            val username = edit_text_username.text.toString()
            loadData(username)
        }
    }

    private fun loadData(userRepo: String) {
        retrofitService.getUserRepo(userRepo).enqueue(object : Callback<List<Repository>> {

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {

                if (response.isSuccessful) {
                    showToast("successful")

                    reposList = response.body()!!
                    setupRecyclerView(reposList)

                } else
                    showToast("posts loaded from API " + response.code() + response.errorBody())
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                showToast(t.localizedMessage)
            }
        })
    }

    private fun setupRecyclerView(items: List<Repository>) {

        adapter = RepositoriesAdapter(
            this,
            items
        )

        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }

}
