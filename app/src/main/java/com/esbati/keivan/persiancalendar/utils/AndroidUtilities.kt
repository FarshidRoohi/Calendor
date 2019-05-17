package com.esbati.keivan.persiancalendar.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.esbati.keivan.persiancalendar.R
import com.esbati.keivan.persiancalendar.components.ApplicationController


private val density = ApplicationController.getContext().resources.displayMetrics.density

fun Context.checkPermissions(vararg permissions: String): Boolean
        = permissions.toList().all {
    ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}
fun AppCompatActivity.shouldShowPermissionsRationale(vararg permissions: String): Boolean
        = permissions.toList().any {
    ActivityCompat.shouldShowRequestPermissionRationale(this, it)
}

fun dp(value: Float): Int {
    return if (value == 0f) 0 else Math.ceil((density * value).toDouble()).toInt()
}

fun Int.toDp(): Int {
    return if (this == 0) 0 else Math.ceil((density * this).toDouble()).toInt()
}

fun Float.toDp(): Int {
    return if (this == 0f) 0 else Math.ceil((density * this).toDouble()).toInt()
}

fun showToast(@StringRes value: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ApplicationController.getContext(), value, duration).show()
}

fun View.showSoftKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideSoftKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun TextView.setTextColorResource(@ColorRes value: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, value))
}

fun AlertDialog.Builder.showThemedDialog(): AlertDialog {
    val dialog = this.create()
    dialog.showThemedDialog()
    return dialog
}

fun AlertDialog.showThemedDialog() {
    show()
    applyDefaultTheme()
}

fun AlertDialog.applyDefaultTheme(): AlertDialog {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
        findViewById<TextView>(R.id.alertTitle)?.layoutDirection = View.LAYOUT_DIRECTION_RTL

    applyFont()
    return this
}

fun AlertDialog.applyFont(@FontRes title: Int = R.font.iransans_fa_num_bold,
                          @FontRes message: Int = R.font.iran_sans,
                          @FontRes buttons: Int = R.font.iran_sans): AlertDialog {

    findViewById<TextView>(R.id.alertTitle)?.typeface = ResourcesCompat.getFont(context, title)
    findViewById<TextView>(android.R.id.message)?.typeface = ResourcesCompat.getFont(context, message)
    findViewById<TextView>(android.R.id.button1)?.typeface = ResourcesCompat.getFont(context, buttons)
    findViewById<TextView>(android.R.id.button2)?.typeface = ResourcesCompat.getFont(context, buttons)
    findViewById<TextView>(android.R.id.button3)?.typeface = ResourcesCompat.getFont(context, buttons)

    return this
}



