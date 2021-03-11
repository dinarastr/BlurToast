package com.example.blurtoast

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.blurtoast.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur


class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)





        vb.hello.setOnClickListener {
            //CustomSnackbar(view, window.decorView, this, R.color.transparent_green).show("Place successfully added on pinboard")
          showToast("Dinara", R.color.transparent_green, window.decorView)
        }
    }

    fun showToast(message: String, color: Int, decorView: View) {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_snackbar, null)

        val toast = Toast(this)
        val toastMessage = view.findViewById<TextView>(R.id.textMessage)
        toastMessage.text = message


        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)
        val windowBackground = decorView.background

        val blurView = view.findViewById<BlurView>(R.id.blurview)
        blurView.backgroundTintList = ContextCompat.getColorStateList(this, color)
        blurView.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(3f)
            .setBlurAutoUpdate(true)
            //.setOverlayColor(ContextCompat.getColor(this, color))
            .setHasFixedTransformationMatrix(true)


        toast.setGravity(
            Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0,
            8
        )

        toast.view = view
        toast.show()
    }
}