package com.example.sciquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class ScoresActivity extends Activity {
 
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Button backToMenuButton;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
 
        backToMenuButton = (Button)findViewById(R.id.button1);
		//listener for "back to menu" button
		backToMenuButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        DbHelper db = new DbHelper(this);
 
        // Adding child data
        //listDataHeader.add("sang");
        listDataHeader.add("système cardiovasculaire");
        //listDataHeader.add("cellule");
 
        // Adding child data
        /*List<Score> scoresSang;
        scoresSang = db.getScoresFromSubject("sang");
        List<String> sang = new ArrayList<String>();
        sang.add("Date                     Score");
        //sort list according to score
        for (int i = 0; i < scoresSang.size() && i < 5; i++) {
        	Score maxscore = scoresSang.get(i);
        	for (int j = i+1; j < scoresSang.size(); j++) {
        		if (Integer.parseInt(scoresSang.get(j).getSCORE()) > Integer.parseInt(maxscore.getSCORE())) {
        			maxscore = scoresSang.get(j);
        			scoresSang.set(j, scoresSang.get(i));
        			scoresSang.set(i, maxscore);
        		}
        	}
        	String time = scoresSang.get(i).getTIME();
        	String upToNCharacters = time.substring(0, Math.min(time.length(), 10));
        	sang.add(upToNCharacters+"       "+scoresSang.get(i).getSCORE());
        }*/
 
     // Adding child data
        List<Score> scoresSysCar;
        scoresSysCar = db.getScoresFromSubject("système cardiovasculaire");
        List<String> sysCar = new ArrayList<String>();
        sysCar.add("Date                     Niveau             Score");
        //sort list according to score
        for (int i = 0; i < scoresSysCar.size() && i < 5; i++) {
        	Score maxscore = scoresSysCar.get(i);
        	for (int j = i+1; j < scoresSysCar.size(); j++) {
        		if (Integer.parseInt(scoresSysCar.get(j).getSCORE()) > Integer.parseInt(maxscore.getSCORE())) {
        			maxscore = scoresSysCar.get(j);
        			scoresSysCar.set(j, scoresSysCar.get(i));
        			scoresSysCar.set(i, maxscore);
        		}
        	}
        	String time = scoresSysCar.get(i).getTIME();
        	String upToNCharacters = time.substring(0, Math.min(time.length(), 10));
        	sysCar.add(upToNCharacters+"       "+scoresSysCar.get(i).getLEVEL()+"                        "+scoresSysCar.get(i).getSCORE());
        }
 
     // Adding child data
        /*List<Score> scoresCellule;
        scoresCellule = db.getScoresFromSubject("cellule");
        List<String> cellule = new ArrayList<String>();
        cellule.add("Date                     Score");
        //sort list according to score
        for (int i = 0; i < scoresCellule.size() && i < 5; i++) {
        	Score maxscore = scoresCellule.get(i);
        	for (int j = i+1; j < scoresCellule.size(); j++) {
        		if (Integer.parseInt(scoresCellule.get(j).getSCORE()) > Integer.parseInt(maxscore.getSCORE())) {
        			maxscore = scoresCellule.get(j);
        			scoresCellule.set(j, scoresCellule.get(i));
        			scoresCellule.set(i, maxscore);
        		}
        	}
        	cellule.add(scoresCellule.get(i).getTIME()+" score: "+scoresCellule.get(i).getSCORE());
        }*/
 
        //listDataChild.put(listDataHeader.get(0), sang); // Header, Child data
        listDataChild.put(listDataHeader.get(0), sysCar);
        //listDataChild.put(listDataHeader.get(2), cellule);
    }
}
