package com.example.myguessapp;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Serializable {

    private int cap;
    private List<Team> teams;
    private Round round;
    private Team activeTeam;

    //Getters and Setters

    public Team getActiveTeam() {
        return activeTeam;
    }

    public String getActiveTeamName() {
        return activeTeam.toString();
    }

    public int getActiveTeamPoints() {
        return this.activeTeam.getPoints();
    }

    public int getCorrectAnswer() {
        return this.round.getCorrectAnswer();
    }

    public void setActiveTeam(Team activeTeam) {
        this.activeTeam = activeTeam;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    // Game Constructor

    public Game(String team1, String team2, int cap) {
        Team teamA = new Team(team1);
        Team teamB = new Team(team2);
        List<Team> teamList = new ArrayList<Team>();
        teamList.add(teamA);
        teamList.add(teamB);
        this.teams = teamList;
        this.activeTeam = teamA;
        this.cap = cap;
        Round round = new Round();
        this.round = round;
    }

    public String toString() {
        String team1 = this.teams.get(0).toString();
        String team2 = this.teams.get(1).toString();
        String cap = Integer.toString(this.cap);
        String round = this.round.toString();
        String activeTeam = this.activeTeam.toString();
        return team1 + " " + team2 + " " + cap + " " + round + " " + activeTeam;
    }

    //Team Class

    private class Team implements Serializable{

        private String name;
        private int points;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        Team (String name) {
            this.name = name;
            this.points = 0;
        }

        public String toString() {
            return this.name;
        }

        private void updatePoints(int points) {
            this.points += points;
        }
    }

    //Calculations

    public int calculatePoints(int guess) {
        int correctResult = this.getCorrectAnswer();
        int range = Math.abs(guess - correctResult);
        int points;
        if (range <= 2) {
            return 5;
        } else if (range <= 5) {
            return 3;
        } else if (range <= 8) {
            return 1;
        }
        return 0;
    }

    public void updatePointsToActiveTeam(int points) {
        this.activeTeam.updatePoints(points);
    }

    public boolean endGameCheck() {
        if (activeTeam.getPoints() >= this.cap) {
            return true;
        }
        return false;
    }

    public void updateTeamValues() {
        for(Team t : this.teams) {
            if(activeTeam.getName() == t.getName()) {
                t = activeTeam;
            }
        }
    }

    public void changeActiveTeam() {
        for(Team t: this.teams) {
            if(t != activeTeam) {
                activeTeam = t;
                break;
            }
        }
    }

    public void drawNewCategories() {
        this.round.drawNewCategories();
    }

    public void drawNewCorrectAnswer() {
        this.round.drawNewCorrectAnswer();
    }
}
