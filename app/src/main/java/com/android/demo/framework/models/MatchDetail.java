package com.android.demo.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchDetail {

    private String score;
    private String stat;
    private String creditsLeft;
    private Provider provider;
    private String v;
    private String description;
    private boolean matchStarted;
    @JsonProperty("team-2")
    private String team2;
    private String ttl;
    @JsonProperty("team-1")
    private String team1;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(String creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    @Override
    public String toString() {
        return "Class [score = " + score + ", stat = " + stat + ", creditsLeft = " + creditsLeft + ", provider = " + provider + ", v = " + v + ", description = " + description + ", matchStarted = " + matchStarted + ", team-2 = " + team2 + ", ttl = " + ttl + ", team-1 = " + team1 + "]";
    }
}
