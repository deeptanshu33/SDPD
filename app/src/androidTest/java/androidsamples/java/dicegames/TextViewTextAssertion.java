package androidsamples.java.dicegames;

import static org.junit.Assert.assertNotNull;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class TextViewTextAssertion implements ViewAssertion {
  private final String[] capturedText;

  public TextViewTextAssertion(String[] capturedText) {
    this.capturedText = capturedText;
  }

  @Override
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }

    // Ensure the view is a TextView
    TextView textView = (TextView) view;
    assertNotNull(textView);

    // Capture the text
    capturedText[0] = textView.getText().toString();
  }
}
