package com.example.bioquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoresActivity extends Activity {
	Button backToMenuButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		backToMenuButton = (Button)findViewById(R.id.button1);
		//listener for "back to menu" button
		backToMenuButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScoresActivity.this, MenuActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
