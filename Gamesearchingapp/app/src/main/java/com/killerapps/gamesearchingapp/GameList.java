package com.killerapps.gamesearchingapp;

/**
 * HW # 5
 * GameList.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GameList {
    private String id;
    private String gameTitle;
    private String releaseDate = "??/??/????";
    private String platform;


    @Override
    public String toString() {
        if(releaseDate.isEmpty()){
            setReleaseDate("??/??/????");
        }
        return gameTitle + ". " + "Released in " + releaseDate + ". "+
                "Platform: " + platform + '.';
    }
//    @Override
//    public String toString() {
//        return "GameList{" +
//                "id='" + id + '\'' +
//                ", gameTitle='" + gameTitle + '\'' +
//                ", releaseDate='" + releaseDate + '\'' +
//                ", platform='" + platform + '\'' +
//                '}';
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}