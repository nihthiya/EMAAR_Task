package com.example.emaartask.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emaartask.R
import com.example.emaartask.database.model.UserModel
import com.example.emaartask.databinding.ItemUserListBinding
import com.example.emaartask.ui.userDetails.activity.UserDetailsActivity
import com.example.emaartask.utils.Utils

class UserListAdapter() : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    var itemList: MutableList<UserModel> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    private lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user_list,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        try {
            Glide.with(context).load(item.avatar).into(holder.itemUserListBinding.ivAvatar)


            holder.itemUserListBinding.tvName.text = item.name
            holder.itemUserListBinding.tvDate.text = Utils.formatDate(item.date)
            holder.itemUserListBinding.tvEmail.text = item.email
            holder.itemUserListBinding.tvCountry.text = item.country

        } catch (e: Exception) {

        }


        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("userId", item.userUUID)
            context.startActivity(intent)
        }

    }


    inner class ViewHolder(
        val itemUserListBinding: ItemUserListBinding
    ) : RecyclerView.ViewHolder(itemUserListBinding.root)

}