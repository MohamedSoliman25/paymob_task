package com.example.paymob_movie_task.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymob_movie_task.databinding.ActivityMainBinding
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.presentation.adapter.MovieListAdapter
import com.example.paymob_movie_task.presentation.viewmodel.MovieViewModel
import com.example.paymob_movie_task.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val movieViewModel:MovieViewModel by viewModels<MovieViewModel> ()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getAllMovies(2024,"vote_average.desc")
        observeAllMovies()

    }

    private fun getAllMovies(releaseYear:Int,sortBy:String){
        movieViewModel.getAllMovies(releaseYear,sortBy)
    }
    private fun observeAllMovies(){
        movieViewModel.moviesState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvMovie.visibility = View.GONE
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                    getMovieLisAdapter()?.updateData(state.data)
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.GONE
                    Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initRecyclerView() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieListAdapter(ArrayList(), ::onItemClick,::onFavoriteClick)
        }
    }
    private fun onItemClick(movie: Movie){
        Log.d(TAG, "onItemClick: "+movie)
    }
    private fun onFavoriteClick(movie: Movie){

        movieViewModel.addToFavorite(movie)
        getMovieLisAdapter()?.notifyDataSetChanged()
    }


    private fun getMovieLisAdapter() =
        binding.rvMovie.adapter as? MovieListAdapter

}