package com.example.paymob_movie_task.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymob_movie_task.databinding.ActivityMainBinding
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.presentation.adapter.MovieListAdapter
import com.example.paymob_movie_task.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

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
        movieViewModel.getAllMovies(2024,"vote_average.desc")

        movieViewModel.movies.observe(this){
            if (it!!.isNotEmpty()){
                getUniversityAdapter()?.updateData(it)
            }        }
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

        Log.d(TAG, "onFavoriteClick: "+movie)
    }


    private fun getUniversityAdapter() =
        binding.rvMovie.adapter as? MovieListAdapter

}