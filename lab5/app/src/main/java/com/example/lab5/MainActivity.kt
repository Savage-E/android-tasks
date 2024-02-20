package com.example.lab5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = setupRetrofit()
        fetchAndDisplayPosts(api)
    }

    private fun setupRetrofit(): JsonPlaceholderApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(JsonPlaceholderApi::class.java)
    }

    private fun fetchAndDisplayPosts(api: JsonPlaceholderApi) {
        api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response.body()
                setupRecyclerView(posts!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Ошибка при загрузке данных: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun setupRecyclerView(posts: List<Post>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = PostAdapter(posts)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
