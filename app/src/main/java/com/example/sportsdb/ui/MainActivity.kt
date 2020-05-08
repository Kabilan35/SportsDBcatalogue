package com.example.sportsdb.ui

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsdb.MoshiClass
import com.example.sportsdb.R
import com.example.sportsdb.dataDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    val mosh: MutableLiveData<ArrayList<MoshiClass.teams>>?= null
    private lateinit var catalogViewModel: CatalogViewModel
    private lateinit var carouselView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("OnCreate")
        val manager = getSystemService(Context.SEARCH_SERVICE)
        carouselView = findViewById(R.id.carouselView)
        var searchView: SearchView = findViewById(R.id.search_bar)
        if(true){
            runBlocking { println("query")
            querySearch(searchView)}
        }
    }
    private fun setupViewModel() {
        println("SetupViewModel")
        catalogViewModel = ViewModelProviders.of(this)[CatalogViewModel::class.java]
        println("1")
        catalogViewModel.LiveData.observe(this, Observer {
            setupAdapter()
        })
        println("2")
        catalogViewModel.MapLiveData.observe(this, Observer {
            setupAdapter()
        })
        println("3")
    }
    private fun setupAdapter() {
        println("SetupAdapter")
        carouselView.run {
            val mLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            catalogViewModel.LiveData.value?.let {
                adapter =
                    SectionAdapter(dataDetails!!)
            }}
        }

    fun querySearch(searchView:SearchView){
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    @RequiresApi(Build.VERSION_CODES.P)
                    override fun onQueryTextSubmit(query: String): Boolean {
                        searchView.clearFocus()
                        searchView.setQuery("", false)
                        Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG)
                            .show()
                        println("Search")
                        println(dataDetails)
                        callAPI(query)
                        Thread.sleep(3000L)
                        println(dataDetails)
                        setupViewModel()
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }
            fun callAPI(query: String) {
                val mosh = MoshiClass().startConvert(query)
            }

//        Picasso.get().load(dataDetails!![position].TeamBadge+"/preview").fit().centerInside() .into(imageView)


}



