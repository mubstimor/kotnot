package mubstimor.android.kotnot

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView

@RunWith(AndroidJUnit4::class)
class NextThroughNotesTest {

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes() {
        onView(ViewMatchers.withId(R.id.listItems))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        for (index in 0..DataManager.notes.lastIndex) {
            val note = DataManager.notes[index]

            onView(withId(R.id.spinnerCourses)).check(
                matches(withSpinnerText(note.course?.title))
            )
            onView(withId(R.id.textNoteTitle)).check(
                matches(withText(note.title))
            )
            onView(withId(R.id.textNoteText)).check(
                matches(withText(note.text))
            )

            if(index != DataManager.notes.lastIndex)
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())

        }

        onView(withId(R.id.action_next)).check(matches(not(isEnabled())))

    }

}