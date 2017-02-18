package com.killerapps.gamesearchingapp;/**
 * Assignment
 * GetGameListAsyncTask.${FILE_TYPE}
 * Created by Myron Williams on 2/18/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * HW # In_Class # 
 * GetGameListAsyncTask.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GetGameListAsyncTask extends AsyncTask<String, Void, ArrayList<GameList>>{
    private final String TAG = "DEBUG";
    IData activity;
    public GetGameListAsyncTask(IData activity){this.activity = activity;}

    @Override
    protected ArrayList<GameList> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return  GameListUtil.GameListSaxParser.parseGameList(in);
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<GameList> result) {
        if (result == null) {
            Log.d(TAG, "onPostExecute: null");
        }else{
            Log.d(TAG, "onPostExecute: " + result.toString());
            activity.setupData(result);
        }

    }


    static public interface IData {
        public void setupData(ArrayList<GameList> gameLists);
//        public void setupImage(Bitmap image);
        public Context getContext();
    }
}