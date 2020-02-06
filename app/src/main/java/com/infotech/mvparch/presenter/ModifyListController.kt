package com.infotech.mvparch.presenter

import com.infotech.mvparch.model.MVCModelImplementor
import com.infotech.mvparch.view.ModifyListActivityImplementation

class ModifyListController(mvcModelImplementor: MVCModelImplementor, mvcViewImplementor: ModifyListActivityImplementation)  {

    private var mvcModel : MVCModelImplementor
    private var mvcView : ModifyListActivityImplementation
    init {
        mvcModel = mvcModelImplementor
        mvcView = mvcViewImplementor
    }

    fun onViewLoaded()
    {
        mvcView.showSelectedItem(mvcModel.getSelectedTODOItem(mvcView.getSelectedId()))
    }

    fun onRemoeClick(id : Int)
    {
        val result =  mvcModel.removeTODOItem(id)
        if (result)
            mvcView.updateViewOnRemove()
        else mvcView.showErrorOnToast()
    }
    fun onModifyClick(id : Int , newTodoItem : String)
    {
        val result =  mvcModel.modifyTODOItem(id , newTodoItem)
        if (result) mvcView.updateViewOnUpdate(mvcModel.getSelectedTODOItem(id))
        else mvcView.showErrorOnToast()
    }
}