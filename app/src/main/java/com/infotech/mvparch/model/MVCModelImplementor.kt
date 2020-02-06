package com.infotech.mvparch.model

import com.infotech.mvparch.db.ToDoListDBAdapter

class MVCModelImplementor(toDoListDBAdapter: ToDoListDBAdapter) : MVCModel {

    private var todoListDBAdapter : ToDoListDBAdapter
    private var listTodo : ArrayList<ToDo>

    init {
        todoListDBAdapter = toDoListDBAdapter
        listTodo = ArrayList(todoListDBAdapter.getAllToDos())
    }

    fun refresh()
    {
        listTodo.clear()
        listTodo = ArrayList(todoListDBAdapter.getAllToDos())
    }
    override fun getAllTODOS() : ArrayList<ToDo> {
        return listTodo
    }

    override fun getSelectedTODOItem(id: Int): ToDo {
        return todoListDBAdapter.getSelectedToDo(id)
    }

    override fun addTODOItem(toDoItem: String, place: String): Boolean {
        val isSuccess = todoListDBAdapter.insert(toDoItem , place)
        if (isSuccess) refresh()

        return isSuccess
    }

    override fun removeTODOItem(id: Int): Boolean {
        val isSuccess = todoListDBAdapter.delete(id)
        if (isSuccess) refresh()
        return isSuccess
    }

    override fun modifyTODOItem(id: Int, newTodoItem: String): Boolean {
        val isSuccess = todoListDBAdapter.modify(id , newTodoItem)
        if (isSuccess) refresh()
        return isSuccess
    }

    override fun removeAll() : Boolean{
        val isSuccess = todoListDBAdapter.deleteAll()
        if (isSuccess) refresh()

        return isSuccess

    }


}