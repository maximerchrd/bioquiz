package com.example.sciquiz;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "bioQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_SUBJECT = "subject";
	private static final String KEY_LEVEL = "level";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private static final String KEY_OPTD= "optd"; //option d
	private static final String KEY_IMAGE = "image";

	// tasks table name
	private static final String TABLE_SCORES = "scores";
	// tasks Table Columns names
	private static final String KEY_IDscores = "idscores";
	private static final String KEY_TIME = "time";
	private static final String KEY_SUBJECTscores = "subjectscores";
	private static final String KEY_SCORE = "score";

	// tasks table settings
	private static final String TABLE_SETTINGS = "settings";
	// tasks Table Columns names
	private static final String KEY_IDsettings = "idsettings";
	private static final String KEY_NAME = "name";

	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		//add table of questions
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SUBJECT + " TEXT, "+ KEY_LEVEL + " TEXT, " +
				KEY_QUES + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD +" TEXT, "+KEY_IMAGE+" TEXT)";
		db.execSQL(sql);		
		addQuestions();

		//add table for scores
		String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORES + " ( "
				+ KEY_IDscores + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TIME + " TEXT, "+ KEY_SUBJECTscores + " TEXT, " +
				KEY_SCORE + " TEXT)";
		db.execSQL(sql2);

		//add table for scores
		String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_SETTINGS + " ( "
				+ KEY_IDsettings + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME +" TEXT)";
		db.execSQL(sql3);
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, "nom de l'utilisateur");
		// Inserting of Replacing Row
		dbase.insert(TABLE_SETTINGS, null, values);
		//db.close();
	}
	//add new name
	public void addName(String newname)
	{
		//SQLiteDatabase db = this.getWritableDatabase();
		dbase=this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, newname);
		// Replacing Row
		dbase.update(TABLE_SETTINGS, values, null, null);
	}
	//get name from db
	public String getName() {
		// Select All Query
		String name = "";
		String selectQuery = "SELECT  * FROM " + TABLE_SETTINGS;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		if (cursor.moveToPosition(0)) { 
			name = cursor.getString(1);
		}
		// return string name
		return name;
	}
	//add new score
	public void addScore(Score newscore)
	{
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TIME, newscore.getTIME());
		values.put(KEY_SUBJECTscores, newscore.getSUBJECTscores());
		values.put(KEY_SCORE, newscore.getSCORE());
		// Inserting Row
		dbase.insert(TABLE_SCORES, null, values);
	}
	public List<Score> getScoresFromSubject(String subjectArg) {
		List<Score> scoreList = new ArrayList<Score>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SCORES;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToPosition(0)) {
			do {
				if (subjectArg.equals(cursor.getString(2))) {   
					Score score = new Score();
					score.setIDscores(cursor.getInt(0));
					score.setTIME(cursor.getString(1));
					score.setSUBJECTscores(cursor.getString(2));
					score.setSCORE(cursor.getString(3));
					scoreList.add(score);
				}
			} while (cursor.moveToNext());
		}
		// return quest list
		return scoreList;
	}
	public List<Score> getScores() {
		List<Score> scoreList = new ArrayList<Score>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SCORES;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToPosition(0)) {
			do {   
				Score score = new Score();
				score.setIDscores(cursor.getInt(0));
				score.setTIME(cursor.getString(1));
				score.setSUBJECTscores(cursor.getString(2));
				score.setSCORE(cursor.getString(3));
				scoreList.add(score);
			} while (cursor.moveToNext());
		}
		// return quest list
		return scoreList;
	}

	private void addQuestions()
	{
		Question q0=new Question("sang/système cardiovasculaire/cellule","1","1","1", "2", "3", "4", "2","none");
		this.addQuestion(q0);
		Question q1=new Question("sang","1","Which company is the largest manufacturer" +
				" of network equipment?","HP", "IBM", "CISCO", "optionD", "CISCO","drawable/compatibilite");
		this.addQuestion(q1);
		Question q2=new Question("sang","1","Which of the following is NOT " +
				"an operating system?", "SuSe", "BIOS", "DOS", "optionD", "BIOS","drawable/compatibilite");
		this.addQuestion(q2);
		Question q3=new Question("sang","1","Which of the following is the fastest" +
				" writable memory?","RAM", "FLASH","Register", "optionD", "Register","none");
		this.addQuestion(q3);
		Question q4=new Question("sang","1","Which of the following device" +
				" regulates internet traffic?",	"Router", "Bridge", "Hub", "optionD", "Router","none");
		this.addQuestion(q4);
		Question q5=new Question("sang","1","Which of the following is NOT an" +
				" interpreted language?","Ruby","Python","BASIC", "optionD", "BASIC","drawable/compatibilite");
		this.addQuestion(q5);
		Question q6=new Question("système cardiovasculaire","1","système cardiovasculaire" +
				" interpreted language?","Ruby","Python","BASIC", "optionD", "BASIC","drawable/compatibilite");
		this.addQuestion(q6);
		Question q7=new Question("cellule","1","Cellule" +
				" interpreted language?","Ruby","Python","BASIC", "optionD", "BASIC","none");
		this.addQuestion(q7);
		Question q8=new Question("cellule","2","Cellule niveau 2" +
				" interpreted language?","Ruby","Python","BASIC", "optionD", "BASIC","none");
		this.addQuestion(q8);
		Question q9=new Question("cellule","3","Cellule niveau 3" +
				" interpreted language?","Ruby","Python","BASIC", "optionD", "BASIC","none");
		this.addQuestion(q9);
		Question q10=new Question("sang","1","Which company is the largest manufacturer" +
				" of network equipment?","HP", "IBM", "CISCO", "optionD", "CISCO","drawable/compatibilite");
		this.addQuestion(q10);
		Question q11=new Question("sang","1","Which company is the largest manufacturer" +
				" of network equipment?","HP", "IBM", "CISCO", "optionD", "CISCO","drawable/compatibilite");
		this.addQuestion(q11);
		Question q12=new Question("sang","1","Which company is the largest manufacturer" +
				" of network equipment?","HP", "IBM", "CISCO", "optionD", "CISCO","drawable/compatibilite");
		this.addQuestion(q12);
		Question q13=new Question("sang","1","Which company is the largest manufacturer" +
				" of network equipment?","HP", "IBM", "CISCO", "optionD", "CISCO","drawable/compatibilite");
		this.addQuestion(q13);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SUBJECT, quest.getSUBJECT());
		values.put(KEY_LEVEL, quest.getLEVEL());
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		values.put(KEY_OPTD, quest.getOPTD());
		values.put(KEY_IMAGE, quest.getIMAGE());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToPosition(1)) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setSUBJECT(cursor.getString(1));
				quest.setLEVEL(cursor.getString(2));
				quest.setQUESTION(cursor.getString(3));
				quest.setANSWER(cursor.getString(4));
				quest.setOPTA(cursor.getString(5));
				quest.setOPTB(cursor.getString(6));
				quest.setOPTC(cursor.getString(7));
				quest.setOPTD(cursor.getString(8));
				quest.setIMAGE(cursor.getString(9));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public List<Question> getQuestionsFromSubject(String subjectArg) {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToPosition(1)) {
			do {
				if (subjectArg.equals(cursor.getString(1))) {
					Question quest = new Question();
					quest.setID(cursor.getInt(0));
					quest.setSUBJECT(cursor.getString(1));
					quest.setLEVEL(cursor.getString(2));
					quest.setQUESTION(cursor.getString(3));
					quest.setANSWER(cursor.getString(4));
					quest.setOPTA(cursor.getString(5));
					quest.setOPTB(cursor.getString(6));
					quest.setOPTC(cursor.getString(7));
					quest.setOPTD(cursor.getString(8));
					quest.setIMAGE(cursor.getString(9));
					quesList.add(quest);
				}
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public List<Question> getQuestionsFromSubjectAndLevel(String subjectArg, int levelArg) {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToPosition(1)) {
			do {
				if (subjectArg.equals(cursor.getString(1)) && levelArg == Integer.parseInt(cursor.getString(2))) {
					Question quest = new Question();
					quest.setID(cursor.getInt(0));
					quest.setSUBJECT(cursor.getString(1));
					quest.setLEVEL(cursor.getString(2));
					quest.setQUESTION(cursor.getString(3));
					quest.setANSWER(cursor.getString(4));
					quest.setOPTA(cursor.getString(5));
					quest.setOPTB(cursor.getString(6));
					quest.setOPTC(cursor.getString(7));
					quest.setOPTD(cursor.getString(8));
					quest.setIMAGE(cursor.getString(9));
					quesList.add(quest);
				}
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public String getSubjects() {
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);

		String dbSubjects = "";
		if (cursor.moveToPosition(0)) {
			dbSubjects = cursor.getString(1);
		}

		return dbSubjects;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}