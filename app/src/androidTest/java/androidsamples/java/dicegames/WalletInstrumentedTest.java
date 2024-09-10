package androidsamples.java.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertNotNull;


// Run tests using AndroidJUnit4 runner
@RunWith(AndroidJUnit4.class)
public class WalletInstrumentedTest {

  // Rule to launch MainActivity before each test
  @Rule
  public ActivityScenarioRule<WalletActivity> activityScenarioRule =
          new ActivityScenarioRule<>(WalletActivity.class); // Adjust to your activity

  // Custom matcher for checking if text matches a regex pattern
  public static Matcher<View> withTextPattern(final String pattern) {
    return new BoundedMatcher<View, TextView>(TextView.class) {
      @Override
      public void describeTo(Description description) {
        description.appendText("with text matching pattern: " + pattern);
      }

      @Override
      protected boolean matchesSafely(TextView textView) {
        return textView.getText().toString().matches(pattern);
      }
    };
  }

  @Test
  public void clickingDieButtonUpdatesUi() {
    // Interact with the UI
    onView(withId(R.id.btn_die)).perform(click());

    // Check that total rolls increased
    onView(withId(R.id.total_dice_rolls_value)).check(matches(withText("1")));

    // Check that the die value is between 1 and 6
    onView(withId(R.id.btn_die)).check(matches(anyOf(
            withText("1"), withText("2"), withText("3"),
            withText("4"), withText("5"), withText("6")
    )));

    // Ensure balance is a valid integer (positive or negative)
    onView(withId(R.id.txt_balance)).check(matches(withTextPattern("-?\\d+")));
  }

  @Test
  public void multipleRollsUpdateStatistics() {
    for (int i = 0; i < 10; i++) {
      onView(withId(R.id.btn_die)).perform(click());
    }

    // Check that total rolls count is updated
    onView(withId(R.id.total_dice_rolls_value)).check(matches(withText("10")));

    // Check that other statistics are non-negative integers
    onView(withId(R.id.sixes_rolled_value)).check(matches(withTextPattern("\\d+")));
    onView(withId(R.id.double_sixes_rolled_value)).check(matches(withTextPattern("\\d+")));
    onView(withId(R.id.double_others_rolled_value)).check(matches(withTextPattern("\\d+")));
  }

  @Test
  public void checkValidStateAfterTwoRolls(){
    for(int i = 0; i<2; i++){
      onView(withId(R.id.btn_die)).perform(click());
    }

    String[] capturedText = new String[1];
    onView(withId(R.id.txt_balance)).check(new TextViewTextAssertion(capturedText));
    int balance = Integer.parseInt(capturedText[0]);
    assertThat("Balance should be between 15 and -5", balance, allOf(greaterThanOrEqualTo(-5), lessThanOrEqualTo(15)));
  }

  @Test
  public void checkValidStateAfterThreeRolls(){
    for(int i = 0; i<3; i++){
      onView(withId(R.id.btn_die)).perform(click());
    }

    String[] capturedText = new String[1];
    onView(withId(R.id.txt_balance)).check(new TextViewTextAssertion(capturedText));
    int balance = Integer.parseInt(capturedText[0]);
    assertThat("Balance should be between 25 and -10", balance, allOf(greaterThanOrEqualTo(-10), lessThanOrEqualTo(25)));
  }

  @Test
  public void checkValidStateAfterFourRolls(){
    for(int i = 0; i<4; i++){
      onView(withId(R.id.btn_die)).perform(click());
    }

    String[] capturedText = new String[1];
    onView(withId(R.id.txt_balance)).check(new TextViewTextAssertion(capturedText));
    int balance = Integer.parseInt(capturedText[0]);
    assertThat("Balance should be between 35 and -15", balance, allOf(greaterThanOrEqualTo(-15), lessThanOrEqualTo(35)));
  }

  @Test
  public void checkValidStateAfterFiveRolls(){
    for(int i = 0; i<5; i++){
      onView(withId(R.id.btn_die)).perform(click());
    }

    String[] capturedText = new String[1];
    onView(withId(R.id.txt_balance)).check(new TextViewTextAssertion(capturedText));
    int balance = Integer.parseInt(capturedText[0]);
    assertThat("Balance should be between 45 and -20", balance, allOf(greaterThanOrEqualTo(-20), lessThanOrEqualTo(45)));
  }
}
