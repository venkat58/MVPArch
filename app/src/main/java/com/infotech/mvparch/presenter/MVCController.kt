package com.infotech.mvparch.presenter

import com.infotech.mvparch.view.MainActivityViewImplementor
import com.infotech.mvparch.model.MVCModelImplementor

class MVCController(mvcModelImplementor: MVCModelImplementor, mvcViewImplementor: MainActivityViewImplementor) {

    private var mvcModel : MVCModelImplementor
    private var mvcView : MainActivityViewImplementor
    init {
        mvcModel = mvcModelImplementor
        mvcView = mvcViewImplementor
    }

    fun onViewLoaded()
    {
        mvcView.showAllTODOLIST(ArrayList(mvcModel.getAllTODOS()))
    }

    fun onAddClick(todoItem : String , place : String)
    {
        val result = mvcModel.addTODOItem(todoItem , place)

        if (result)
            mvcView.updateViewOnAdd(mvcModel.getAllTODOS())
        else mvcView.showErrorOnToast()
    }
    fun onRemoeClick(id : Int)
    {
        val result =  mvcModel.removeTODOItem(id)
        if (result)
            mvcView.updateViewOnRemove(mvcModel.getAllTODOS())
        else mvcView.showErrorOnToast()
    }
    fun onModifyClick(id : Int , newTodoItem : String)
    {
        val result =  mvcModel.modifyTODOItem(id , newTodoItem)
        if (result) mvcView.updateViewOnUpdate(mvcModel.getAllTODOS())
        else mvcView.showErrorOnToast()
    }
}