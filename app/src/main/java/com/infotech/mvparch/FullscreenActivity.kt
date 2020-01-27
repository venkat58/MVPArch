package com.infotech.mvparch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infotech.mvparch.view.MainActivityViewImplementor

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity()  {

    /*@BindView(R.id.mEdittext)
    lateinit var mEditText: AppCompatEditText*/

    lateinit var mvcView : MainActivityViewImplementor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_fullscreen)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mvcView = MainActivityViewImplementor(this , null)
        setContentView(mvcView.getRootView())
        mvcView.initViews()
    }


    override fun onResume() {
        super.onResume()
        mvcView.bindDataToView()
    }
}
