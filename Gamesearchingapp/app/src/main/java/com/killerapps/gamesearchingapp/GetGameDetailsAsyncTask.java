package com.killerapps.gamesearchingapp;/**
 * Assignment
 * GetGameDetailsAsyncTask.${FILE_TYPE}
 * Created by Myron Williams on 2/18/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.Html;
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
 * HW # 5
 * * GetGameDetailsAsyncTask.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GetGameDetailsAsyncTask extends AsyncTask<String, Void, GameDetail> {
    private final String TAG = "DEBUG";
    GetGameDetailsAsyncTask.IData activity;
    public GetGameDetailsAsyncTask(GetGameDetailsAsyncTask.IData activity){this.activity = activity;}

    @Override
    protected GameDetail doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            Log.d(TAG, "doInBackground: "+ statusCode);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return  GameDetailUtil.GameDetailSaxParser.parseGameDetail(in);
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
    protected void onPostExecute(GameDetail result) {
        if (result == null) {
            Log.d(TAG, "onPostExecute: null");
        }else{
            Log.d(TAG, "onPostExecute: " + result.toString());
            activity.setupGameData(result);
        }

    }


    static public interface IData {
        public void setupGameData(GameDetail gameDetails);
        public void setupGameImage(Bitmap image);
        public Context getContext();
    }
}