package com.devsaul.bookfinderapp.ui.home.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.devsaul.bookfinderapp.R
import org.hamcrest.*
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityTestRule(MainActivity::class.java)

    var TITLE = "Blanca Nieves Ve la Luz"
    var AUTHOR = "Autor: Molly Aloian"

    @Test
    fun searchBookTest() {

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.searchEditText),
                isDisplayed()
            )
        )
            .perform(ViewActions.click())

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.searchEditText),
                isDisplayed()
            )
        )
            .perform(ViewActions.replaceText(TITLE), ViewActions.closeSoftKeyboard())

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.fbSearch),
                isDisplayed()
            )
        )
            .perform(ViewActions.click())


        sleepThread(2000)


        Espresso.onView(
            allOf(
                withId(R.id.cardBook),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.RecyclerListTickets),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        ).perform(ViewActions.click())


        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvSubtitle), withText(TITLE),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withText(TITLE)))


        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvAuthor), withText(AUTHOR),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withText(AUTHOR)))


        Espresso.onView(withId(R.id.fbAddFav)).perform(ViewActions.click())

        Espresso.onView(
            Matchers.allOf(
                withId(android.R.id.button2),
                withText("Cerrar")
            )
        ).perform(ViewActions.click())

        Espresso.onView(
            CoreMatchers.allOf(withId(R.id.btnFav), isDisplayed())
        ).perform(ViewActions.click())

        sleepThread(2000)

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvTitle), withText(TITLE),
                withParent(withParent(withId(R.id.cardBook))),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withText(TITLE)))

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvSubtitleDesp),
                withText(AUTHOR),
                withParent(withParent(withId(R.id.cardBook))),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withText(AUTHOR)))

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.cardBook),
                isDisplayed()
            )
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.fbDeleteFav)).perform(ViewActions.click())

        sleepThread(2000)

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvTitleError),
                withText("No tienes favoritos"),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withText("No tienes favoritos")))

        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.tvTitleErrorDesp),
                withText("Regresa a la pantalla de inicio para agregar favoritos"),
                isDisplayed()
            )
        )
            .check(ViewAssertions.matches(withText("Regresa a la pantalla de inicio para agregar favoritos")))
    }

    fun sleepThread(number: Long) {
        Thread.sleep(number)
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View?>?, position: Int
    ): Matcher<View?>? {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher!!.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher!!.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
