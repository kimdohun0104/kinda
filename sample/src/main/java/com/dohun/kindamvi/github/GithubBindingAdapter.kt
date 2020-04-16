package com.dohun.kindamvi.github

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("users")
fun RecyclerView.bindUsers(users: List<UserModel>) {
    (adapter as UserListAdapter).submitList(users)
}