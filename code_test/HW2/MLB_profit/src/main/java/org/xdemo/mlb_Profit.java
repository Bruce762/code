package org.xdemo;
//Xdemo改進前
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

class Team {
    String name;
    String stadium;
    int seatingCapacity;
    double playoffFullRate;
    double worldSeriesFullRate;
    int rank;

    public Team(String name, String stadium, int seatingCapacity, double playoffFullRate, double worldSeriesFullRate, int rank) {
        this.name = name;
        this.stadium = stadium;
        this.seatingCapacity = seatingCapacity;
        this.playoffFullRate = playoffFullRate;
        this.worldSeriesFullRate = worldSeriesFullRate;
        this.rank = rank;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public double getPlayoffFullRate() {
        return playoffFullRate;
    }

    public double getWorldSeriesFullRate() {
        return worldSeriesFullRate;
    }

    public int getRank() {
        return rank;
    }
}

class PlayoffCalculator {
    private static final double WILDCARD_TICKET_PRICE = 450;
    private static final double WORLD_SERIES_TICKET_PRICE = 800;
    private static final double HOME_TEAM_SHARE = 0.85;
    private static final double AWAY_TEAM_SHARE = 0.15;

    private List<Team> teams;

    public PlayoffCalculator(List<Team> teams) {
        this.teams = teams;
    }

    public void calculateProfit() {
        for (Team team : teams) {
            double minProfit = calculateMinProfit(team);
            double maxProfit = calculateMaxProfit(team);

            System.out.println("Team: " + team.name);
            System.out.println("Minimum Profit: $" + String.format("%.2f", minProfit));
            System.out.println("Maximum Profit: $" + String.format("%.2f", maxProfit));
            System.out.println();
        }
    }

    private double calculateMinProfit(Team team) {
        return team.getSeatingCapacity() * team.getPlayoffFullRate() * WILDCARD_TICKET_PRICE * AWAY_TEAM_SHARE * 2;
    }

    private double calculateMaxProfit(Team team) {
        int[] games = {3, 5, 7, 7};
        double[] ticketPrices = {WILDCARD_TICKET_PRICE, WILDCARD_TICKET_PRICE, WILDCARD_TICKET_PRICE, WORLD_SERIES_TICKET_PRICE};
        double[] fullRates = {team.getPlayoffFullRate(), team.getPlayoffFullRate(), team.getPlayoffFullRate(), team.getWorldSeriesFullRate()};

        double totalProfit = 0;
        for (int i = 0; i < games.length; i++) {
            int homeGames = (i == 0) ? games[i] : (games[i] / 2 + 1);
            totalProfit += team.getSeatingCapacity() * fullRates[i] * ticketPrices[i] * HOME_TEAM_SHARE * homeGames;
        }
        return totalProfit;
    }

    public static List<Team> loadTeamsFromJson(String filePath) {
        List<Team> teams = new ArrayList<>();
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                // 驗證各欄位是否存在並且範圍合理
                if (isValidTeamData(obj)) {
                    Team team = new Team(
                            obj.getString("team"),
                            obj.getString("stadium"),
                            obj.getInt("seating_capacity"),
                            obj.getDouble("playoff_full_rate"),
                            obj.getDouble("world_series_full_rate"),
                            obj.getInt("rank")
                    );
                    teams.add(team);
                } else {
                    System.err.println("Invalid team data at index " + i + ", skipping...");
                }
            }
        } catch (IOException | JSONException e) {
            System.err.println("Error loading teams from JSON: " + e.getMessage());
        }
        return teams;
    }

    private static boolean isValidTeamData(JSONObject obj) {
        try {
            // 檢查欄位是否存在
            if (!obj.has("team") || !obj.has("stadium") || !obj.has("seating_capacity") ||
                    !obj.has("playoff_full_rate") || !obj.has("world_series_full_rate") || !obj.has("rank")) {
                return false;
            }

            // 檢查座位數是否為正數
            int seatingCapacity = obj.getInt("seating_capacity");
            if (seatingCapacity <= 0) {
                System.err.println("Invalid seating capacity: " + seatingCapacity);
                return false;
            }

            // 檢查滿座率是否在 0 到 1 之間
            double playoffFullRate = obj.getDouble("playoff_full_rate");
            double worldSeriesFullRate = obj.getDouble("world_series_full_rate");
            if (playoffFullRate < 0 || playoffFullRate > 1 || worldSeriesFullRate < 0 || worldSeriesFullRate > 1) {
                System.err.println("Invalid full rate: playoff " + playoffFullRate + ", world series " + worldSeriesFullRate);
                return false;
            }

            // 檢查排名是否為正數
            int rank = obj.getInt("rank");
            if (rank <= 0) {
                System.err.println("Invalid rank: " + rank);
                return false;
            }

            return true;
        } catch (JSONException e) {
            System.err.println("JSON field error: " + e.getMessage());
            return false;
        }
    }
}

public class mlb_Profit {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/xdemo/team.json";
        List<Team> teams = PlayoffCalculator.loadTeamsFromJson(filePath);

        if (!teams.isEmpty()) {
            PlayoffCalculator calculator = new PlayoffCalculator(teams);
            calculator.calculateProfit();
        } else {
            System.out.println("No valid team data found.");
        }
    }
}
