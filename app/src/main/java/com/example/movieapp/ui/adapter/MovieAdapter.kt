package com.example.movieapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.APIUrls
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.ui.MovieDetailsActivity
import timber.log.Timber
import kotlin.coroutines.coroutineContext

class MovieAdapter(val context:Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun addItems(movie: List<Movie>) {
        movies.addAll(movie)
        notifyDataSetChanged()
    }
    var onItemClick: ((Movie) -> Unit)? = null


    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textviewMovieTitle.text = movie.title
            if (movie.posterPath == "" || movie.posterPath == null || movie.posterPath == "null")
                binding.textviewMovieTitle.visibility = View.VISIBLE
            else Glide.with(this.itemView)
                .load(APIUrls.POSTER_BASE_URL_OF_SIZE_W500 + movie.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.shapeableImageviewMoviePoster)

            itemView.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val bind = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}