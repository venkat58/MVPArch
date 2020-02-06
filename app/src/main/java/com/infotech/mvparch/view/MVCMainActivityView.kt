package com.infotech.mvparch.view

import com.infotech.mvparch.model.ToDo
import com.infotech.mvparch.view.MVCView

interface MVCMainActivityView : MVCView {
    fun bindDataToView()
    fun showAllTODOLIST(todoList : ArrayList<ToDo>)
    fun updateViewOnAdd(todoList : ArrayList<ToDo>)
    fun showErrorOnToast()
}