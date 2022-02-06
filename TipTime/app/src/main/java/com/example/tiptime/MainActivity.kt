package com.example.tiptime

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

// ?. --> safe calls - we can add a ? after the data type of that property which declares that variable as a nullable property
// ‘nullableVariable’ is immutable (i.e. a local property which can’t be altered between the check and the utilization or a member ‘val’ which has a backing field and is not overridable)
// !!. --> null checks - the nullable property requires a null check every time before utilizing that property or requires a confirmation from the developer that the estimation of that property won’t be null while utilizing it
//This calls the method if the property is not null or returns null if that property is null without throwing an NPE (null pointer exception).
//Safe calls are useful in chains.

class MainActivity : AppCompatActivity() {
    private var btnToggleDark: ImageView? = null  //in java it's: private Button(or ImageView) btnToggleDark;
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcButton.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )  //to hide the keyboard when "enter/done" is clicked
        }
        //finding the image/icon
        btnToggleDark = findViewById(R.id.theme_state)
        //saving the state of our app using sharedpreferences
        val sharedPreferences = getSharedPreferences(
            "sharedPrefs", MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()  //to edit the old theme
        val isDarkMode: Boolean = sharedPreferences.getBoolean("isDarkMode", false)  //default setting theme to light

        //when user reopens the app after applying dark/light mode
        if(isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            delegate.applyDayNight()
            binding.themeState.setImageResource(R.drawable.ic_light_mode)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            delegate.applyDayNight()
            binding.themeState.setImageResource(R.drawable.ic_night_mode)
        }

        btnToggleDark?.setOnClickListener (
            View.OnClickListener {
                //when user taps the icon
                if (isDarkMode) {
                    //if dark mode is on it, will turn it off
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    binding.themeState.setImageResource(R.drawable.ic_night_mode)
                    editor.putBoolean("isDarkMode", false)
                    editor.apply()
                    //it will set isDarkMode boolean to false
                }
                else {
                    //if dark mode is off, it will turn it on
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                    binding.themeState.setImageResource(R.drawable.ic_light_mode)
                    editor.putBoolean("isDarkMode", true)
                    editor.apply()
                    //it will set isDarkMode boolean to true
                }
            }
        )
    }

    private fun calculateTip() {
        val CoS = binding.costOfServiceEditText.text.toString()
        val cost = CoS.toDoubleOrNull()   //null-safety
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.first -> 0.20
            R.id.second -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View?, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //Hide the keyboard
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
            return true
        }
        return false
    }

//private fun View.setOnClickListener(defaultNightMode: Unit) {
//    val new = null
//    new View.OnClickListener() {
//        @Override
//        public void callOnClick(View view) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//    }
//}


//        private Button btnToggleDark = findViewById(R.id.btnToggleDark)
//        val SharedPreferences sharedPreferences = getSharedPreferences (
//                "sharePrefs", MODE_PRIVATE)
//        SharedPreferences.Editor editor = sharedPreferences.edit()
//        val boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
//        if(isDarkModeOn) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            binding.themeState.setImageResource(R.drawable.ic_night_mode)
//            delegate.applyDayNight()
//        }
//        else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            binding.themeState.setImageResource(R.drawable.ic_light_mode)
//            delegate.applyDayNight()
//        }
//
//        btnToggleDark.setOnClickListener(
//            new View.OnClickListener() {
//                override fun onClick(View view)
//                {
//                    if(isDarkModeOn) {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                        editor.putBoolean("isDarkModeOn", false)
//                        editor.apply()
//                    }
//                    else {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                        editor.putBoolean("isDarkModeOn", true)
//                        editor.apply()
//                    }
//                }
//            }
//        )
////        binding.themeState.setOnClickListener(AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES))
//        checkTheme()
//        binding.themeState.setOnClickListener{ changeTheme() }
}

//    private fun checkTheme(){
//        when (MyPreferences(this).darkMode) {
//            0 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                binding.themeState.setImageResource(R.drawable.ic_night_mode)
//                delegate.applyDayNight()
//            }
//            1 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                binding.themeState.setImageResource(R.drawable.ic_light_mode)
//                delegate.applyDayNight()
//            }
//        }
//    }
//    private fun changeTheme() {
////        val checkedItem = MyPreferences(this).darkMode
//        if(checkedItem == 0){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            binding.themeState.setImageResource(R.drawable.ic_night_mode)
////            MyPreferences(this).darkMode = 0
//            delegate.applyDayNight()
////            flag = 0
//        }
//        else if (checkedItem == 1) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            binding.themeState.setImageResource(R.drawable.ic_light_mode)
////            MyPreferences(this).darkMode = 1
//            delegate.applyDayNight()
////            flag = 1
//        }
//    }


//    private fun checkTheme(isDark: Int) : Int {
//        var isDarkM = 0
//        if(isDark == 0)
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            delegate.applyDayNight()
//            isDarkM = 1
//        }
//        else if(isDark == 1)
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            delegate.applyDayNight()
//            isDarkM = 0
//        }
//        return isDarkM
//    }
//
//    private fun changeTheme(isDarkY: Int) {
//        if(isDarkY == 0)
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            delegate.applyDayNight()
//            binding.themeState.setImageResource(R.drawable.ic_night_mode)
//        }
//        else if(isDarkY == 1)
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            delegate.applyDayNight()
//            binding.themeState.setImageResource(R.drawable.ic_light_mode)
//        }
//    }
