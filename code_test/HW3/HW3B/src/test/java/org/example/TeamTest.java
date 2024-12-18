package org.example;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {
    @Test
    void readAndSortTeamsFromFile() {
        Map<String, List<Team>> leagueMap = Main.readAndSortTeamsFromFile("src/teams.txt");
        List<Team> alTeams = leagueMap.get("American League");
        assertAll("AL wrong sorting",
                ()->assertEquals("BAL",alTeams.get(0).getName()),
                ()->assertEquals("TB",alTeams.get(1).getName()),
                ()->assertEquals("HOU",alTeams.get(2).getName()),
                ()->assertEquals("TEX",alTeams.get(3).getName()),
                ()->assertEquals("TOR",alTeams.get(4).getName()),
                ()->assertEquals("SEA",alTeams.get(5).getName()));

        List<Team> nlTeams = leagueMap.get("National League");
        assertAll("NL wrong sorting",
                ()->assertEquals("ATL",nlTeams.get(0).getName()),
                ()->assertEquals("LAD",nlTeams.get(1).getName()),
                ()->assertEquals("MIL",nlTeams.get(2).getName()),
                ()->assertEquals("PHI",nlTeams.get(3).getName()),
                ()->assertEquals("AZ",nlTeams.get(4).getName()),
                ()->assertEquals("MIA",nlTeams.get(5).getName()));
    }

    @Test
    void main() {
        Map<String, List<Team>> leagueMap = Main.readAndSortTeamsFromFile("src/teams.txt");

        // 測試是否正確跳過錯誤行
        List<Team> alTeams = leagueMap.get("American League");
        assertNotNull(alTeams);
        assertTrue(alTeams.size() > 0);

        List<Team> nlTeams = leagueMap.get("National League");
        assertNotNull(nlTeams);
        assertTrue(nlTeams.size() > 0);
    }
}