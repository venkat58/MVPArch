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

    fun removeAll()
    {
        val result = mvcModel.removeAll()
        if (result) mvcView.showAllTODOLIST(ArrayList(mvcModel.getAllTODOS()))
        else mvcView.showErrorOnToast()
    }

}