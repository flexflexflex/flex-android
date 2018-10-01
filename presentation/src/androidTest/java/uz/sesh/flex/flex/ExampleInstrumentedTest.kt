package uz.sesh.flex.flex

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("uz.sesh.flex.flex", appContext.packageName)
    }


    @Test
    fun greeterSaysHello() {
        onView(withId(R.id.name_field)).perform(typeText("Steve"))
        onView(withId(R.id.greet_button)).perform(click())
        onView(withText("Hello Steve!")).check(matches(isDisplayed()))
    }
}
