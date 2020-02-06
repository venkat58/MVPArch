package com.infotech.mvparch.view

import android.view.View

interface MVCRecyclerItemView <T>{

    interface ListItemListener
    {
        fun onItemClicked(position : Long)
    }
    fun setListItemListener(listener: ListItemListener)
    fun getMainView() : View
    fun bindDataToView(selectedItem: T)
    fun initViews()
}