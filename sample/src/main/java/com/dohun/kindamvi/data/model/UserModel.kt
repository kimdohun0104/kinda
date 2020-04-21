package com.dohun.kindamvi.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class UserModel(

    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    val id: Int
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.login == newItem.login && oldItem.avatarUrl == newItem.avatarUrl

        }
    }
}