package com.infotech.mvparch.model

interface MVCModel {
    fun getAllTODOS() : List<ToDo>
    fun getSelectedTODOItem(id : Int) : ToDo
    fun addTODOItem(toDoItem: String , place: String): Boolean
    fun removeTODOItem(id: Int) : Boolean
    fun modifyTODOItem(id: Int , newTodoItem: String): Boolean
    fun removeAll() : Boolean
}