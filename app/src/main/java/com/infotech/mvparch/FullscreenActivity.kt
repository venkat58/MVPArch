package com.infotech.mvparch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infotech.mvparch.view.MainActivityViewImplementor

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * after this example we have to check this  https://github.com/ankitbisht/trending-git.git
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
        setContentView(mvcView.getRootViewOfActivity())
        mvcView.initViews()
    }


    override fun onResume() {
        super.onResume()
        mvcView.bindDataToView()
    }
}
