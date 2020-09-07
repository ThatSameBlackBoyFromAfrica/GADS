package com.chukwuebuka.gads2020leaderboard.data;


import java.util.List;

public class LearningLeader {
    String name;
    String hours;
    String country;
    static List<LearningLeader> learningLeaders;

    public static void setLearningLeaders(List<LearningLeader> learningLeaders) {
        LearningLeader.learningLeaders = learningLeaders;
    }

    public static List<LearningLeader> getLearningLeaders() {
        return learningLeaders;
    }

    public LearningLeader(String name, String hours, String country) {
        this.name = name;
        this.hours = hours;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
