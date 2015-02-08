package com.example.sciquiz;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class QuizActivity extends Activity {
	//List<Question> quesList;
	List<Question> quesList1;
	List<Question> quesList2;
	List<Question> quesList3;
	int score=0;
	int qid=0;
	int level=1;
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
		//quesList = db.getQuestionsFromSubject(subjectQuiz);
		quesList1 = db.getQuestionsFromSubjectAndLevel(subjectQuiz, 1);
		quesList2 = db.getQuestionsFromSubjectAndLevel(subjectQuiz, 2);
		quesList3 = db.getQuestionsFromSubjectAndLevel(subjectQuiz, 3);
		Collections.shuffle(quesList1);
		Collections.shuffle(quesList2);
		Collections.shuffle(quesList3);

		txtQuestion=(TextView)findViewById(R.id.textView1);
		answerButton1 = (Button)findViewById(R.id.answerbutton1);
		answerButton2 = (Button)findViewById(R.id.answerbutton2);
		answerButton3 = (Button)findViewById(R.id.answerbutton3);
		answerButton4 = (Button)findViewById(R.id.answerbutton4);
		picture = (ImageView)findViewById(R.id.imageView1);


		final int nbQuestions;
		if (level == 1){
			currentQ=quesList1.get(qid);
			if (quesList1.size() > 8) {
				nbQuestions = 8;
			} else {
				nbQuestions = quesList1.size();
			}
		} else if (level == 2) {
			currentQ=quesList2.get(qid);
			if (quesList2.size() > 6) {
				nbQuestions = 6;
			} else {
				nbQuestions = quesList2.size();
			}
		} else {
			currentQ=quesList3.get(qid);
			if (quesList3.size() > 5) {
				nbQuestions = 5;
			} else {
				nbQuestions = quesList3.size();
			}
		}
		setQuestionView();

		answerButton1.setOnClickListener(new View.OnClickListener() {		
			@SuppressLint("SimpleDateFormat") @Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton1.getText()))
				{
					if(qid < nbQuestions){					
						if (level == 1) {
							score = score + 2;
							currentQ=quesList1.get(qid);
						} else if (level == 2) {
							score = score + 5;
							currentQ=quesList2.get(qid);
						} else {
							score = score + 10;
							currentQ=quesList3.get(qid);
						}
						Log.d("score", "Your score"+score);
						setQuestionView();
					} else {
						if (level == 1 && score < 2) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
							finish();
						} else if (level == 1 && score >= 2) {
							qid = 0;
							currentQ = quesList2.get(qid);
							level = 2;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 2", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else if (level == 2 && score < 3) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
						} else if (level == 2 && score >= 3) {
							qid = 0;
							currentQ = quesList3.get(qid);
							level = 3;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 3", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
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

					}
				}else{
					answerButton1.setBackgroundResource(R.drawable.button_red);
					if (level == 1) {
						score = score - 1;
					} else if (level == 2) {
						score = score - 4;
					} else {
						score = score - 8;
					}
				}
				invalidateOptionsMenu();
			}
		});
		answerButton2.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if (currentQ.getANSWER().equals(answerButton2.getText()))
				{
					if(qid < nbQuestions){					
						if (level == 1) {
							score = score + 2;
							currentQ=quesList1.get(qid);
						} else if (level == 2) {
							score = score + 5;
							currentQ=quesList2.get(qid);
						} else {
							score = score + 10;
							currentQ=quesList3.get(qid);
						}
						Log.d("score", "Your score"+score);
						setQuestionView();
					} else {
						if (level == 1 && score < 2) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
							finish();
						} else if (level == 1 && score >= 2) {
							qid = 0;
							currentQ = quesList2.get(qid);
							level = 2;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 2", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else if (level == 2 && score < 3) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
						} else if (level == 2 && score >= 3) {
							qid = 0;
							currentQ = quesList3.get(qid);
							level = 3;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 3", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
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
					}
				} else {
					answerButton2.setBackgroundResource(R.drawable.button_red);
					if (level == 1) {
						score = score - 1;
					} else if (level == 2) {
						score = score - 4;
					} else {
						score = score - 8;
					}
				}
				invalidateOptionsMenu();
			}
		});
		answerButton3.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton3.getText()))
				{
					if(qid < nbQuestions){					
						if (level == 1) {
							score = score + 2;
							currentQ=quesList1.get(qid);
						} else if (level == 2) {
							score = score + 5;
							currentQ=quesList2.get(qid);
						} else {
							score = score + 10;
							currentQ=quesList3.get(qid);
						}
						Log.d("score", "Your score"+score);
						setQuestionView();
					} else {
						if (level == 1 && score < 2) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
							finish();
						} else if (level == 1 && score >= 2) {
							qid = 0;
							currentQ = quesList2.get(qid);
							level = 2;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 2", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else if (level == 2 && score < 3) {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
							Score scoreTOdb = new Score(formattedDate, subjectQuiz, scoreString);
							db.addScore(scoreTOdb);

							Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
							Bundle b = new Bundle();
							b.putInt("score", score); //Your score
							b.putInt("maxscore", maxscore);
							intent.putExtras(b); //Put your score to your next Intent
							startActivity(intent);
						} else if (level == 2 && score >= 3) {
							qid = 0;
							currentQ = quesList3.get(qid);
							level = 3;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 3", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else {
							int maxscore = nbQuestions * 2;
							// get date and time
							Calendar c = Calendar.getInstance();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String formattedDate = df.format(c.getTime());
							String scoreString = Integer.toString(score);
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
					}
				} else {
					answerButton3.setBackgroundResource(R.drawable.button_red);
					if (level == 1) {
						score = score - 1;
					} else if (level == 2) {
						score = score - 4;
					} else {
						score = score - 8;
					}
				}
				invalidateOptionsMenu();
			}
		});
		answerButton4.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(currentQ.getANSWER().equals(answerButton4.getText()))
				{
					if(qid < nbQuestions){					
						if (level == 1) {
							score = score + 2;
							currentQ=quesList1.get(qid);
						} else if (level == 2) {
							score = score + 5;
							currentQ=quesList2.get(qid);
						} else {
							score = score + 10;
							currentQ=quesList3.get(qid);
						}
						Log.d("score", "Your score"+score);
						setQuestionView();
					}else{
						if (level == 1 && score < 2) {
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
						} else if (level == 1 && score >= 2) {
							qid = 0;
							currentQ = quesList2.get(qid);
							level = 2;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 2", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else if (level == 2 && score < 3) {
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
						} else if (level == 2 && score >= 3) {
							qid = 0;
							currentQ = quesList3.get(qid);
							level = 3;
							Toast toast = Toast.makeText(getApplicationContext(), "Bien joué! \n Vous passez au niveau 3", Toast.LENGTH_SHORT);
							LinearLayout toastLayout = (LinearLayout) toast.getView();
							TextView toastTV = (TextView) toastLayout.getChildAt(0);
							toastTV.setTextSize(30);
							toast.show();
							setQuestionView();
						} else {
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
					}
				} else {
					answerButton4.setBackgroundResource(R.drawable.button_red);
					if (level == 1) {
						score = score - 1;
					} else if (level == 2) {
						score = score - 4;
					} else {
						score = score - 8;
					}
				}
				invalidateOptionsMenu();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		MenuItem menuLevel = menu.findItem(R.id.menu_level);
		String stringlevel = String.valueOf(level);
		menuLevel.setTitle("NIVEAU:\n"+stringlevel);
		MenuItem menuScore = menu.findItem(R.id.menu_score);
		String stringscore = String.valueOf(score);
		menuScore.setTitle("SCORE:\n"+stringscore);
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