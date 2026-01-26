package com.perudo.api;

public class DudoResult {
    private int actualCount;
    private boolean bidWon;
    private String loserName;

    public DudoResult() {
    }

    public DudoResult(int actualCount, boolean bidWon, String loserName) {
        this.actualCount = actualCount;
        this.bidWon = bidWon;
        this.loserName = loserName;
    }

    public int getActualCount() {
        return actualCount;
    }

    public void setActualCount(int actualCount) {
        this.actualCount = actualCount;
    }

    public boolean isBidWon() {
        return bidWon;
    }

    public void setBidWon(boolean bidWon) {
        this.bidWon = bidWon;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }
}
