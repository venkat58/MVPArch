package com.infotech.mvparch.view

import com.infotech.mvparch.model.ToDo

interface MVCModifyListActivityView : MVCView {
        fun bindDataToView()
        fun showSelectedItem(todoList :  ToDo)
        fun updateViewOnRemove()
        fun updateViewOnUpdate(todoList : ToDo)
        fun showErrorOnToast()
}