package com.chocomiruku.homework10

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.overview.ApiStatus
import com.chocomiruku.homework10.overview.FishAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Fish>?) {
    val adapter = recyclerView.adapter as FishAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatusRecyclerView(recyclerView: RecyclerView, status: ApiStatus?) {
    when (status) {
        ApiStatus.DONE -> {
            recyclerView.visibility = View.VISIBLE
        }
        else -> {
            recyclerView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatusImage(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatusText(statusTextView: TextView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        else -> {
            statusTextView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatusProgressIndicator(
    statusProgressIndicator: CircularProgressIndicator,
    status: ApiStatus?
) {
    when (status) {
        ApiStatus.LOADING -> {
            statusProgressIndicator.show()
        }
        else -> {
            statusProgressIndicator.hide()
        }
    }
}

@BindingAdapter("imageUrl")
fun bindIllustration(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        imgView.visibility = View.VISIBLE
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_anim)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    } ?: run {
        imgView.visibility = View.INVISIBLE
    }
}