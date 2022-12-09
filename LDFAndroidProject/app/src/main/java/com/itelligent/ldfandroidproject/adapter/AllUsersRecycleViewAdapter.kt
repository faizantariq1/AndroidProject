package com.itelligent.ldfandroidproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itelligent.ldfandroidproject.data.model.UserResponse
import com.itelligent.ldfandroidproject.databinding.ItemUsersListBinding
import javax.inject.Inject

class AllUsersRecycleViewAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<AllUsersRecycleViewAdapter.ViewHolder>(){

    private var usersList: ArrayList<UserResponse> = arrayListOf()
    var onItemClick: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUsersListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(usersList[position], context, position)

    override fun getItemCount(): Int = usersList.size

    fun updateList(usersList: ArrayList<UserResponse>){
        this.usersList.addAll(usersList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(user: UserResponse, context: Context, position: Int) {
            binding.apply {
                userName.text = user.login
                Glide.with(context)
                    .load(user.avatar_url)
                    .fitCenter()
                    .into(userIv)
                userName.setOnClickListener {
                    onItemClick?.invoke(position)
                }
            }
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClick?.invoke(position)
        }
    }
}