package com.killerapps.gamesearchingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * HW # 5
 * MainActivity.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class MainActivity extends AppCompatActivity implements GetGameListAsyncTask.IData, GetGameDetailsAsyncTask.IData{
    final String TAG = "Debug";
    private final String GET_GAME_URL = "http://thegamesdb.net/api/GetGamesList.php?name=";
    private String gameName;
    private ArrayList<GameList> gameArrayList;
    private ProgressBar progressBar;
    private LinearLayout mainLayout;
    private Button goBtn;
    ScrollView mainScroller;
    LinearLayout scrollLayout;
    RadioGroup radioGroup;
    private String selectedGameId;
    private final String GET_GAME_DETAILS = "http://thegamesdb.net/api/GetGame.php?id=";
    private GameDetail currentGameDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goBtn = (Button) findViewById(R.id.btnGo);
        mainScroller = (ScrollView) findViewById(R.id.scollViewMain);
        scrollLayout = (LinearLayout) findViewById(R.id.layoutScroll);
//        goBtn.setEnabled(false);
    }

    public void getSearchName(){

        EditText etName = (EditText) findViewById(R.id.etName);
        if(etName.getText() != null) {
            gameName = etName.getText().toString();
        }else{
            Toast.makeText(this, "Enter search name", Toast.LENGTH_LONG).show();
        }
    }

    public String concatURL(String url, String param){
        return url.concat(param);
    }

    public void search(View view){
        getSearchName();
        Log.d(TAG, "search: " + concatURL(GET_GAME_URL, gameName));
        setUpProgressSpinner();
        new GetGameListAsyncTask(MainActivity.this).execute(concatURL(GET_GAME_URL, gameName));
    }

    private void setUpProgressSpinner(){
        toggleDisplayScrollView();
        progressBar = new ProgressBar(MainActivity.this);
        mainLayout = (LinearLayout) findViewById(R.id.layoutMain);

        mainLayout.addView(progressBar);

    }

    private void toggleDisplayScrollView(){

        if(mainScroller.getVisibility() == View.GONE){
            mainScroller.setVisibility(View.VISIBLE);
        }else {
            mainScroller.setVisibility(View.GONE);
        }
    }

    private void setUpScrollView(){
        radioGroup = new RadioGroup(MainActivity.this);

        for (GameList game:gameArrayList) {
            RadioButton radioButton = new RadioButton(MainActivity.this);
            radioButton.setText(game.toString());
            radioGroup.addView(radioButton);
            Log.d(TAG, "setUpScrollView: " + game.toString());
        }
        scrollLayout.addView(radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d(TAG, "onCheckedChanged: " + gameArrayList.get(i-1).toString());
                selectedGameId = gameArrayList.get(i-1).getId();
            }
        });
    }



    public void goButton(View view){
        setUpProgressSpinner();
//        new GetGameListAsyncTask(MainActivity.this).cancel(true);
        new GetGameDetailsAsyncTask(MainActivity.this).execute(concatURL(GET_GAME_DETAILS, selectedGameId));
    }





    @Override
    public void setupData(ArrayList<GameList> gameLists) {

        gameArrayList = gameLists;

        progressBar.setVisibility(View.GONE);
        toggleDisplayScrollView();
        goBtn.setEnabled(true);
        setUpScrollView();
    }

    @Override
    public void setupGameData(GameDetail gameDetail) {

        currentGameDetail = gameDetail;

        progressBar.setVisibility(View.GONE);
        toggleDisplayScrollView();
        //TODO: GOTO next activity
        Log.d(TAG, "setupGameData: GOTO NEXT ACTIVITY");
    }

    @Override
    public void setupGameImage(Bitmap image) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
