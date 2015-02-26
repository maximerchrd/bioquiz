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
	// tasks Table Columns names for the question table
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
	private static final String KEY_TRIAL1 = "questscore1"; //good answer on first trial
	private static final String KEY_TRIAL2 = "questscore2";
	private static final String KEY_TRIAL3 = "questscore3";
	private static final String KEY_TRIAL4 = "questscore4";

	// tasks table name
	private static final String TABLE_SCORES = "scores";
	// tasks Table Columns names for the score table
	private static final String KEY_IDscores = "idscores";
	private static final String KEY_TIME = "time";
	private static final String KEY_SUBJECTscores = "subjectscores";
	private static final String KEY_SCORE = "score";
	private static final String KEY_LEVELscore = "levelscore";

	// tasks table settings
	private static final String TABLE_SETTINGS = "settings";
	// tasks Table Columns names for the settings table
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
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD +" TEXT, "+KEY_TRIAL1 +" TEXT, "
				+KEY_TRIAL2 +" TEXT, "+KEY_TRIAL3+" TEXT, "+KEY_TRIAL4 +" TEXT,"+KEY_IMAGE+" TEXT)";
		db.execSQL(sql);		
		addQuestions();

		//add table for scores
		String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORES + " ( "
				+ KEY_IDscores + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TIME + " TEXT, "+ KEY_SUBJECTscores + " TEXT, " +
				KEY_SCORE + " TEXT, " +	KEY_LEVELscore +" TEXT)";
		db.execSQL(sql2);

		//add table for scores
		String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_SETTINGS + " ( "
				+ KEY_IDsettings + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME +" TEXT)";
		db.execSQL(sql3);
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, "Anonyme");
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
		values.put(KEY_LEVELscore, newscore.getLEVEL());
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
					score.setLEVEL(cursor.getString(4));
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
				score.setLEVEL(cursor.getString(4));
				scoreList.add(score);
			} while (cursor.moveToNext());
		}
		// return quest list
		return scoreList;
	}

	private void addQuestions()
	{
		Question q0=new Question("système cardiovasculaire/électricité","1","0","0","0","0", "0", "0","none");
		this.addQuestion(q0);
		Question q1=new Question("système cardiovasculaire","2","Quel gaz contient le sang qui circule dans" +
				" la partie gauche du coeur?","azote","oxygène","gaz hilarant", "gaz carbonique", "oxygène","drawable/circulation2");
		this.addQuestion(q1);
		Question q2=new Question("système cardiovasculaire","2","Quel gaz contient le sang qui circule dans" +
				" l'artère pulmonaire?","protoxyde d'azote","oxygène","vapeur d'eau", "gaz carbonique", "gaz carbonique","drawable/circulation2");
		this.addQuestion(q2);
		Question q3=new Question("système cardiovasculaire","2","Quel gaz contient le sang qui circule dans" +
				" les veines pulmonaires?","azote","oxygène","argon", "gaz carbonique", "oxygène","drawable/circulation2");
		this.addQuestion(q3);
		Question q4=new Question("système cardiovasculaire","2","Quel gaz contient le sang qui circule dans" +
				" l'aorte?","azote","oxygène","gaz hilarant", "gaz carbonique", "oxygène","drawable/circulation2");
		this.addQuestion(q4);
		//Question q5=new Question("système cardiovasculaire","2","Que se passe-t-il dans les poumons?",
		//		"Le gaz carbonique du sang est échangé contre de l'oxygène","L'oxygène du sang est échangé contre du gaz carbonique","La respiration cellulaire 'donne' de l'oxygène au sang et 'prend' du gaz carbonique", "La respiration cellulaire 'donne' du gaz carbonique au sang et 'prend' de l'oxygène", "Le gaz carbonique du sang est échangé contre de l'oxygène","drawable/circulation2");
		//this.addQuestion(q5);
		//Question q6=new Question("système cardiovasculaire","2","Que se passe-t-il dans les muscles?",
		//		"Le gaz carbonique du sang est échangé contre de l'oxygène","La photosynthèse 'donne' de l'oxygène au sang et 'prend' du gaz carbonique","La respiration cellulaire 'prend' du gaz carbonique du sang et 'donne' de l'oxygène", "La respiration cellulaire 'prend' de l'oxygène du sang et 'donne' du gaz carbonique", "La respiration cellulaire 'prend' de l'oxygène du sang et 'donne' du gaz carbonique","drawable/circulation2");
		//this.addQuestion(q6);
		Question q7=new Question("système cardiovasculaire","1","Que représente le numéro 1 sur cette image?","coeur","poumons","muscle", "intestin grêle", "coeur","drawable/circulation1");
		this.addQuestion(q7);
		Question q8=new Question("système cardiovasculaire","1","Que représente le numéro 2 sur cette image?","coeur","poumons","muscle", "intestin grêle", "poumons","drawable/circulation1");
		this.addQuestion(q8);
		Question q9=new Question("système cardiovasculaire","1","Que représente le numéro 3 sur cette image?","cerveau","poumons","muscle", "intestin grêle", "cerveau","drawable/circulation1");
		this.addQuestion(q9);
		Question q10=new Question("système cardiovasculaire","1","Quel numéro n'est pas alimenté par la grande circulation?","1","2","3","4", "2","drawable/circulation1");
		this.addQuestion(q10);
		Question q11=new Question("système cardiovasculaire","1","Quel gaz contient le sang qui circule dans" +
				" la partie droite du coeur?","azote","oxygène","gaz hilarant", "gaz carbonique", "gaz carbonique","drawable/circulation2");
		this.addQuestion(q11);
		Question q12=new Question("système cardiovasculaire","1","Quel élément de la respiration cellulaire est symbolisé par la lettre A","oxygène","gaz carbonique","lumière","eau", "oxygène","drawable/respiration_cellulaire");
		this.addQuestion(q12);
		/*Question q13=new Question("système cardiovasculaire","1","Quel élément de la respiration cellulaire est symbolisé par la lettre B","oxygène","gaz carbonique","lumière","eau", "gaz carbonique","drawable/respiration_cellulaire");
		this.addQuestion(q13);
		Question q14=new Question("système cardiovasculaire","1","Que représente le numéro 1 sur cette image du coeur?","oreillette droite","oreillette gauche","ventricule droit","ventricule gauche", "oreillette droite","drawable/schema_coeur");
		this.addQuestion(q14);
		Question q15=new Question("système cardiovasculaire","1","Que représente le numéro 2 sur cette image du coeur?","oreillette droite","oreillette gauche","ventricule droit","ventricule gauche", "ventricule droit","drawable/schema_coeur");
		this.addQuestion(q15);
		Question q16=new Question("système cardiovasculaire","1","Que représente le numéro 3 sur cette image du coeur?","oreillette droite","oreillette gauche","ventricule droit","ventricule gauche", "oreillette gauche","drawable/schema_coeur");
		this.addQuestion(q16);
		Question q17=new Question("système cardiovasculaire","1","Que représente le numéro 4 sur cette image du coeur?","oreillette droite","oreillette gauche","ventricule droit","ventricule gauche", "ventricule gauche","drawable/schema_coeur");
		this.addQuestion(q17);
		Question q18=new Question("système cardiovasculaire","1","Que désigne le numéro 1 sur cette image?","veine","capillaires","artère","valvule", "veine","drawable/capillaires");
		this.addQuestion(q18);
		Question q19=new Question("système cardiovasculaire","1","Que désigne le numéro 2 sur cette image?","veine","capillaires","artère","valvule", "capillaires","drawable/capillaires");
		this.addQuestion(q19);
		Question q20=new Question("système cardiovasculaire","1","Que désigne le numéro 3 sur cette image?","veine","capillaires","artère","valvule", "artère","drawable/capillaires");
		this.addQuestion(q20);
		Question q21=new Question("système cardiovasculaire","1","Les artères ...","... partent du coeur","... arrivent au coeur","... contiennent du sang oxygéné","... contiennent du sang avec du gaz carbonique", "... partent du coeur","none");
		this.addQuestion(q21);
		Question q22=new Question("système cardiovasculaire","2","Laquelle de ces affirmations est exacte?","Les veines partent du coeur","Les artères contiennent des valvules","La paroi des veines est plus mince que celle des artères","Les capillaires ne se trouvent que dans les poumons", "La paroi des veines est plus mince que celle des artères","none");
		this.addQuestion(q22);
		Question q23=new Question("système cardiovasculaire","3","Que représente la zone du graphique désignée par la lettre B","la systole auriculaire","la systole ventriculaire","la diastole auriculaire et ventriculaire","la diastole ventriculaire", "la systole ventriculaire","drawable/ecg");
		this.addQuestion(q23);
		Question q24=new Question("système cardiovasculaire","3","Que représente la zone du graphique désignée par la lettre A","la systole auriculaire","la systole ventriculaire","la diastole auriculaire et ventriculaire","la diastole ventriculaire", "la systole auriculaire","drawable/ecg");
		this.addQuestion(q24);
		Question q25=new Question("système cardiovasculaire","3","Que représente la zone du graphique désignée par la lettre C","la systole auriculaire","la systole ventriculaire","la diastole auriculaire et ventriculaire","la diastole ventriculaire", "la diastole auriculaire et ventriculaire","drawable/ecg");
		this.addQuestion(q25);
		Question q26=new Question("système cardiovasculaire","3","Que représente ce graphique?","La fréquence cardiaque d'un coureur en fonction de sa vitesse","La vitesse d'un coureur en fonction de sa fréquence cardiaque","La fréquence cardiaque d'un coureur en fonction du temps","La vitesse d'un coureur en fonction de la distance", "La fréquence cardiaque d'un coureur en fonction de sa vitesse","drawable/fc");
		this.addQuestion(q26);
		Question q27=new Question("système cardiovasculaire","3","À quelle vitesse le coureur représenté par la courbe rouge atteint-il sa fréquence cardiaque maximale?","11 km/h","8 km/h","10 km/h","20 km/h", "11 km/h","drawable/fc");
		this.addQuestion(q27);
		Question q28=new Question("système cardiovasculaire","3","Quelle courbe représente le coureur le mieux entraîné?","La courbe rouge","La courbe bleue","La courbature","La courbe de croissance", "La courbe bleue","drawable/fc");
		this.addQuestion(q28);
		Question q29=new Question("système cardiovasculaire","1","Que représente le numéro 1 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","Les poumons","drawable/circul_schema_simple");
		this.addQuestion(q29);
		Question q30=new Question("système cardiovasculaire","1","Que représente le numéro 2 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","Les autres organes (muscles, cerveau, etc.)","drawable/circul_schema_simple");
		this.addQuestion(q30);
		Question q31=new Question("système cardiovasculaire","1","Que représente le numéro 3 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","La partie droite du coeur","drawable/circul_schema_simple");
		this.addQuestion(q31);
		Question q32=new Question("système cardiovasculaire","1","Que représente le numéro 4 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","La partie gauche du coeur","drawable/circul_schema_simple");
		this.addQuestion(q32);
		Question q33=new Question("système cardiovasculaire","2","Que représente le numéro 2 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","Les poumons","drawable/circul_schema_simple2");
		this.addQuestion(q33);
		Question q34=new Question("système cardiovasculaire","2","Que représente le numéro 3 sur ce schéma de la circulation sanguine?","Les poumons","La partie droite du coeur","La partie gauche du coeur", "Les autres organes (muscles, cerveau, etc.)","La partie gauche du coeur","drawable/circul_schema_simple2");
		this.addQuestion(q34);*/
		Question q35=new Question("système cardiovasculaire","1","La diastole correspond...","à la contraction des muscles du coeur","au relâchement des muscles du coeur","-", "-","au relâchement des muscles du coeur","drawable/circul_schema_simple2");
		this.addQuestion(q35);
		Question q36=new Question("système cardiovasculaire","1","La systole correspond...","à la contraction des muscles du coeur","au relâchement des muscles du coeur","-", "-","à la contraction des muscles du coeur","drawable/circul_schema_simple2");
		this.addQuestion(q36);
		Question el1=new Question("électricité","1","Qu'est-ce qui crée le courant électrique?","Le mouvement d'électrons","Le mouvement de protons","Le mouvement d'atomes de métal", "Le mouvement d'atomes d'oxygène","Le mouvement d'électrons","none");
		this.addQuestion(el1);
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
		values.put(KEY_TRIAL1, "0");
		values.put(KEY_TRIAL2, "0");
		values.put(KEY_TRIAL3, "0");
		values.put(KEY_TRIAL4, "0");
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
				quest.setTRIAL1(cursor.getString(9));
				quest.setTRIAL2(cursor.getString(10));
				quest.setTRIAL3(cursor.getString(11));
				quest.setTRIAL4(cursor.getString(12));
				quest.setIMAGE(cursor.getString(13));  //13, because the trials are between OPTD and IMAGE
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
					quest.setTRIAL1(cursor.getString(9));
					quest.setTRIAL2(cursor.getString(10));
					quest.setTRIAL3(cursor.getString(11));
					quest.setTRIAL4(cursor.getString(12));
					quest.setIMAGE(cursor.getString(13)); //13, because the trials are between OPTD and IMAGE
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
					quest.setTRIAL1(cursor.getString(9));
					quest.setTRIAL2(cursor.getString(10));
					quest.setTRIAL3(cursor.getString(11));
					quest.setTRIAL4(cursor.getString(12));
					quest.setIMAGE(cursor.getString(13)); //13, because the trials are between OPTD and IMAGE
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
	public void incrementTrialNFromQuestion(int trial, Question question) {
		dbase=this.getReadableDatabase();
		int questionID = question.getID();
		ContentValues values = new ContentValues();
		if (trial == 1) {
			int trialTotal = Integer.parseInt(question.getTRIAL1());
			trialTotal++;
			values.put(KEY_TRIAL1, trialTotal);
			String[] questionIDArray = new String[1];
			questionIDArray[0] = Integer.toString(questionID);
			dbase.update(TABLE_QUEST, values, "id = ?", questionIDArray);
		} else if (trial == 2) {
			int trialTotal = Integer.parseInt(question.getTRIAL2());
			trialTotal++;
			values.put(KEY_TRIAL2, trialTotal);
			String[] questionIDArray = new String[1];
			questionIDArray[0] = Integer.toString(questionID);
			dbase.update(TABLE_QUEST, values, "id = ?", questionIDArray);
		} else if (trial == 3) {
			int trialTotal = Integer.parseInt(question.getTRIAL3());
			trialTotal++;
			values.put(KEY_TRIAL3, trialTotal);
			String[] questionIDArray = new String[1];
			questionIDArray[0] = Integer.toString(questionID);
			dbase.update(TABLE_QUEST, values, "id = ?", questionIDArray);
		} else if (trial == 4) {
			int trialTotal = Integer.parseInt(question.getTRIAL4());
			trialTotal++;
			values.put(KEY_TRIAL4, trialTotal);
			String[] questionIDArray = new String[1];
			questionIDArray[0] = Integer.toString(questionID);
			dbase.update(TABLE_QUEST, values, "id = ?", questionIDArray);
		}
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
