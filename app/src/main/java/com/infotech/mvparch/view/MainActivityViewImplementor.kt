package com.infotech.mvparch.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.infotech.mvparch.ModifyListActivity
import com.infotech.mvparch.R
import com.infotech.mvparch.adapters.ListItemClickListener
import com.infotech.mvparch.adapters.ToDoListAdapter
import com.infotech.mvparch.db.ToDoListDBAdapter
import com.infotech.mvparch.model.MVCModelImplementor
import com.infotech.mvparch.model.ToDo
import com.infotech.mvparch.presenter.MVCController


class MainActivityViewImplementor (val context: Context , viewGroup: ViewGroup?):
    MVCMainActivityView , MVCRecyclerItemView.ListItemListener{

    private var mainView : View
    private var controller : MVCController
    private var model : MVCModelImplementor
    private lateinit var editTextNewToDoString : EditText
    private lateinit var editTextPlace : EditText
    private lateinit var textViewToDos : RecyclerView
    private lateinit var addTodo : Button

    init {
        mainView =LayoutInflater.from(context).inflate(R.layout.activity_fullscreen, viewGroup)
        model = MVCModelImplementor(ToDoListDBAdapter(context))
        controller  = MVCController(model, this)
    }

    override fun bindDataToView() {
        controller.onViewLoaded()
    }

    override fun showAllTODOLIST(todoList : ArrayList<ToDo>) {
        textViewToDos.adapter = ToDoListAdapter(context , todoList , this)
        clearFields()
    }

    override fun updateViewOnAdd(todoList : ArrayList<ToDo>) {
       showAllTODOLIST(todoList)
        clearFields()
    }

    override fun showErrorOnToast() {
       Toast.makeText( context, "hi " , Toast.LENGTH_SHORT).show()
    }

    override fun getRootViewOfActivity(): View {
        return mainView
    }

    override fun initViews() {
        editTextNewToDoString = getRootViewOfActivity().findViewById(R.id.editTextNewToDoString)
        textViewToDos = getRootViewOfActivity().findViewById(R.id.textViewToDos)

        textViewToDos.layoutManager = LinearLayoutManager(context)

        editTextPlace = getRootViewOfActivity().findViewById(R.id.editTextPlace)
        addTodo = getRootViewOfActivity().findViewById(R.id.buttonAddToDo)
        addTodo.setOnClickListener({
            controller.onAddClick(editTextNewToDoString.text.toString() , editTextPlace.text.toString())
        })

        getRootViewOfActivity().findViewById<Button>(R.id.removeall).setOnClickListener({
            controller.removeAll()
        })

    }

    fun clearFields()
    {
        editTextNewToDoString.setText("")
        editTextPlace.setText("")
    }

    override fun onItemClicked(position: Long) {
        val modify = Intent( mainView.context , ModifyListActivity::class.java )
        modify.putExtra("id" , position)
        mainView.context.startActivity(modify)
    }
}

