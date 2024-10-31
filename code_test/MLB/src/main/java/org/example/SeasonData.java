package org.example;

import java.util.List;

public class SeasonData {
    private String season;
    private List<TeamRecord> teamRecord;
    private List<PlayoffTeam> playoffTeams;
    // Getters and Setters
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<TeamRecord> getTeamRecord() {
        return teamRecord;
    }

    public void setTeamRecord(List<TeamRecord> teamRecord) {
        this.teamRecord = teamRecord;
    }

    public List<PlayoffTeam> getPlayoffTeams() {
        return playoffTeams;
    }

    public void setPlayoffTeams(List<PlayoffTeam> playoffTeams) {
        this.playoffTeams = playoffTeams;
    }
}

class TeamRecord {
    private String team;
    private int wins;
    private int losses;
    private String league;
    private String division;

    // Getters and Setters
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}

class PlayoffTeam {
    private String team;
    private List<Series> series;

    // Getters and Setters
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}

class Series {
    private String opponent;
    private boolean advanced;

    // Getters and Setters
    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public boolean getAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }
}
