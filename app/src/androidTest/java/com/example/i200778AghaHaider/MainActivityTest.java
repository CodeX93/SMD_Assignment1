import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.i200778AghaHaider.MainActivity;
import com.example.myapplication.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private SharedPreferences sharedPreferences;

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clearing any existing data in shared preferences
        editor.apply();
    }

    @Test
    public void checkLoginButtonDisplayed() {
        onView(withId(R.id.login_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void loginUser() {
        onView(withId(R.id.email_input)).perform(typeText("example@example.com"));
        onView(withId(R.id.password_input)).perform(typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());
        onView(withText("User login success")).check(matches(isDisplayed()));
    }

    @Test
    public void checkSignUpButtonDisplayed() {
        onView(withId(R.id.signup_btn)).check(matches(isDisplayed()));
    }
}
