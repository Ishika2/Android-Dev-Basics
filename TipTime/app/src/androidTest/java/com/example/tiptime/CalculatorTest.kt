package com.example.tiptime

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.StringContains.containsString
//import org.hamcrest.Matchers.containsString
//import com.example.tiptime.R.id.cost_of_service_edit_text
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_20_tip() {
        onView(withId(R.id.cost_of_service_edit_text))   //onView(ViewMatcher)
            .perform(typeText("50.00"))
        onView(withId(R.id.calc_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("Rs. 10.00"))))

    }
}

//Espresso - used for instrumentation tests; doesn't auto complete the code