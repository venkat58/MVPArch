package com.infotech.mvparch.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.infotech.mvparch.R
import com.infotech.mvparch.db.ToDoListDBAdapter
import com.infotech.mvparch.model.MVCModelImplementor
import com.infotech.mvparch.model.ToDo
import com.infotech.mvparch.presenter.MVCController


class MainActivityViewImplementor (context: Context , viewGroup: ViewGroup?):
    MVCMainActivityView {

    private var mainView : View
    private var controller : MVCController
    private var model : MVCModelImplementor
    private lateinit var editTextNewToDoString : EditText
    private lateinit var editTextPlace : EditText
    private lateinit var editTextToDoId : EditText
    private lateinit var editTextNewToDo : EditText
    private lateinit var textViewToDos : TextView
    private lateinit var addTodo : Button
    private lateinit var modifyTodo : Button
    private lateinit var removeTodo : Button
    private var context : Context

    init {
        mainView =LayoutInflater.from(context).inflate(R.layout.activity_fullscreen, viewGroup)
        model = MVCModelImplementor(ToDoListDBAdapter(context))
        controller  = MVCController(model, this)
        this.context = context
    }

    override fun bindDataToView() {
        controller.onViewLoaded()
    }

    override fun showAllTODOLIST(todoList : ArrayList<ToDo>) {
        textViewToDos.setText(todoList.toString())
        clearFields()
    }

    override fun updateViewOnAdd(todoList : ArrayList<ToDo>) {
       showAllTODOLIST(todoList)
        clearFields()
    }

    override fun updateViewOnRemove(todoList : ArrayList<ToDo>) {
        showAllTODOLIST(todoList)
        clearFields()
    }

    override fun updateViewOnUpdate(todoList : ArrayList<ToDo>) {
        showAllTODOLIST(todoList)
        clearFields()
    }

    override fun showErrorOnToast() {
       Toast.makeText( context, "hi " , Toast.LENGTH_SHORT).show()
    }

    override fun getRootView(): View {
        return mainView
    }

    override fun initViews() {
        editTextToDoId = getRootView().findViewById(R.id.editTextToDoId)
        editTextNewToDoString = getRootView().findViewById(R.id.editTextNewToDoString)
        editTextNewToDo = getRootView().findViewById(R.id.editTextNewToDo)
        textViewToDos = getRootView().findViewById(R.id.textViewToDos)
        editTextPlace = getRootView().findViewById(R.id.editTextPlace)
        addTodo = getRootView().findViewById(R.id.buttonAddToDo)
        modifyTodo = getRootView().findViewById(R.id.buttonModifyToDo)
        removeTodo = getRootView().findViewById(R.id.buttonRemoveToDo)
        addTodo.setOnClickListener({
            controller.onAddClick(editTextNewToDoString.text.toString() , editTextPlace.text.toString())
        })
        modifyTodo.setOnClickListener({
            controller.onModifyClick(Integer.valueOf(editTextToDoId.text.toString()) , editTextNewToDo.text.toString() )
        })
        removeTodo.setOnClickListener({
            controller.onRemoeClick(Integer.valueOf(editTextToDoId.text.toString()))
        })
    }

    fun clearFields()
    {
        editTextToDoId.setText("")
        editTextNewToDo.setText("")
        editTextNewToDoString.setText("")
        editTextPlace.setText("")
    }
}

