package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Team class to store team name and seed (ranking)
class Team {
    String name,league,division;
    int wins,losses; // Seed or ranking based on performance

    public Team(String name, int wins,int losses,String league,String division) {
        this.name = name;
        this.wins=wins;
        this.losses=losses;
        this.league=league;
        this.division=division;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        //return String.format("%-4s Wins: %d Losses: %d league: %s division: %s", name, wins, losses,league,division);
        return String.format("%-4s", name); // Ensure fixed width for team names
    }
}

public class Main {

    // Method to build and print the playoff bracket for American League
    public static void generateALBracket(List<Team> teams) {
        System.out.println("(AMERICAN LEAGUE)");
        printALBracket(teams);
        System.out.println();
    }

    // Method to build and print the playoff bracket for National League
    public static void generateNLBracket(List<Team> teams) {
        printNLBracket(teams);
        System.out.println("(NATIONAL LEAGUE)");
    }

    // Helper method to print the American League bracket
    private static void printALBracket(List<Team> teams) {
        // Preventive Programming: Ensure we have exactly 6 teams per league
        //assert teams.size()>=6 : "Error: Each league should have at least 6 teams.";
        if (teams.size() < 6) {
            System.out.println("Error: Each league should have at least 6 teams.");
            return;
        }

        // Format playoff matches based on your desired output format
        System.out.println(teams.get(5) + "6 -----");
        System.out.println(teams.get(2) + "3 ----- ? -----");
        System.out.println("        " + teams.get(1) + "2 ----- ?");
        System.out.println(teams.get(4) + "5 -----");
        System.out.println(teams.get(3) + "4 ----- ? -----");
        System.out.println("        " + teams.get(0) + "1 ----- ? ----- ?");
        System.out.println("                               ---- ?");
    }

    // Helper method to print the National League bracket
    private static void printNLBracket(List<Team> teams) {
        // Preventive Programming: Ensure we have exactly 6 teams per league
        //assert teams.size()>=6:"Error1: Each league should have at least 6 teams.";
        if (teams.size() < 6) {
            System.out.println("Error: Each league should have at least 6 teams.");
            return;
        }

        // Format playoff matches for National League
        System.out.println(teams.get(5) + "6 ----- ? ----- ? ----- ?");
        System.out.println(teams.get(2) + "3 -----");
        System.out.println("        " + teams.get(1) + "2 ----");
        System.out.println(teams.get(4) + "5 ----- ? ----- ?");
        System.out.println(teams.get(3) + "4 -----");
        System.out.println("        " + teams.get(0) + "1 -----");
    }

    // Method to read teams from a txt file and sort them
    public static Map<String, List<Team>> readAndSortTeamsFromFile(String filename) {
        Map<String, List<Team>> leagueMap = new LinkedHashMap<>();
        List<Team> ALTeams = new ArrayList<>();
        List<Team> NLTeams = new ArrayList<>();
        boolean isNL = false; // Flag to separate leagues

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    // Empty line separates the leagues
                    isNL = true;
                    continue;
                }

                // Split the line into team name and seed
                String[] parts = line.split("\\s+");
                if (parts.length < 5) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                String teamName = parts[0];
                int wins = Integer.parseInt(parts[1]);
                int losses = Integer.parseInt(parts[2]);
                String league=parts[3];
                String division=parts[4];

                Team team = new Team(teamName, wins,losses,league,division);

                // Add teams to the appropriate league list
                if (isNL) {
                    NLTeams.add(team);
                } else {
                    ALTeams.add(team);
                }
            }

            // Sort teams based on seed (lower seed number is better)
            Collections.sort(ALTeams, Comparator.comparingInt(Team::getWins).reversed().thenComparing(Team::getName));
            Collections.sort(NLTeams, Comparator.comparingInt(Team::getWins).reversed().thenComparing(Team::getName));

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Add both leagues' teams to the map
        leagueMap.put("American League", ALTeams);
        leagueMap.put("National League", NLTeams);

        return leagueMap;
    }

    public static void main(String[] args) {
        // Read and sort the teams from the txt file
        Map<String, List<Team>> leagueMap = readAndSortTeamsFromFile("src/teams.txt");

        // Generate and print the playoff bracket for both leagues
        generateALBracket(leagueMap.get("American League"));
        generateNLBracket(leagueMap.get("National League"));
    }
}
