package com.example.movieapp.data.remote

object APIUrls {
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "c62dca1998f28811d341e8349678bc1b"
    const val PAGE = "1"
    const val LATEST_MOVIE = "now_playing?api_key=${API_KEY}&language=en-US&"
    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val POSTER_SIZE_W342 = "w342"
    private const val POSTER_SIZE_W500 = "w500"
    private const val POSTER_SIZE_W780 = "w780"
    private const val POSTER_SIZE_ORIGINAL = "original"
    const val POSTER_BASE_URL_OF_SIZE_W500 = POSTER_BASE_URL + POSTER_SIZE_W500
    const val POSTER_BASE_URL_OF_SIZE_W342 = POSTER_BASE_URL + POSTER_SIZE_W342
    const val POSTER_BASE_URL_OF_SIZE_W780 = POSTER_BASE_URL + POSTER_SIZE_W780
    const val POSTER_BASE_URL_OF_SIZE_ORIGINAL = POSTER_BASE_URL + POSTER_SIZE_ORIGINAL
}