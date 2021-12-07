package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.date.text = data.date

        val imageBytes = Base64.decode(data.image, 0)
        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        holder.image.setImageBitmap(imageBitmap)
    }

}