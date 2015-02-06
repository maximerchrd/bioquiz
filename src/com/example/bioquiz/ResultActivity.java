package com.example.bioquiz;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
public class ResultActivity extends Activity {
	Button backToMenuButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		backToMenuButton = (Button)findViewById(R.id.button1);
		//get rating bar object
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); 
		bar.setNumStars(5);
		bar.setStepSize(0.5f);
		//get text view
		TextView t=(TextView)findViewById(R.id.textResult);
		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		int maxscore = b.getInt("maxscore");
		if (score < 0) score = 0;
		float percentage = (float)score;
		float maxscorefloat = (float)maxscore;
		percentage = percentage / maxscorefloat * 100;
		//display score
		bar.setRating(percentage / 100 * 5);
		switch (score)
		{
		case 1:
		case 2: t.setText("Oopsie! Better Luck Next Time!");
		break;
		case 3:
		case 4:t.setText("Hmmmm.. Someone's been reading a lot of trivia");
		break;
		case 5:t.setText("Who are you? A trivia wizard???");
		break;
		}
		
		//listener for "back to menu" button
		backToMenuButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}