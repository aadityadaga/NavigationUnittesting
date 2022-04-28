package com.example.navigationunittesting


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BottomNavigationActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(BottomNavigationActivity::class.java)

    @Test
    fun bottomNavigationActivityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.button_Validate), withText("register"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.edittext_Name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInput_Name),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        textInputEditText.perform(replaceText("Aditya Daga"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            withId(R.id.edittext_Email)

        )
        textInputEditText3.check(matches(isDisplayed()))
        textInputEditText3.perform(click())


        val textInputEditText5 = onView(
            withId(R.id.edittext_Email)

        )
        textInputEditText5.check(matches(isDisplayed()))
        textInputEditText5.perform(replaceText("Aadityadaga01@gmail.com"), closeSoftKeyboard())


            val textInputEditText7 =onView(
                withId(R.id.edittext_Address)

            )
        textInputEditText7.check(matches(isDisplayed()))

        textInputEditText7.perform(replaceText("Noida"), closeSoftKeyboard())

            val materialButton2 = onView(
                allOf(
                    withId(R.id.button_Validate), withText("register"),
                    childAtPosition(
                        childAtPosition(
                            withClassName(`is`("androidx.cardview.widget.CardView")),
                            0
                        ),
                        4
                    ),
                    isDisplayed()
                )
            )
            materialButton2.perform(click())

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.edittext_Name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInput_Name),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(replaceText("Test Demo"), closeSoftKeyboard())


        val textInputEditText10= onView(
            withId(R.id.edittext_Email)

        )
        textInputEditText10.check(matches(isDisplayed()))
        textInputEditText10.perform(replaceText("testDemo01@gmail.com"), closeSoftKeyboard())


        val textInputEditText11 =onView(
            withId(R.id.edittext_Address)

        )
        textInputEditText11.check(matches(isDisplayed()))

        textInputEditText11.perform(replaceText("Noida"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.button_Validate), withText("register"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())



              val bottomNavigationItemView = onView(
                  allOf(
                      withId(R.id.item_list), withContentDescription("List"),
                      childAtPosition(
                          childAtPosition(
                              withId(R.id.bottom_navigation),
                              0
                          ),
                          1
                      ),
                      isDisplayed()
                  )
              )
              bottomNavigationItemView.perform(click())

             val constraintLayout = onView(
                  allOf(
                      withId(R.id.constraintLayout),
                      childAtPosition(
                          childAtPosition(
                              withId(R.id.recyclerView),
                              0
                          ),
                          0
                      ),
                      isDisplayed()
                  )
              )
              constraintLayout.perform(click())

              // Added a sleep statement to match the app's execution delay.
              // The recommended way to handle such scenarios is to use Espresso idling resources:
              // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
              Thread.sleep(500)

            val appCompatImageView = onView(
                allOf(
                    withId(R.id.imageView_delete),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.toolbar_Header),
                            0
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            appCompatImageView.perform(click())

                   val materialButton4 = onView(
                     allOf(
                         withId(android.R.id.button1), withText("Ok"),
                         childAtPosition(
                             childAtPosition(
                                 withId(androidx.appcompat.R.id.buttonPanel),
                                 0
                             ),
                             3
                         )
                     )
                 )
                 materialButton4.perform(scrollTo(), click())

                 Thread.sleep(700)

             /*    val constraintLayout2 = onView(
                     allOf(
                         withId(R.id.constraintLayout),
                         childAtPosition(
                             childAtPosition(
                                 withId(R.id.recyclerView),
                                 0
                             ),
                             0
                         ),
                         isDisplayed()
                     )
                 )
                 constraintLayout2.perform(click())

                Thread.sleep(700)

                 pressBack()*/

                 Thread.sleep(1000)

                 val bottomNavigationItemView2 = onView(
                         withId(R.id.item_setting)
                 )

        bottomNavigationItemView2.check(matches(isDisplayed()))

        bottomNavigationItemView2.perform(click())

                 val constraintLayout3 = onView(
                     allOf(
                         childAtPosition(
                             allOf(
                                 withId(R.id.nav_host_fragment),
                                 childAtPosition(
                                     withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                                     1
                                 )
                             ),
                             0
                         ),
                         isDisplayed()
                     )
                 )
                 constraintLayout3.perform(click())

                 val materialButton5 = onView(
                     allOf(
                         withId(android.R.id.button1), withText("Ok"),
                         childAtPosition(
                             childAtPosition(
                                 withId(androidx.appcompat.R.id.buttonPanel),
                                 0
                             ),
                             3
                         )
                     )
                 )
                 materialButton5.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
