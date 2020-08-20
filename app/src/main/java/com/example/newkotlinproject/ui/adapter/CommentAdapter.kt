package com.example.newkotlinproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newkotlinproject.R
import com.example.newkotlinproject.data.model.Comment
import com.example.newkotlinproject.viewmodel.WaifuDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_recycler_line.view.*

class CommentAdapter(private val comments:ArrayList<Comment>, val viewModel: WaifuDetailsViewModel) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.CommentViewHolder {
        return CommentViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_recycler_line, parent, false))
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        var comment = comments[position]

        holder.commentUser.text = comment.author

        holder.commentPhoto.setImageBitmap(viewModel.getPhotoByUsername(comment.author))

        holder.commentContent.text = comment.content
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var commentUser = itemView.commentUserName
        var commentPhoto = itemView.commentUserPhoto
        var commentContent = itemView.commentContent
    }
}