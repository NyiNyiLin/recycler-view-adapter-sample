package com.nyi.test.differentviewtype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.R
import com.nyi.test.diffCallBackWith
import java.lang.IllegalArgumentException

class DifferentViewTypeAdapter(
    private val onItemClick: (data: MovieDetail) ->Unit
) : ListAdapter<MovieInterface, DifferentViewTypeAdapter.MovieViewHolder>(
    diffCallBackWith(
        areItemTheSame = { item1, item2 ->
            return@diffCallBackWith if(item1 is MovieType && item2 is MovieType){
                item1.id == item2.id
            } else if(item1 is MovieDetail && item2 is MovieDetail) {
                item1.id == item2.id
            } else {
                item1 == item2
            }
        },
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        }
    )
) {

    private val onTextViewTextClicked = { position: Int ->
        onItemClick.invoke(getItem(position) as MovieDetail)
    }

    companion object {
        private const val VIEW_TYPE_MOVIE_TYPE = 1
        private const val VIEW_TYPE_MOVIE_DETAIL = 2
    }

    override fun getItemViewType(position: Int): Int {
        val movie = getItem(position)
        return if(movie is MovieDetail) VIEW_TYPE_MOVIE_DETAIL
        else VIEW_TYPE_MOVIE_TYPE
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(holder is MovieTypeViewHolder) holder.onBind(getItem(position) as MovieType)
        else if(holder is MovieDetailViewHolder) holder.onBind(getItem(position) as MovieDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return if(viewType == VIEW_TYPE_MOVIE_TYPE) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
            MovieTypeViewHolder(itemView)
        } else if (viewType == VIEW_TYPE_MOVIE_DETAIL) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
            MovieDetailViewHolder(itemView, onTextViewTextClicked)
        } else {
            throw IllegalArgumentException()
        }
    }

    open class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class MovieTypeViewHolder(
        itemView: View
    ) : MovieViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_type)

        fun onBind(dataItem : MovieType) {
            textView.text = dataItem.type
        }
    }

    class MovieDetailViewHolder(
        itemView: View,
        private val onTextViewClicked: (position: Int) ->Unit
    ) : MovieViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        init {
            textView.setOnClickListener {
                onTextViewClicked.invoke(adapterPosition)
            }
        }

        fun onBind(dataItem : MovieDetail) {
            textView.text = dataItem.name
        }
    }
}
