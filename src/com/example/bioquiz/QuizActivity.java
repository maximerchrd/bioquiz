package com.example.bioquiz;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class QuizActivity extends Activity {
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	Button butNext, answerButton1, answerButton2, answerButton3, answerButton4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		DbHelper db=new DbHelper(this);
		quesList=db.getAllQuestions();
		currentQ=quesList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		answerButton1=(Button)findViewById(R.id.answerbutton1);
		answerButton2=(Button)findViewById(R.id.answerbutton2);
		answerButton3=(Button)findViewById(R.id.answerbutton3);
		answerButton4=(Button)findViewById(R.id.answerbutton4);
		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		
		answerButton1.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton1.getText()))
				{
					score++;
					Log.d("score", "Your score"+score);
					if(qid<5){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton1.setBackgroundColor(Color.RED);
				}
			}
		});
		answerButton2.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton2.getText()))
				{
					score++;
					Log.d("score", "Your score"+score);
					if(qid<5){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton2.setBackgroundColor(Color.RED);
				}
			}
		});
		answerButton3.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton3.getText()))
				{
					score++;
					Log.d("score", "Your score"+score);
					if(qid<5){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton3.setBackgroundColor(Color.RED);
				}
			}
		});
		answerButton4.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton4.getText()))
				{
					score++;
					Log.d("score", "Your score"+score);
					if(qid<5){					
						currentQ=quesList.get(qid);
						setQuestionView();
					}else{
						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else{
					answerButton4.setBackgroundColor(Color.RED);
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
		answerButton1.setText(currentQ.getOPTA());
		answerButton2.setText(currentQ.getOPTB());
		answerButton3.setText(currentQ.getOPTC());
		answerButton4.setText(currentQ.getOPTD());
		answerButton1.setBackgroundColor(Color.WHITE);
		answerButton2.setBackgroundColor(Color.WHITE);
		answerButton3.setBackgroundColor(Color.WHITE);
		answerButton4.setBackgroundColor(Color.WHITE);
		qid++;
	}
}