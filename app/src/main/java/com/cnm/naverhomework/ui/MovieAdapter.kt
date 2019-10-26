package com.cnm.naverhomework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnm.naverhomework.R
import com.cnm.naverhomework.network.model.NaverMovieResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val onClickAction: (NaverMovieResponse.Item) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.CustomViewHolder>() {
    val items = mutableListOf<NaverMovieResponse.Item>()

    fun setItem(item: List<NaverMovieResponse.Item>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.Bind(items[position])
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val item = items[adapterPosition]
                onClickAction(item)
            }
        }

        fun Bind(item: NaverMovieResponse.Item) {
            with(itemView) {
                Glide.with(this)
                    .load(item.image)
                    .into(iv_movie_image)
                tv_movie_name.text = item.title
                rb_movie_rating.rating = item.userRating / 2
                tv_movie_year.text = item.pubDate
                tv_moive_director.text = item.director
                tv_movie_actor.text = item.actor
            }
        }

    }

}