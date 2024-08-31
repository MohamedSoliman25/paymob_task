package com.example.paymob_movie_task.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.paymob_movie_task.R
import com.example.paymob_movie_task.databinding.ActivityMovieDetailsBinding
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.presentation.viewmodel.MovieViewModel
import com.example.paymob_movie_task.utils.MyUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieDetailsBinding
    private var movie:Movie? = null
    private  val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiveMovie()
    }

    private fun receiveMovie(){
        movie = intent.getSerializableExtra(MyUtils.MOVIE_ITEM) as? Movie
        movie?.let {
            displayData(it)
            addToFavorite(it)
        }
    }
    private fun displayData(movie:Movie) {
        binding.apply {
            binding.textViewMovieName.title = movie.title
            binding.textViewMovieName.setCollapsedTitleTextColor(resources.getColor(R.color.white))
            binding.textViewMovieName.setExpandedTitleColor(resources.getColor(R.color.white))
            textViewReleaseDate.text = "Release Date : ${movie.releaseDate}"
            textViewRating.text = "Rating : ${movie.voteAverage}"
            textViewOverview.text = "Overview : ${movie.overview}"
            textViewVote.text = "Vote Average: ${movie.voteAverage}"
            textViewLanguage.text = "Original Language : ${movie.originalLanguage}"

            Glide.with(root.context)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .into(imageViewPoster)

            btnSave.setImageResource(
                if (movie.isFavorite) R.drawable.baseline_favorite else R.drawable.baseline_favorite_border
            )
        }
    }

    private fun addToFavorite(movie: Movie){
        btn_save.setOnClickListener {
            movieViewModel.addToFavorite(movie)
            binding.apply {
                btnSave.setImageResource(
                    if (movie.isFavorite) R.drawable.baseline_favorite else R.drawable.baseline_favorite_border
                )
            }
        }
    }
}