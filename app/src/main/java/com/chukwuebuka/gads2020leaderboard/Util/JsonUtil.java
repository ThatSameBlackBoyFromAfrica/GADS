package com.chukwuebuka.gads2020leaderboard.Util;

import android.util.Log;

import com.chukwuebuka.gads2020leaderboard.data.LearningLeader;
import com.chukwuebuka.gads2020leaderboard.data.SkillLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonUtil {

    private JsonUtil() {}

    private static final String BASE_URI = "https://gadsapi.herokuapp.com";
    private static final String LEARNING_LEADERS = "/api/hours";
    private static final String SKILL_IQ_LEADERS = "/api/skilliq";

    public static String getJsonLearningLeader(){
        URL url = null;
        try {
            url = new URL(BASE_URI + LEARNING_LEADERS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connection = null;

        try {
            if (url != null){
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                Scanner scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                boolean hasData = scanner.hasNext();
                if (hasData){
                    return scanner.next();
                } else return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String getJsonSkillLeader(){
        URL url = null;
        try {
            url = new URL(BASE_URI + SKILL_IQ_LEADERS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connection = null;

        try {
            if (url != null){
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                Scanner scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                boolean hasData = scanner.hasNext();
                if (hasData){
                    return scanner.next();
                } else return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static ArrayList<LearningLeader> getLearningLeaderFromJson(String json){
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";

        ArrayList<LearningLeader> learningLeaders = new ArrayList<>();
        try {
            JSONArray jsonLearningLeaderArray = new JSONArray(json);
            int numberOfLearners = jsonLearningLeaderArray.length();
            for (int i = 0; i < numberOfLearners; i++){
                JSONObject jsonLearnerObject = jsonLearningLeaderArray.getJSONObject(i);
                String name = jsonLearnerObject.getString(NAME);
                String hour = jsonLearnerObject.getString(HOURS).toString();
                String country = jsonLearnerObject.getString(COUNTRY);

                LearningLeader learningLeader = new LearningLeader(name, hour, country);
                learningLeaders.add(learningLeader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return learningLeaders;
    }


    public static ArrayList<SkillLeader> getIqSkillLeaderFromJson(String json){
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";

        ArrayList<SkillLeader> skillLeaders = new ArrayList<>();
        try {
            JSONArray jsonLearningLeaderArray = new JSONArray(json);
            int numberOfLearners = jsonLearningLeaderArray.length();
            for (int i = 0; i < numberOfLearners; i++){
                JSONObject jsonLearnerObject = jsonLearningLeaderArray.getJSONObject(i);
                String name = jsonLearnerObject.getString(NAME);
                String score = jsonLearnerObject.getString(SCORE);
                String country = jsonLearnerObject.getString(COUNTRY);

                SkillLeader SkillLeader = new SkillLeader(name, score, country);
                skillLeaders.add(SkillLeader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return skillLeaders;
    }
}
