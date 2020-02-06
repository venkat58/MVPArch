package com.infotech.mvparch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import android.widget.TextView
import com.infotech.mvparch.R
import com.infotech.mvparch.model.ToDo

class MVCRecyclerItemViewImplementation(layoutInflater: LayoutInflater, parent: ViewGroup) : MVCRecyclerItemView<ToDo>{

    private var mainView: View
    lateinit var todoItem : ToDo
    lateinit var id : TextView
    lateinit var name : TextView
    lateinit var place : TextView
    lateinit var container : LinearLayout
    lateinit var listener : MVCRecyclerItemView.ListItemListener

    init {
        mainView = layoutInflater.inflate(R.layout.todo_itemview , parent , false)
    }

    override fun setListItemListener(listener: MVCRecyclerItemView.ListItemListener) {
        this.listener = listener
    }

    override fun getMainView(): View {
        return mainView
    }

    override fun initViews() {
         id = mainView.findViewById(R.id.id)
         name = mainView.findViewById(R.id.name)
         place = mainView.findViewById(R.id.place)
         container = mainView.findViewById(R.id.container)
    }

    override fun bindDataToView(selectedItem: ToDo) {
        this.todoItem = selectedItem
        id.text = selectedItem.id.toString()
        name.text = selectedItem.toDo
        place.text = selectedItem.place
        container.id = selectedItem.id.toInt()
        container.setOnClickListener({
                listener.onItemClicked(it.id.toLong())
        })

    }
}