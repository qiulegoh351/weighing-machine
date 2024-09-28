package com.farmtech.weighingmachine

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.farmtech.weighingmachine.ui.App


class MainActivity : ComponentActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appContext: Activity
            private set // Make the setter private to avoid external modification
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        enableEdgeToEdge(
        //            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        //        )
        // Initialize the context
        appContext = this
        AuthRepository.init(this)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { App() }
    }

    /**
     * Override Touch Event To Clear Focus and Close The Soft Keyboard After Clicking Outside EditText
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                // Clear focus of the current view
                view.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}