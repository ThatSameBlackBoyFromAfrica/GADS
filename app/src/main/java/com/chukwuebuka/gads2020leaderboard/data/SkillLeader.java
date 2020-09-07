package com.chukwuebuka.gads2020leaderboard.data;

import java.util.List;

public class SkillLeader {
    String name;
    String score;
    String country;
    static List<SkillLeader> skillLeaders;

    public static List<SkillLeader> getSkillLeaders() {
        return skillLeaders;
    }

    public static void setSkillLeaders(List<SkillLeader> skillLeaders) {
        SkillLeader.skillLeaders = skillLeaders;
    }

    public SkillLeader(String name, String score, String country) {
        this.name = name;
        this.score = score;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
