package com.khan.galleryai

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private var list: List<Uri>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    internal fun updateList(list: List<Uri>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindImage(list[position])
    }

    class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView? by lazy {
            itemView.findViewById(R.id.iv_item) as ImageView?
        }

        internal fun bindImage(uri: Uri) {
            Glide.with(itemView)
                .load(uri)
                .into(imageView!!)
        }
    }
}
