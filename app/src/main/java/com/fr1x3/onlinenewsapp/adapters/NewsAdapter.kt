package com.fr1x3.onlinenewsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fr1x3.onlinenewsapp.BR
import com.fr1x3.onlinenewsapp.adapters.NewsAdapter.ArticleViewHolder
import com.fr1x3.onlinenewsapp.databinding.NewsItemBinding
import com.fr1x3.onlinenewsapp.model.Article

class NewsAdapter: RecyclerView.Adapter<ArticleViewHolder>() {

    inner class ArticleViewHolder( val itemBinding: NewsItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind( data: Any){
            itemBinding.setVariable(BR.article, data)
            itemBinding.executePendingBindings()
        }
    }

    val differCallback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =  NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.bind(article)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}