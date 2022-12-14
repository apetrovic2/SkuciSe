package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(var context: Context) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    var dataList = emptyList<DataModelNotifications>()

    internal fun setDataList(dataList: List<DataModelNotifications>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
        var username: TextView = itemView.findViewById(R.id.username)
        var date: TextView = itemView.findViewById(R.id.date)
        var details: Button = itemView.findViewById(R.id.btnDetails)

        //        init {
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, AdInfo::class.java)
//                itemView.context.startActivity(intent)
//            }
//        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_layout_notifications, parent, false)
        return NotificationAdapter.ViewHolder(view)
    }

    override fun getItemCount() = dataList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]
        holder.title.text = data.title
        holder.username.text = data.username
        holder.date.text = data.date?.take(10) + "  " + data.date?.takeLast(5) + "h"
        if(data.ind == 0)
        {
            holder.details.visibility = View.GONE
            holder.details.isClickable = false
        }
        else {
            holder.details.setOnClickListener() {
                val intent = Intent(context, ReservationAcceptDecline::class.java)
                intent.putExtra("id", data.id)
                intent.putExtra("title", data.title)
                intent.putExtra("image", data.image)
                intent.putExtra("username", data.username)
                intent.putExtra("name", data.name)
                intent.putExtra("email", data.email)
                intent.putExtra("date", data.date)
                intent.putExtra("approved", data.approved)
                context.startActivity(intent)
            }
        }
        val imageBytes = Base64.decode(data.image, 0)
        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        holder.image.setImageBitmap(imageBitmap)
    }

}