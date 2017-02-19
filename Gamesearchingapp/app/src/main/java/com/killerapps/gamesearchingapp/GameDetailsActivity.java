package com.killerapps.gamesearchingapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameDetailsActivity extends AppCompatActivity{
    private GameDetail gameDetail;
    private Bitmap gameImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);


        // get data
        if(getIntent().getExtras().getSerializable(MainActivity.KEY_DETAIL) != null){
            gameDetail = (GameDetail) getIntent().getExtras().getSerializable(MainActivity.KEY_DETAIL);
        }
        // get Img
        if(getIntent().getExtras().get(MainActivity.KEY_IMG) != null){
            gameImg = (Bitmap) getIntent().getExtras().get(MainActivity.KEY_IMG);
        }

        //show data
        TextView gameName = (TextView) findViewById(R.id.txtGameName);
        gameName.setText(gameDetail.getGameTitle());


        LinearLayout layoutGameDetail = (LinearLayout)findViewById(R.id.layoutScrollGameDetail);
        TextView overview = new TextView(GameDetailsActivity.this);
        overview.setText(gameDetail.getOverview());
        layoutGameDetail.addView(overview);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(gameImg);

//        TextView gameGene = (TextView) findViewById(R.id.txtGenre);
//        gameGene.setText("Genre: " + gameDetail.getGenre().toString());

        TextView gamePub = (TextView) findViewById(R.id.txtPublisher);
        gamePub.setText("Publisher: " + gameDetail.getPublisher());
    }

    public void playTrailer(View view){
        WebView webView = new WebView(GameDetailsActivity.this);
        webView.loadUrl(gameDetail.getBaseImgUrl());
        LinearLayout layoutGameDetail = (LinearLayout)findViewById(R.id.layoutGameDetail);
        layoutGameDetail.addView(webView);
    }

    public void showSimilarGames(View view){

    }

    public void finish(){finish();}
}
