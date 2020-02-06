package com.infotech.mvparch.adapters

import androidx.recyclerview.widget.RecyclerView
import com.infotech.mvparch.view.MVCRecyclerItemViewImplementation

class ToDoViewHolder(itemView: MVCRecyclerItemViewImplementation) :
    RecyclerView.ViewHolder(itemView.getMainView())
{
    var mainView:MVCRecyclerItemViewImplementation

    init {
        this.mainView = itemView
    }
}