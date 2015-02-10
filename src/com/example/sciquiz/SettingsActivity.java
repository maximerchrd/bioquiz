package com.example.sciquiz;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class SettingsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		final EditText editName;
		final Button buttonSaveAndBack;
		final DbHelper db = new DbHelper(this);

		editName = (EditText) findViewById(R.id.edittextnom);
		buttonSaveAndBack = (Button) findViewById(R.id.buttonsaveandback);
		editName.setText(db.getName(), null);

		buttonSaveAndBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String name = editName.getText().toString();
				db.addName(name);
				finish();
			}
		});
	}
}
