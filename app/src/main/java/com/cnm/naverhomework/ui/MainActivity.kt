package com.cnm.naverhomework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.cnm.naverhomework.R
import com.cnm.naverhomework.network.NetworkHelper
import com.cnm.naverhomework.network.model.NaverMovieResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(::showMovieDetail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_content.adapter = movieAdapter

        bt_search.setOnClickListener {
            if (et_movie_name.text != null) {
                val query = et_movie_name.text.toString()
                getMovieList(query)
            }
        }

        et_movie_name.setOnEditorActionListener { textView, i, keyEvent ->
            when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    bt_search.performClick()
                }
            }
            true
        }
    }

    private fun getMovieList(query: String) {
        showLoading()
        NetworkHelper.naverApi.getNaverMovie(query)
            .enqueue(object : Callback<NaverMovieResponse> {
                override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<NaverMovieResponse>,
                    response: Response<NaverMovieResponse>
                ) {
                    hideLoading()
                    val body = response.body()
                    if (body != null) {
                        setMovieList(body.items)
                    }
                }
            })
    }

    private fun setMovieList(items: List<NaverMovieResponse.Item>) {
        movieAdapter.setItem(items)
    }

    private fun showMovieDetail(item: NaverMovieResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    private fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
    }
}
