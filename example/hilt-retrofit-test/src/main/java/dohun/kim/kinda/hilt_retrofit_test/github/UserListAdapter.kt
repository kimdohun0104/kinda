package dohun.kim.kinda.hilt_retrofit_test.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dohun.kim.kinda.hilt_retrofit_test.R
import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter : ListAdapter<UserModel, UserListAdapter.UserHolder>(UserModel.DIFF_CALLBACK) {

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val item = getItem(position)
            Glide.with(itemView).load(item.avatarUrl).into(itemView.iv_avatar)
            itemView.tv_login.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder =
        UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bind(position)
}