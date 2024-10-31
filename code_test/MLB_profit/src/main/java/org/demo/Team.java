package org.demo;

/**
 * Team class represents a baseball team with its stadium capacity and full rates.
 */
public class Team {
    private final String name; // 球隊名稱
    private final String stadium; // 球場名稱
    private final int seatingCapacity; // 座位數
    private final double playoffRate; // 季後賽滿座率
    private final double wsRate; // 世界大賽滿座率
    private final int rank; // 隊伍排名

    public String getName() {
        return name;
    }

    /**
     * Constructor to initialize a Team object.
     * @param name Team's name.
     * @param stadium Stadium's name.
     * @param seatingCapacity Seating capacity of the stadium.
     * @param playoffRate Playoff full rate.
     * @param wsRate World Series full rate.
     * @param rank Team's ranking.
     */
    public Team(final String name, final String stadium, final int seatingCapacity,
                final double playoffRate, final double wsRate, final int rank) {
        this.name = name;
        this.stadium = stadium;
        this.seatingCapacity = seatingCapacity;
        this.playoffRate = playoffRate;
        this.wsRate = wsRate;
        this.rank = rank;
    }

    // Getter methods
    public int getSeatingCapacity() { return seatingCapacity; }
    public double getPlayoffRate() { return playoffRate; }
    public double getWsRate() { return wsRate; }
    public int getRank() { return rank; }
}