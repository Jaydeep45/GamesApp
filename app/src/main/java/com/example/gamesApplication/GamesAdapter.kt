package com.example.gamesApplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener: NewItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<Games> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            val viewHolder = NewsViewHolder(view)
            view.setOnClickListener{
                listener.onItemClicked(items[viewHolder.adapterPosition])
            }
            return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateNews(updateItem: ArrayList<Games>) {
        items.clear()
        items.addAll(updateItem)

        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)

}
interface NewItemClicked {
    fun onItemClicked(item: Games)
}