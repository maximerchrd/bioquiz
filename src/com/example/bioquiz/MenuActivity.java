package com.example.bioquiz;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends Activity {
	Button startButton;
	ListView listSubjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		startButton = (Button)findViewById(R.id.startbutton);
		listSubjects = (ListView) findViewById(R.id.listView1);
		DbHelper db = new DbHelper(this);

		String subjectsRaw = db.getSubjects();
		final String[] subjects = subjectsRaw.split("/");

		
		
		
		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, subjects);
		// Assign adapter to ListView
		listSubjects.setAdapter(adapter); 
		
		
		// ListView Item Click Listener
		listSubjects.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String subject = (String)parent.getAdapter().getItem(position);
				
				startButton.setOnClickListener(new View.OnClickListener() {		
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MenuActivity.this, QuizActivity.class);
						Bundle bun = new Bundle();
						bun.putString("subject", subject);
						intent.putExtras(bun);
						startActivity(intent);
						finish();
					}
				});
				
				
				
			}

		});
	

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
}
