package com.example.blurtoast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.content.ContextCompat
import com.example.blurtoast.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import eightbitlab.com.blurview.RenderScriptBlur

class CustomSnackbar(private val view: View, val decorView: View, val context: Context, val color: Int) {

    val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)
    val windowBackground = decorView.background


    fun show(title: String) {
        val snackView = View.inflate(view.context, R.layout.custom_snackbar, null)
        val binding = CustomSnackbarBinding.bind(snackView)
        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        (snackbar.view as ViewGroup).removeAllViews()
        (snackbar.view as ViewGroup).addView(binding.root)
        snackbar.view.setPadding(0, 0, 0, 0)
        snackbar.view.clipToOutline = true
        snackbar.view.elevation = 0f
        snackbar.setBackgroundTint(ContextCompat.getColor(context, color))
        binding.blurview.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(context))
            .setBlurRadius(3f)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.textMessage.text = title
        snackbar.show()
    }
}