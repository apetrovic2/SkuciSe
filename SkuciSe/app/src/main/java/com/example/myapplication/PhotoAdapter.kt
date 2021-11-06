package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(var context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    var dataList = emptyList<DataModel>()

    internal fun setDataList(dataList:List<DataModel>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView
        var title: TextView
        var desc : TextView

        init{
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount() =  dataList.size
    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        var data = dataList[position]
        holder.title.text = data.title
        holder.desc.text = data.desc
        holder.image.setImageResource(data.image)
    }

}