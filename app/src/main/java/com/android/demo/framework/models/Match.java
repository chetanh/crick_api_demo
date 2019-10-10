package com.android.demo.framework.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@JsonIgnoreProperties
public class Match {

    private Date date;
    private String stat;
    private String toss_winner_team;
    @JsonProperty("unique_id")
    private String uniqueId;
    private String squad;
    private String dateTimeGMT;
    @JsonProperty("winner_team")
    private String winnerTeam;
    private boolean matchStarted;
    private String type;
    @JsonProperty("team-2")
    private String team2;
    @JsonProperty("team-1")
    private String team1;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getToss_winner_team() {
        return toss_winner_team;
    }

    public void setToss_winner_team(String toss_winner_team) {
        this.toss_winner_team = toss_winner_team;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public void setDateTimeGMT(String dateTimeGMT) {
        this.dateTimeGMT = dateTimeGMT;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMatchStarted() {
        return matchStarted;
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

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @NotNull
    @Override
    public String toString() {
        return "Class [date = " + date + ", toss_winner_team = " + toss_winner_team + ", uniqueId = " + uniqueId + ", squad = " + squad + ", dateTimeGMT = " + dateTimeGMT + ", winnerTeam = " + winnerTeam + ", matchStarted = " + matchStarted + ", type = " + type + ", team2 = " + team2 + ", team1 = " + team1 + "]";
    }
}