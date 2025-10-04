package com.example.sis2assignment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postText: TextView = itemView.findViewById(R.id.postText)
        val postImage: ImageView = itemView.findViewById(R.id.postImage)

        val likeButton: Button = itemView.findViewById(R.id.likeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {//position показывает какой айтем щас будет
        val post = posts[position]
        holder.postText.text = post.text

        Glide.with(holder.itemView.context)
            .load(post.imageUrl)
            .into(holder.postImage)

        holder.likeButton.text = if (post.liked) "Unlike" else "Like"

        holder.likeButton.setOnClickListener {
            post.liked = !post.liked
            notifyItemChanged(position) //обновить кнопку
        }
    }

    override fun getItemCount() = posts.size
}
