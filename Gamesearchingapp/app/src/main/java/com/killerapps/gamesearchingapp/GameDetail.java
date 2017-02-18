package com.killerapps.gamesearchingapp;/**
 * Assignment
 * GameDetail.${FILE_TYPE}
 * Created by Myron Williams on 2/18/2017.
 */

import java.util.ArrayList;

/**
 * HW # In_Class # 
 * GameDetail.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GameDetail {
    private String baseImgUrl;
    private String gameTitle;
    private String overview;
    private ArrayList<String> genre;
    private String publisher;
    private ArrayList<String> similarGames;

    @Override
    public String toString() {
        return "GameDetail{" +
                "baseImgUrl='" + baseImgUrl + '\'' +
                ", gameTitle='" + gameTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", similarGames=" + similarGames +
                '}';
    }

    public String getBaseImgUrl() {
        return baseImgUrl;
    }

    public void setBaseImgUrl(String baseImgUrl) {
        this.baseImgUrl = baseImgUrl;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public void addGenre(String g){
        genre.add(g);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ArrayList<String> getSimilarGames() {
        return similarGames;
    }

    public void setSimilarGames(ArrayList<String> similarGames) {
        this.similarGames = similarGames;
    }

    public void addSimilarGames(String s) {
        similarGames.add(s);
    }
}