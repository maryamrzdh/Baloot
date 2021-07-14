package com.example.baloot_maryammemarzadeh.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baloot_maryammemarzadeh.R
import com.example.baloot_maryammemarzadeh.model.Article


class RecyclerAdapter(private var itemsList: ArrayList<Article>, private val cellClickListener: ClickListener) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>()  {

    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_items, parent, false)){
        private var mTitleView: TextView? = null
        private var mAuthorView: TextView? = null
        private var mDateView: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.list_title)
            mAuthorView = itemView.findViewById(R.id.list_author)
            mDateView = itemView.findViewById(R.id.list_publishedAt)
        }

        fun bind(item: Article ) {
            mTitleView?.text = item.title
            mAuthorView?.text = item.author
            mDateView?.text = item.publishedAt

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item: Article = itemsList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onItemClick(position)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemsList.size

    interface ClickListener {
        fun onItemClick(id: Int)
    }
}

