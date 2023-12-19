package com.example.davaleba_18.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.davaleba_18.data.User
import com.example.davaleba_18.databinding.ItemUserBinding

class UsersAdapter : PagingDataAdapter<User, UsersAdapter.UserViewHolder>(UserComparator) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    object UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }


    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
            user?.let {
                with(binding) {
                    tvFirstName.text = it.firstName
                    tvLastName.text = it.lastName
                    tvEmail.text = it.email

                    Glide.with(root.context)
                        .load(it.avatar)
                        .into(ivImage)
                }
            }
        }
    }


}





