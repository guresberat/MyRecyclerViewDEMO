package com.guresberat.myrecyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guresberat.myrecyclerviewdemo.models.BlogPost


class BlogRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<BlogPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BlogViewHolder ->{
                holder.bind(items[position])
                holder.cardView.animation =
                    AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_in)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<BlogPost>){
        items = blogList
    }

    class BlogViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val blogImage = itemView.findViewById<ImageView>(R.id.blog_image)
        val blogTitle = itemView.findViewById<TextView>(R.id.blog_title)
        val blogAuthor = itemView.findViewById<TextView>(R.id.blog_author)
        val cardView = itemView.findViewById<CardView>(R.id.card_view)

        fun bind(blogPost: BlogPost) {
            blogTitle.text = blogPost.title
            blogAuthor.text = blogPost.username

            val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions).load(blogPost.image).into(blogImage)

        }
    }
}