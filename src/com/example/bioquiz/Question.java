package com.example.bioquiz;

public class Question {
	private int ID;
	private String SUBJECT;
	private String LEVEL;
	private String QUESTION;
	private String OPTA;
	private String OPTB;
	private String OPTC;
	private String OPTD;
	private String ANSWER;
	public Question()
	{
		ID=0;
		SUBJECT="";
		LEVEL="";
		QUESTION="";
		OPTA="";
		OPTB="";
		OPTC="";
		OPTD="";
		ANSWER="";
	}
	public Question(String sUBJECT, String lEVEL, String qUESTION, String oPTA, String oPTB, String oPTC, String oPTD,
			String aNSWER) {
		
		SUBJECT = sUBJECT;
		LEVEL = lEVEL;
		QUESTION = qUESTION;
		OPTA = oPTA;
		OPTB = oPTB;
		OPTC = oPTC;
		OPTD = oPTD;
		ANSWER = aNSWER;
	}
	public int getID()
	{
		return ID;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getOPTA() {
		return OPTA;
	}
	public String getOPTB() {
		return OPTB;
	}
	public String getOPTC() {
		return OPTC;
	}
	public String getOPTD() {
		return OPTD;
	}
	public String getANSWER() {
		return ANSWER;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public void setLEVEL(String lEVEL) {
		LEVEL = lEVEL;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setOPTA(String oPTA) {
		OPTA = oPTA;
	}
	public void setOPTB(String oPTB) {
		OPTB = oPTB;
	}
	public void setOPTC(String oPTC) {
		OPTC = oPTC;
	}
	public void setOPTD(String oPTD) {
		OPTD = oPTD;
	}
	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}
	
}