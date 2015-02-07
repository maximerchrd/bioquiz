package com.example.bioquiz;

public class Score {
	private int IDscores;
	//private String DATE;
	private String TIME;
	private String SUBJECTscores;
	private String SCORE;
	
	public Score()
	{
		IDscores = 0;
		//DATE = "";
		TIME = "";
		SUBJECTscores = "";
		SCORE = "";
	}
	public Score(/*String dATE,*/ String tIME, String sUBJECTscores, String sCORE) {
		//DATE = dATE;
		TIME = tIME;
		SUBJECTscores = sUBJECTscores;
		SCORE = sCORE;
	}
	public int getIDscores()
	{
		return IDscores;
	}
	/*public String getDATE() {
		return DATE;
	}*/
	public String getTIME() {
		return TIME;
	}
	public String getSUBJECTscores() {
		return SUBJECTscores;
	}
	public String getSCORE() {
		return SCORE;
	}
	public void setIDscores(int iDscores) {
		IDscores = iDscores;
	}
	/*public String setDATE(String dATE) {
		return DATE;
	}*/
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public void setSUBJECTscores(String sUBJECTscores) {
		SUBJECTscores = sUBJECTscores;
	}
	public void setSCORE(String sCORE) {
		SCORE = sCORE;
	}
}
