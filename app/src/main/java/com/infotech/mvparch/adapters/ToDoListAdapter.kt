package com.infotech.mvparch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.infotech.mvparch.R
import com.infotech.mvparch.model.ToDo
import com.infotech.mvparch.view.MVCRecyclerItemView
import com.infotech.mvparch.view.MVCRecyclerItemViewImplementation
import com.infotech.mvparch.view.MainActivityViewImplementor

class ToDoListAdapter(context: Context , todoList: List<ToDo> ,val listenr: MVCRecyclerItemView.ListItemListener) :
    RecyclerView.Adapter<ToDoViewHolder>() , MVCRecyclerItemView.ListItemListener {

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    private var context:Context
    private var todoList: List<ToDo>


    init {
        this.context=context
        this.todoList = todoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView =MVCRecyclerItemViewImplementation(LayoutInflater.from(context) , parent)
        itemView.initViews()
        itemView.setListItemListener(this)
        return ToDoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todoList.count()
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.todo_itemview
    }
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = todoList.get(position)
        holder.mainView.bindDataToView(toDo)
    }

    override fun onItemClicked(position: Long) {
        listenr.onItemClicked(position)
    }

}