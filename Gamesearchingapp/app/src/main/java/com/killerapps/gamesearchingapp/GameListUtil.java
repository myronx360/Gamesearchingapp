package com.killerapps.gamesearchingapp;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * HW # 5
 * GameListUtil.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GameListUtil {
    static public class GameListSaxParser extends DefaultHandler {
        ArrayList<GameList> gameArrayList;
        GameList gameList;
        StringBuilder xmlInnerTest;
        String LOG = "DEBUG";
        String TAG = "DEBUG";

        static public ArrayList<GameList> parseGameList(InputStream in) throws IOException, SAXException {
            GameListSaxParser parser = new GameListSaxParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);
            return parser.gameArrayList;
        }
        public ArrayList<GameList> getGameArrayList() {
            return gameArrayList;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlInnerTest = new StringBuilder();
            gameArrayList = new ArrayList<>();

        }
        //TODO: Handle when there is no release date
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("Game")){
                gameList = new GameList();
                // set object attributes
                // object.setId(attributes.getValue("id");
                gameList.setId(attributes.getValue("id"));
                gameList.setGameTitle(attributes.getValue("GameTitle"));
                gameList.setReleaseDate(attributes.getValue("ReleaseDate"));
                gameList.setPlatform(attributes.getValue("Platform"));
                Log.d(LOG, "set Att");
//                Log.d(LOG, news.getTitle());
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if(localName.equals("Game")){
                gameArrayList.add(gameList);
                Log.d(LOG, "ADD gamelist to list");
                Log.d(LOG, gameList.getGameTitle());
            }else if(localName.equals("id")) {
                //object.setName(xmlInnerTest.toString().trim());
                if(xmlInnerTest.toString() != null) {
                    Log.d(TAG, xmlInnerTest.toString());
                    gameList.setId(xmlInnerTest.toString());
                }else{
                    Log.d(TAG, "set id == null ");
                }
            }else if(localName.equals("GameTitle")) {

                gameList.setGameTitle(xmlInnerTest.toString());
            }else if(localName.equals("ReleaseDate")){
                if(xmlInnerTest.toString().isEmpty()){
                    gameList.setReleaseDate("??/??/????");
                }else {
                    gameList.setReleaseDate(xmlInnerTest.toString());
                }
            }else if(localName.equals("Platform")){
                gameList.setPlatform(xmlInnerTest.toString());
            }
            xmlInnerTest.setLength(0);
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerTest.append(ch, start, length);
        }
    }

}