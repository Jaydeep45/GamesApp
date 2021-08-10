package com.example.gamesApplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest



class MainActivity : AppCompatActivity(), NewItemClicked {
    private lateinit var multipleAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview: RecyclerView = findViewById(R.id.recycleView)
        recyclerview.layoutManager = LinearLayoutManager(this)
         fetchData()
        multipleAdapter = NewsAdapter(this)
        recyclerview.adapter = multipleAdapter
    }
    private fun fetchData()  {
        val url = "https://www.freetogame.com/api/games"
        val jsonArrayRequestRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsArray = ArrayList<Games>()
                for(i in 0 until it.length()) {
                    val games = it.getJSONObject(i)
                    val news = Games(
                        games.getString("title"),
                        games.getString("developer"),
                        games.getString("game_url"),
                        games.getString("thumbnail")
                    )
                    newsArray.add(news)
                }
                multipleAdapter.updateNews(newsArray)
            },
            {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequestRequest)
    }

    override fun onItemClicked(item: Games) {
        val builder = CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}