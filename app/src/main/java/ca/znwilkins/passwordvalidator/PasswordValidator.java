package ca.znwilkins.passwordvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordValidator extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_validator);
	}

	public void validateClick(View view) {
		EditText editText = findViewById(R.id.PasswordField);
		TextView textView = findViewById(R.id.PasswordStatusView);

		// Ahhh, I'm storing a password as a String, terrible idea :(
		String password = editText.getText().toString();

		if (password.length() == 0) {
			textView.setText(R.string.empty_password);
			return;
		}

		if (Validator.validate(password) > 3)
			textView.setText(getResources().getString(R.string.strong_password));
		else
			textView.setText(getResources().getString(R.string.weak_password));
	}
}
