package com.dohun.kindamvi.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dohun.kindamvi.databinding.ItemUserBinding

class UserListAdapter : ListAdapter<UserModel, UserListAdapter.UserViewHolder>(UserModel.DIFF_CALLBACK) {

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.user = getItem(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind()
}