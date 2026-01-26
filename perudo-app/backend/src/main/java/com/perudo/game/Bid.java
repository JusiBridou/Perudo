package com.perudo.game;

public class Bid {
    private int quantity;
    private int faceValue;
    private String playerId;

    public Bid(int quantity, int faceValue, String playerId) {
        this.quantity = quantity;
        this.faceValue = faceValue;
        this.playerId = playerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isValid() {
        return quantity > 0 && faceValue >= 1 && faceValue <= 6;
    }

    public boolean isHigherThan(Bid other) {
        if (faceValue != other.faceValue) {
            return faceValue > other.faceValue;
        }
        return quantity > other.quantity;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "quantity=" + quantity +
                ", faceValue=" + faceValue +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
