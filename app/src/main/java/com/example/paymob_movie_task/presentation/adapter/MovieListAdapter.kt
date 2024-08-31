package com.example.paymob_movie_task.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paymob_movie_task.R
import com.example.paymob_movie_task.databinding.MovieItemBinding
import com.example.paymob_movie_task.domain.model.Movie
import kotlin.properties.Delegates

class MovieListAdapter(
    list: List<Movie> = emptyList(),
    private val onItemClick: (Movie) -> Unit,
    private val onFavoriteClick: (Movie) -> Unit

) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var list: List<Movie> by Delegates.observable(list) { _, old, new ->
        DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize() = old.size

                override fun getNewListSize() = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition].id == new[newItemPosition].id

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition] == new[newItemPosition]

            }
        ).also { result ->
            result.dispatchUpdatesTo(this@MovieListAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onItemClick,onFavoriteClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Movie>) {
        Log.d("TAG", "updateData: "+newList)
        list = newList
    }

    class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, onItemClick: (Movie) -> Unit, onFavoriteClick: (Movie) -> Unit) {
            binding.apply {
                textViewMovieName.text = movie.title
                textViewReleaseDate.text = movie.releaseDate
                textViewRating.text = movie.voteAverage.toString()

                Glide.with(root.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .into(imageViewPoster)

                imageViewFavoriteIcon.setImageResource(
                    if (movie.isFavorite) R.drawable.baseline_favorite else R.drawable.baseline_favorite_border
                )

                imageViewFavoriteIcon.setOnClickListener {
                    onFavoriteClick(movie)
                }
                root.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }
}
