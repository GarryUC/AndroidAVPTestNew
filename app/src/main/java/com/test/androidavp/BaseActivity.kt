package com.test.androidavp

import androidx.appcompat.app.AppCompatActivity
import com.test.androidavp.utility.DialogProgress

open class BaseActivity : AppCompatActivity() {
    private var dialogProgress: DialogProgress? = null

    fun showProgressDialog() {
        if (dialogProgress != null && dialogProgress!!.isShowing)
            dialogProgress!!.dismiss()
        dialogProgress = DialogProgress(this)
        dialogProgress!!.show()
    }

    fun hideProgressDialog() {
        if (dialogProgress != null && dialogProgress!!.isShowing)
            dialogProgress!!.dismiss()
    }
}