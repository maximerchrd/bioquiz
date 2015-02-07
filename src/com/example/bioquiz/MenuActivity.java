package com.example.bioquiz;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MenuActivity extends Activity {
	Button startButton, scoresButton, sendButton;
	ListView listSubjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		startButton = (Button)findViewById(R.id.startbutton);
		scoresButton = (Button)findViewById(R.id.scoresbutton);
		sendButton = (Button)findViewById(R.id.sendbutton);
		listSubjects = (ListView) findViewById(R.id.listView1);
		final DbHelper db = new DbHelper(this);

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

		//go to scores button
		scoresButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, ScoresActivity.class);
				startActivity(intent);
				finish();
			}
		});

		//send email button
		sendButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				try
			    {
			        File root = new File(Environment.getExternalStorageDirectory(), "SciQuiz");
			        if (!root.exists()) {
			            root.mkdirs();
			        }
			        File gpxfile = new File(root, "resultats.txt");
			        FileWriter writer = new FileWriter(gpxfile);
			        List<Score> scorelist;
			        scorelist = db.getScores();
			        for (int i = 0; i < scorelist.size(); i++) {
			        	Score scoreToWrite = scorelist.get(i);
			        	writer.append(scoreToWrite.getSUBJECTscores()+"   "+scoreToWrite.getTIME()+"   "+scoreToWrite.getSCORE()+ "\n");
			        }
			        writer.flush();
			        writer.close();
			    }
			    catch(IOException e)
			    {
			         e.printStackTrace();
			    }
				new SendEmailAsyncTask().execute();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}


	//class for sending mail
	class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
		Mail m = new Mail("maximerchrd@gmail.com", "henearkr");

		public SendEmailAsyncTask() {
			if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "SendEmailAsyncTask()");
			String[] toArr = { "maximerchrd@hotmail.com"};
			m.setTo(toArr);
			m.setFrom("maximerchrd@gmail.com");
			m.setSubject("Email from Android");
			m.setBody("body.");
			try {
				File results = new File(Environment.getExternalStorageDirectory(), "SciQuiz/resultats.txt");
				m.addAttachment(results.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
			try {
				m.send();
				return true;
			} catch (AuthenticationFailedException e) {
				Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
				e.printStackTrace();
				return false;
			} catch (MessagingException e) {
				Log.e(SendEmailAsyncTask.class.getName(), m.getTO() + "failed");
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
