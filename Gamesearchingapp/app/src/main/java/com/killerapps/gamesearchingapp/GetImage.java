package com.killerapps.gamesearchingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImage extends AsyncTask<String, Void, Bitmap> {
    String loc;
    GetGameDetailsAsyncTask.IData activity;

    public GetImage(String loc, GetGameDetailsAsyncTask.IData activity) {
        this.loc = loc;
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream in = null;
        try {
            URL url = new URL(loc);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            in = con.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(in);
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected  void onPostExecute(Bitmap result) {
//        Log.d("PostEx" , "at post execute");
        activity.setupGameImage(result);
    }
}
