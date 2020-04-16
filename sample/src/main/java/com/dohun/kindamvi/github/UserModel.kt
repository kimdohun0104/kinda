package com.dohun.kindamvi.github

import androidx.recyclerview.widget.DiffUtil

data class UserModel(

    val login: String,

    val id: Int,

    val type: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.login == newItem.login && oldItem.type == newItem.type

        }
    }
}