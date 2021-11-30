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

class PhotoAdapter(var context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    var dataList = emptyList<DataModel>()

    internal fun setDataList(dataList:List<DataModel>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
        var desc : TextView = itemView.findViewById(R.id.desc)

//        init {
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, AdInfo::class.java)
//                itemView.context.startActivity(intent)
//            }
//        }

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
        val imageBytes = Base64.decode(data.image, 0)
        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.image.setImageBitmap(imageBitmap)
        holder.image.setOnClickListener(){
            Log.i("AD ID2", "" + data.id)
            //val adInfoClass = AdInfo(data.id)
            if(data.ind == 1)
            {
                val intent = Intent(context, AdInfo::class.java)
                intent.putExtra("id", data.id)
                context.startActivity(intent)
            }
            else
            {
                val intent = Intent(context, AdInfoVisitor::class.java)
                intent.putExtra("id", data.id)
                context.startActivity(intent)
            }

        }
    }

}