package com.infotech.mvparch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.infotech.mvparch.model.MVCModel
import com.infotech.mvparch.view.MainActivityViewImplementor
import com.infotech.mvparch.view.ModifyListActivityImplementation

class ModifyListActivity : AppCompatActivity() {

    lateinit var mvcViewImplementor: ModifyListActivityImplementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mvcViewImplementor = ModifyListActivityImplementation(this , null)
        setContentView(mvcViewImplementor.getRootViewOfActivity())
        mvcViewImplementor.getRootViewOfActivity().id = intent.getLongExtra("id" , 0).toInt()
        mvcViewImplementor.initViews()
    }

    override fun onResume() {
        super.onResume()
        mvcViewImplementor.bindDataToView()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt("id" , mvcViewImplementor.getRootViewOfActivity().id)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        mvcViewImplementor.getRootViewOfActivity().id = savedInstanceState.getInt("id")
        super.onRestoreInstanceState(savedInstanceState)
    }
}
