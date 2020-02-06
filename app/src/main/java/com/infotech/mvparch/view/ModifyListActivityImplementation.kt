package com.infotech.mvparch.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.infotech.mvparch.R
import com.infotech.mvparch.db.ToDoListDBAdapter
import com.infotech.mvparch.model.MVCModelImplementor
import com.infotech.mvparch.model.ToDo
import com.infotech.mvparch.presenter.MVCController
import com.infotech.mvparch.presenter.ModifyListController

class ModifyListActivityImplementation(context: Context , viewGroup: ViewGroup?) : MVCModifyListActivityView{

    private var mainView : View
    private var controller : ModifyListController
    private var model : MVCModelImplementor
    private lateinit var editTextNewToDo : EditText
    private lateinit var lisOfItems : TextView
    private lateinit var modifyTodo : Button
    private lateinit var removeTodo : Button
    private var context : Context
    private var selectedId : Int = 0

    init {
        mainView = LayoutInflater.from(context).inflate(R.layout.activity_modify_list, viewGroup)
        model = MVCModelImplementor(ToDoListDBAdapter(context))
        controller  = ModifyListController(model, this)
        this.context = context
    }

    override fun bindDataToView() {
        controller.onViewLoaded()
    }

    override fun showSelectedItem(todoList: ToDo) {
        lisOfItems.setText(todoList.toString())
        clearFields()
    }

    override fun updateViewOnRemove() {
        clearFields()
    }

    override fun updateViewOnUpdate(todoList :  ToDo) {
        showSelectedItem(todoList)
        clearFields()
    }

    override fun showErrorOnToast() {
        Toast.makeText( context, "hi " , Toast.LENGTH_SHORT).show()
    }

    override fun getRootViewOfActivity(): View {
        return mainView
    }

    override fun initViews() {
        selectedId = getRootViewOfActivity().id
        editTextNewToDo = getRootViewOfActivity().findViewById(R.id.editTextNewToDo)
        lisOfItems = getRootViewOfActivity().findViewById(R.id.listOfItems)
        modifyTodo = getRootViewOfActivity().findViewById(R.id.buttonModifyToDo)
        removeTodo = getRootViewOfActivity().findViewById(R.id.buttonRemoveToDo)

        modifyTodo.setOnClickListener({
            controller.onModifyClick(selectedId , editTextNewToDo.text.toString() )
        })
        removeTodo.setOnClickListener({
            controller.onRemoeClick(selectedId)
        })
    }

    fun clearFields()
    {
        editTextNewToDo.setText("")
    }
    fun getSelectedId() : Int{
        return selectedId
    }
}