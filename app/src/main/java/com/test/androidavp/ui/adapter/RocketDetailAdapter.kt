package com.test.androidavp.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.androidavp.R

class RocketDetailAdapter  (
    private val activity: Activity,
    private var list: ArrayList<String>,
) : RecyclerView.Adapter<RocketDetailAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_rocket_detail, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val item = list[position]
        try {
            if(item.isNotEmpty()) {
                viewHolder.tvNoImageFound.visibility = View.GONE
                viewHolder.ivImage.visibility = View.VISIBLE
                Glide.with(activity)
                    .load(item)
                    .into(viewHolder.ivImage)
            } else {
                viewHolder.tvNoImageFound.visibility = View.VISIBLE
                viewHolder.ivImage.visibility = View.GONE
            }

        } catch (e: Exception) {
            Log.e("exception ", e.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivImage: ImageView = view.findViewById(R.id.ivImage)
        var tvNoImageFound: TextView = view.findViewById(R.id.tvNoImageFound)
    }
}