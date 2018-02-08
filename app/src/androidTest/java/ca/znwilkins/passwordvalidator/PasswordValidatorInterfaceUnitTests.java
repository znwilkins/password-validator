package ca.znwilkins.passwordvalidator;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PasswordValidatorInterfaceUnitTests {

	private String emptyString = "";
	private String weakPassword = "abc";
	private String strongPassword = "N7Zd:5bfuM'?bW:@";

	@Rule
	public ActivityTestRule<PasswordValidator> pwActivityRule =
			new ActivityTestRule<PasswordValidator>(PasswordValidator.class);

	@Test
	public void emptyPassword() {
		onView(withId(R.id.PasswordField))
				.perform(typeText(emptyString), closeSoftKeyboard());
		onView(withId(R.id.ValidateButton)).perform(click());

		onView(withId(R.id.PasswordStatusView)).check(matches(withText(R.string.empty_password)));
	}

	@Test
	public void weakPassword() {
		onView(withId(R.id.PasswordField))
				.perform(typeText(weakPassword), closeSoftKeyboard());
		onView(withId(R.id.ValidateButton)).perform(click());

		onView(withId(R.id.PasswordStatusView)).check(matches(withText(R.string.weak_password)));
	}

	@Test
	public void strongPassword() {
		onView(withId(R.id.PasswordField))
				.perform(typeText(strongPassword), closeSoftKeyboard());
		onView(withId(R.id.ValidateButton)).perform(click());

		onView(withId(R.id.PasswordStatusView)).check(matches(withText(R.string.strong_password)));
	}

}
