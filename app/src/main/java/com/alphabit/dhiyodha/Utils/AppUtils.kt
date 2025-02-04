package com.alphabit.dhiyodha.Utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.telephony.TelephonyManager
import android.text.format.DateUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// Add these extension functions to an empty kotlin file
// For getting keyboard height and open/close status
fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics
    )
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = Math.round(this.convertDpToPx(50F))
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}

fun View.toggleArrow(isExpanded: Boolean) {
    if (isExpanded) {
        this.animate().setDuration(150).rotationX(180f)
    } else {
        this.animate().setDuration(150).rotationX(0f)
    }
}

fun View.arrowDown(isExpanded: Boolean) {
    if (isExpanded) {
        this.animate().setDuration(150).rotation(90f)
    } else {
        this.animate().setDuration(150).rotation(0f)
    }
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.dpToPx(dp: Int): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}

object AppUtils {
    private val TAG = "AppUtils"

    @RequiresApi(Build.VERSION_CODES.O)
    fun isCurrentDatePassed(dateInString: String): Boolean {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val date = df.parse(dateInString)
        val currentDate = Calendar.getInstance().time

        Log.d(TAG, "isCurrentDatePassed: Date ===== $date")
        Log.d(TAG, "isCurrentDatePassed: currentDate ===== $currentDate")

        return if (date != null) {
            Log.d(TAG, "isCurrentDatePassed: currentDate ===== " + (date < currentDate))
            (date < currentDate)
        } else false
    }

    fun getReadableTimeFromCreatedAtWithOutSecond(date: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val parsedDate = inputFormatter.parse(date)
        val outputFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        outputFormatter.timeZone = TimeZone.getDefault() // Convert to local time zone
        return outputFormatter.format(parsedDate!!)
    }

    fun getReadableDateFromCreatedAtSecond(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val parsedDate = sdf.parse(date)

        val outPutFormatter = SimpleDateFormat("dd MMM yyyy")
        return outPutFormatter.format(parsedDate)
    }

    fun printHashKey(pContext: Context) {
        try {
            val info = pContext.packageManager.getPackageInfo(
                "com.mor", PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures!!) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, SHOW_IMPLICIT)
    }

    fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun View.focusAndShowKeyboard(tryAgain: Boolean = true) {
        /**
         * This is to be called when the window already has focus.
         */
        fun View.showTheKeyboardNow() {
            if (isFocused) {
                post {
                    // We still post the call, just in case we are being notified of the windows focus
                    // but InputMethodManager didn't get properly setup yet.
                    val imm =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    val isInputMethodManagerSetup = imm.showSoftInput(this, SHOW_IMPLICIT)
                    if (!isInputMethodManagerSetup) {
                        // InputMethodManager still didn't get properly setup yet even we post the call.
                        // so we should give it one more try.
                        if (tryAgain) {
                            this.focusAndShowKeyboard(false)
                        }
                    }
                }
            }
        }

        requestFocus()
        if (hasWindowFocus()) {
            // No need to wait for the window to get focus.
            showTheKeyboardNow()
        } else {
            // We need to wait until the window gets focus.
            viewTreeObserver.addOnWindowFocusChangeListener(object :
                ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    // This notification will arrive just before the InputMethodManager gets set up.
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        // It’s very important to remove this listener once we are done.
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
        }
    }


    /**
     * Used to show alert dialog without title.
     * @param context
     * @param message
     * @param positiveButton
     */
    fun showAlertDialog(context: Context?, message: String?, positiveButton: String?) {
        val alertDialog = AlertDialog(context)
        alertDialog.setCancelable(false)
        alertDialog.message = message
        alertDialog.setPositiveButton(positiveButton,
            View.OnClickListener { alertDialog.dismiss() })
        alertDialog.show()
    }

    /**
     * Used to show alert dialog with title.
     * @param context
     * @param title
     * @param message
     * @param positiveButton
     */
    fun showAlertDialog(
        context: Context?, title: String?, message: String?, positiveButton: String?
    ) {
        val alertDialog = AlertDialog(context)
        alertDialog.setCancelable(false)
        alertDialog.title = title
        alertDialog.message = message
        alertDialog.setPositiveButton(positiveButton,
            View.OnClickListener { alertDialog.dismiss() })
        alertDialog.show()
    }

    /**
     * Used to show alert dialog with title.
     * @param context
     * @param title
     * @param message
     * @param positiveButton
     */

    /**
     * Used to check is EditText empty or not.
     * @param editText
     * @return
     */
    fun isEditTextEmpty(editText: EditText): Boolean {
        return editText.text.toString().trim { it <= ' ' } == ""
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun time24To12Hr(date: String): String {
        val inputDateStr = date
        val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())

        val date = inputFormat.parse(inputDateStr)
        val outputDateStr = date?.let { outputFormat.format(it) }

        return outputDateStr!!
    }

    fun time24To12HrforChat(date: String): String {
        val inputDateStr = date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat =
            SimpleDateFormat("hh:mm a", Locale.getDefault()) // 12-hour format with leading zero

        val date = inputFormat.parse(inputDateStr)
        val outputDateStr = date?.let { outputFormat.format(it) }

        return outputDateStr ?: ""
    }

    /**
     * Method is used to get the value of EditText.
     * @param editText
     * @return
     */
    fun getEditTextValue(editText: EditText): String? {
        return editText.text.toString()
    }

    /**
     * Used to check the email validation..
     * @param editText
     * @return
     */
    fun isValidEmail(editText: EditText): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(getEditTextValue(editText)!!).matches()
    }

    fun convertUtcToIst(slotEndTime: String): String {
        // Define the UTC date format
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Parse the UTC time
        val utcDate = utcFormat.parse(slotEndTime)

        // Define the IST date format
        val istFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        istFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")

        // Format the parsed UTC time to IST
        return istFormat.format(utcDate!!)
    }

    fun getFormattedTime(createdAt: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return try {
            val createdTime: Long? = sdf.parse(createdAt)?.time
            createdTime?.let {
                val currentTime = System.currentTimeMillis()
                DateUtils.getRelativeTimeSpanString(it, currentTime, DateUtils.MINUTE_IN_MILLIS)
                    .toString()
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getAge(DOB_Year: Int, DOB_Month: Int, DOB_Day: Int): Int {
        var age: Int
        val calenderToday = Calendar.getInstance()
        val currentYear = calenderToday[Calendar.YEAR]
        val currentMonth = 1 + calenderToday[Calendar.MONTH]
        val todayDay = calenderToday[Calendar.DAY_OF_MONTH]
        age = currentYear - DOB_Year
        if (DOB_Month > currentMonth) {
            --age
        } else if (DOB_Month == currentMonth) {
            if (DOB_Day > todayDay) {
                --age
            }
        }
        return age
    }

    @Throws(ParseException::class)
    fun formatDateFromDateString(
        inputDateFormat: String?, outputDateFormat: String?, inputDate: String?
    ): String? {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)
        return mOutputDateString
    }

    fun formatNumber(context: Context, unformattedNumber: String): String? {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryCode = tm.simCountryIso

        var formattedNumber: String?
        formattedNumber = if (Build.VERSION.SDK_INT >= 21) {
            PhoneNumberUtils.formatNumberToE164(unformattedNumber, countryCode)
        } else {
            PhoneNumberUtils.formatNumber(unformattedNumber)
        }
        if (formattedNumber == null) {
            formattedNumber = unformattedNumber.replace("[-,+]", "")
        }
        return formattedNumber
    }

    fun replaceFragment(
        context: Context, container: Int, fragment: Fragment?, isAddToBackstack: Boolean
    ) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.replace(container, fragment!!)
        if (isAddToBackstack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(
        context: Context,
        container: Int,
        fragment: Fragment?,
        bundle: Bundle,
        isAddToBackstack: Boolean
    ) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragment!!.arguments = bundle
        transaction.replace(container, fragment)
        if (isAddToBackstack) transaction.addToBackStack(null)
        transaction.commit()
    }


    fun setStatusBar(window: Window, activity: Activity) {
        window.decorView.rootView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.findViewById(android.R.id.content)) { view, insets ->
            insets.replaceSystemWindowInsets(0, 0, 0, insets.systemWindowInsetBottom).apply {
                ViewCompat.onApplyWindowInsets(view, this)
            }
        }
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val window = activity.window
        val winParams = window.attributes
        if (on) winParams.flags = winParams.flags or bits else winParams.flags =
            winParams.flags and bits.inv()
        window.attributes = winParams
    }


    fun isActivityRunning(context: Context, activityClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = activityManager.getRunningTasks(Int.MAX_VALUE)
        for (task in tasks) {
            if (activityClass.canonicalName.equals(
                    task.baseActivity!!.className, ignoreCase = true
                )
            ) return true
        }
        return false
    }
}