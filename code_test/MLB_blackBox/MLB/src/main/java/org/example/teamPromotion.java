package org.example;

public class teamPromotion {
    private String name;
    private int win;

    public teamPromotion(String name, int win) {
        this.name = name;
        this.win = win;
    }
    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWin(int win) {
        this.win = win;
    }
}
