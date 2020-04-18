package com.dohun.kindamvi.github.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dohun.kindamvi.data.model.UserModel
import com.dohun.kindamvi.github.adapter.UserListAdapter

@BindingAdapter("users")
fun RecyclerView.bindUsers(users: List<UserModel>) {
    (adapter as UserListAdapter).submitList(users)
}