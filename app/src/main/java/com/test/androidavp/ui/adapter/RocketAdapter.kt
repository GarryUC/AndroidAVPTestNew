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
import com.test.androidavp.dto.RocketListDto
import com.test.androidavp.roomdatabase.entity.RocketEntity
import com.test.androidavp.R

class RocketAdapter (
    private val activity: Activity,
    private var list: ArrayList<RocketListDto>,
    private var rocketDBList: ArrayList<RocketEntity>,
    private var rocketInterface: RocketInterface
) : RecyclerView.Adapter<RocketAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_rocket_list, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        if(rocketDBList.isEmpty()) {
            val item = list[position]
            try {
                viewHolder.tvName.text = item.name
                viewHolder.tvCountry.text = item.country
                viewHolder.tvEngineCount.text = "${activity.getString(R.string.engine_count)}: ${item.firstStage?.engines.toString()}"

                viewHolder.itemView.setOnClickListener {
                    rocketInterface.onClick(position)
                }

                if (item.flickrImages?.get(0)!!.isNotEmpty()) {
                    Glide.with(activity)
                        .load(item.flickrImages.get(0) ?: "")
                        .into(viewHolder.ivImage)
                }

            } catch (e: Exception) {
                Log.e("exception ", e.toString())
            }
        } else {
            val item = rocketDBList[position]
            try {
                viewHolder.tvName.text = item.name
                viewHolder.tvCountry.text = item.country
                viewHolder.tvEngineCount.text = item.engineCount.toString()

                viewHolder.itemView.setOnClickListener {
                    rocketInterface.onClick(position)
                }

                if (item.image.isNotEmpty()) {
                    Glide.with(activity)
                        .load(item.image)
                        .into(viewHolder.ivImage)
                }

            } catch (e: Exception) {
                Log.e("exception ", e.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return if(rocketDBList.isEmpty())
            list.size
        else
            rocketDBList.size
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tvName)
        var ivImage: ImageView = view.findViewById(R.id.ivImage)
        var tvCountry: TextView = view.findViewById(R.id.tvCountry)
        var tvEngineCount: TextView = view.findViewById(R.id.tvEngineCount)

    }

    interface RocketInterface {
        fun onClick(position: Int)
    }
}