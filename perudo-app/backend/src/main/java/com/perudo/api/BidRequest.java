package com.perudo.api;

public class BidRequest {
    private int quantity;
    private int faceValue;
    private String playerId;

    public BidRequest() {
    }

    public BidRequest(int quantity, int faceValue, String playerId) {
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
}
