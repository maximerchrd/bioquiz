package com.example.bioquiz;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;



import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class QuizActivity extends Activity {
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	Button answerButton1, answerButton2, answerButton3, answerButton4;
	ImageView picture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		final DbHelper db = new DbHelper(this);
		Bundle bun = getIntent().getExtras();
		final String subjectQuiz = bun.getString("subject");
		quesList = db.getQuestionsFromSubject(subjectQuiz);
		Collections.shuffle(quesList);
		
		txtQuestion=(TextView)findViewById(R.id.textView1);
		answerButton1 = (Button)findViewById(R.id.answerbutton1);
		answerButton2 = (Button)findViewById(R.id.answerbutton2);
		answerButton3 = (Button)findViewById(R.id.answerbutton3);
		answerButton4 = (Button)findViewById(R.id.answerbutton4);
		picture = (ImageView)findViewById(R.id.imageView1);
		currentQ=quesList.get(qid);
		setQuestionView();
		
		
		final int nbQuestions = quesList.size();
		answerButton1.setOnClickListener(new View.OnClickListener() {		
			@SuppressLint("SimpleDateFormat") @Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton1.getText()))
				{
					score = score + 2;
					Log.d("score", "Your score"+score);
					if(qid < nbQuestions){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						int maxscore = nbQuestions * 2;
						// get date and time
						Calendar c = Calendar.getInstance();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String formattedDate = df.format(c.getTime());
				        String scoreString = Integer.toString(score);;
				        Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
						db.addScore(scoreTOdb);
						
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						b.putInt("maxscore", maxscore);
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton1.setBackgroundResource(R.drawable.button_red);
					score--;
				}
			}
		});
		answerButton2.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton2.getText()))
				{
					score = score + 2;
					Log.d("score", "Your score"+score);
					if(qid < nbQuestions){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						int maxscore = nbQuestions * 2;
						// get date and time
						Calendar c = Calendar.getInstance();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String formattedDate = df.format(c.getTime());
				        String scoreString = Integer.toString(score);;
				        Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
						db.addScore(scoreTOdb);
						
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						b.putInt("maxscore", maxscore);
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton2.setBackgroundResource(R.drawable.button_red);
					score--;
				}
			}
		});
		answerButton3.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton3.getText()))
				{
					score = score + 2;
					Log.d("score", "Your score"+score);
					if(qid < nbQuestions){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						int maxscore = nbQuestions * 2;
						// get date and time
						Calendar c = Calendar.getInstance();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String formattedDate = df.format(c.getTime());
				        String scoreString = Integer.toString(score);;
				        Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
						db.addScore(scoreTOdb);
						
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						b.putInt("maxscore", maxscore);
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton3.setBackgroundResource(R.drawable.button_red);
					score--;
				}
			}
		});
		answerButton4.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton4.getText()))
				{
					score = score + 2;
					Log.d("score", "Your score"+score);
					if(qid < nbQuestions){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						int maxscore = nbQuestions * 2;
						// get date and time
						Calendar c = Calendar.getInstance();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String formattedDate = df.format(c.getTime());
				        String scoreString = Integer.toString(score);;
				        Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
						db.addScore(scoreTOdb);
						
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						b.putInt("maxscore", maxscore);
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton4.setBackgroundResource(R.drawable.button_red);
					score--;
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}
	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		answerButton1.setBackgroundResource(R.drawable.button_blue);
		answerButton2.setBackgroundResource(R.drawable.button_blue);
		answerButton3.setBackgroundResource(R.drawable.button_blue);
		answerButton4.setBackgroundResource(R.drawable.button_blue);
			int imageResource = getResources().getIdentifier(currentQ.getIMAGE(), null, getPackageName());
			picture.setImageResource(imageResource);
		
		
		String[] answerOptions;
		answerOptions = new String[4];
		answerOptions[0] = currentQ.getOPTA();
		answerOptions[1] = currentQ.getOPTB();
		answerOptions[2] = currentQ.getOPTC();
		answerOptions[3] = currentQ.getOPTD();
		
		//implementing Fisher-Yates shuffle
		Random rnd = new Random();
	    for (int i = answerOptions.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String a = answerOptions[index];
	      answerOptions[index] = answerOptions[i];
	      answerOptions[i] = a;
	    }
		
		
		answerButton1.setText(answerOptions[0]);
		answerButton2.setText(answerOptions[1]);
		answerButton3.setText(answerOptions[2]);
		answerButton4.setText(answerOptions[3]);
		qid++;
	}
}