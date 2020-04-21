package com.dohun.kindamvi.github.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dohun.kindamvi.data.model.UserModel
import com.dohun.kindamvi.github.adapter.UserListAdapter

@BindingAdapter("users")
fun RecyclerView.bindUsers(users: List<UserModel>) {
    (adapter as UserListAdapter).submitList(users)
}

@BindingAdapter("avatarUrl")
fun ImageView.bindAvatarUrl(avatarUrl: String) {
    Glide.with(context).load(avatarUrl).into(this)
}
