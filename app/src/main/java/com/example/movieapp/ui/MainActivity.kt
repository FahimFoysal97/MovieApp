package com.example.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.remote.APIUrls
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.utils.DataState
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var pageNumber: Int = 1
    private var totalPage: Int = 0
    private var isLoading: Boolean = false
    private val movieViewModel: MovieViewModel by viewModels()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callTheAPI()
        initView()

        //initButtons()
    }
    private fun initView() {
        binding.recyclerviewMoviesList.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter
        }
        binding.recyclerviewMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1) && !isLoading && pageNumber<=totalPage) {
                    isLoading = true
                    movieViewModel.getMovies(pageNumber++)
                }
            }
        })
        movieViewModel.getMovies(pageNumber++)
        movieAdapter.onItemClick = { movieItem ->
            val gson = Gson()
            val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java).apply {
                putExtra("movie", gson.toJson(movieItem))
            }
            Timber.e(movieItem.title)
            startActivity(intent)
        }
    }

    private fun callTheAPI() {
        movieViewModel.movie.observe(this){
            when(it){
                is DataState.Loading->{
                    Timber.e("Loading...")
                }
                is DataState.Success->{
                    totalPage=it.data.totalPages
                    Timber.e(it.data.results[0].title)
                    movieAdapter.addItems(it.data.results)
                    isLoading = false
                }
                is DataState.Error->{
                    Timber.e("Error...")
                }
            }
        }
    }
}