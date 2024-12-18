package org.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * PlayoffCalculator calculates the minimum and maximum profit of each team in the playoffs.
 */
public class PlayoffCalculator {
    private static final double WC_PRICE = 450; // 外卡系列賽票價
    private static final double WS_PRICE = 800; // 世界大賽票價
    private static final double HOME_SHARE = 0.85; // 主場分潤比例
    private static final double AWAY_SHARE = 0.15; // 客場分潤比例
    private final List<Team> teams;

    /**
     * Constructor to initialize the PlayoffCalculator with a list of teams.
     * @param teams List of teams.
     */
    public PlayoffCalculator(final List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Calculates and prints the profit of each team.
     */
    public void calculateProfit() {
        for (final Team team : teams) {
            final double minProfit = calculateMinProfit(team);
            final double maxProfit = calculateMaxProfit(team);

            System.out.println("Team: " + team.getName());
            System.out.println("Minimum Profit: $" + String.format("%.2f", minProfit));
            System.out.println("Maximum Profit: $" + String.format("%.2f", maxProfit));
            System.out.println();
        }
    }

    /**
     * Calculates the minimum profit for a team.
     * @param team The team object.
     * @return Minimum profit.
     */
    private double calculateMinProfit(final Team team) {
        return team.getSeatingCapacity() * team.getPlayoffRate() * WC_PRICE * AWAY_SHARE * 2;
    }

    /**
     * Calculates the maximum profit for a team.
     * @param team The team object.
     * @return Maximum profit.
     */
    private double calculateMaxProfit(final Team team) {
        final int[] games = {3, 5, 7, 7};
        final double[] prices = {WC_PRICE, WC_PRICE, WC_PRICE, WS_PRICE};
        final double[] rates = {team.getPlayoffRate(), team.getPlayoffRate(), team.getPlayoffRate(), team.getWsRate()};

        double totalProfit = 0;
        for (int i = 0; i < games.length; i++) {
            final int homeGames = (i == 0) ? games[i] : (games[i] / 2 + 1);
            totalProfit += team.getSeatingCapacity() * rates[i] * prices[i] * HOME_SHARE * homeGames;
        }
        return totalProfit;
    }

    /**
     * Loads teams from a JSON file.
     * @param filePath The path to the JSON file.
     * @return List of teams.
     */
    public static List<Team> loadTeamsFromJson(final String filePath) {
        final List<Team> teams = new ArrayList<>();
        try {
            final String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            final JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject obj = jsonArray.getJSONObject(i);

                if (isValidTeamData(obj)) {
                    final Team team = new Team(
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

    /**
     * Validates team data from JSON.
     * @param obj The JSON object containing team data.
     * @return True if valid, false otherwise.
     */
    private static boolean isValidTeamData(final JSONObject obj) {
        try {
            if (!obj.has("team") || !obj.has("stadium") || !obj.has("seating_capacity") ||
                    !obj.has("playoff_full_rate") || !obj.has("world_series_full_rate") || !obj.has("rank")) {
                return false;
            }
            final int seatingCapacity = obj.getInt("seating_capacity");
            if (seatingCapacity <= 0) return false;

            final double playoffRate = obj.getDouble("playoff_full_rate");
            final double wsRate = obj.getDouble("world_series_full_rate");
            if (playoffRate < 0 || playoffRate > 1 || wsRate < 0 || wsRate > 1) return false;

            final int rank = obj.getInt("rank");
            return rank > 0;
        } catch (JSONException e) {
            return false;
        }
    }
}
