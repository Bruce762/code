package org.demo;
//demo改進後
import java.util.List;

/**
 * MLBProfit is the main class for calculating playoff profits.
 */
public class mlb_Profit {
    public static void main(final String[] args) {
        final String filePath = "src/main/java/org/demo/team.json";
        final List<Team> teams = PlayoffCalculator.loadTeamsFromJson(filePath);

        if (!teams.isEmpty()) {
            final PlayoffCalculator calculator = new PlayoffCalculator(teams);
            calculator.calculateProfit();
        } else {
            System.out.println("No valid team data found.");
        }
    }
}
