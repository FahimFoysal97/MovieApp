package com.example.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.APIUrls
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.google.gson.Gson

class MovieDetailsActivity() : AppCompatActivity() {
    private lateinit var _binding: ActivityMovieDetailsBinding
    private lateinit var _movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        initView()
    }

    private fun initView() {
        val json = intent.getStringExtra("movie")
        _movie = Gson().fromJson(json, Movie::class.java)

        _binding.movieName.text = "Title: " + _movie.title
        _binding.movieDetails.text = "Overview: " + _movie.overview
        _binding.movieReleased.text = "Released: " + _movie.releaseDate

        Glide.with(this.applicationContext)
            .load(APIUrls.POSTER_BASE_URL_OF_SIZE_W780 + _movie.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(_binding.movieDetailImage)
    }
}