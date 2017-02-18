package com.killerapps.gamesearchingapp;/**
 * Assignment
 * GameDetailUtil.${FILE_TYPE}
 * Created by Myron Williams on 2/18/2017.
 */

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * HW # In_Class # 
 * GameDetailUtil.java
 * Created By: Myron Williams on 2/18/2017.
 */
public class GameDetailUtil {
    static public class GameDetailSaxParser extends DefaultHandler {
//        ArrayList<GameDetail> gameArrayList;
        GameDetail gameDetail;
        StringBuilder xmlInnerTest;
        String LOG = "DEBUG";
        String TAG = "DEBUG";

        static public GameDetail parseGameDetail(InputStream in) throws IOException, SAXException {
            GameDetailUtil.GameDetailSaxParser parser = new GameDetailSaxParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);
            return parser.gameDetail;
        }
        public GameDetail getGameDetail() {
            return gameDetail;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlInnerTest = new StringBuilder();
//            gameArrayList = new ArrayList<>();

        }
        //TODO: Handle when there is no release date
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("Data")){
                gameDetail = new GameDetail();
                // set object attributes
                // object.setId(attributes.getValue("id");
                gameDetail.setBaseImgUrl(attributes.getValue("baseImgUrl"));
                gameDetail.setGameTitle(attributes.getValue("GameTitle"));
                gameDetail.setOverview(attributes.getValue("Overview"));
                gameDetail.addGenre(attributes.getValue("genre"));
                gameDetail.setPublisher(attributes.getValue("Publisher"));
                gameDetail.addSimilarGames(attributes.getValue("Similar"));
                Log.d(LOG, "set Att");
//                Log.d(LOG, news.getTitle());
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if(localName.equals("baseImgUrl")){
               gameDetail.setBaseImgUrl(xmlInnerTest.toString());
            }else if(localName.equals("GameTitle")) {
                //object.setName(xmlInnerTest.toString().trim());
                gameDetail.setGameTitle(xmlInnerTest.toString());
            }else if(localName.equals("Overview")) {

                gameDetail.setOverview(xmlInnerTest.toString());
            }else if(localName.equals("genre")){
                //TODO: Handle multiple Genres
                gameDetail.addGenre(xmlInnerTest.toString());
            }else if(localName.equals("Publisher")){
                gameDetail.setPublisher(xmlInnerTest.toString());
            }//TODO: Handle adding similar Games
//            else if(localName.equals("Similar")){
//                gameDetail.addSimilarGames(xmlInnerTest.toString());
//            }
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