package com.perudo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameState {
    public enum GamePhase {
        WAITING, PLAYING, RESOLUTION, FINISHED
    }

    private String gameId;
    private List<Player> players;
    private int currentPlayerIndex;
    private Bid currentBid;
    private GamePhase phase;
    private String message;

    public GameState() {
        this.gameId = UUID.randomUUID().toString();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.phase = GamePhase.WAITING;
        this.currentBid = null;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Bid getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }

    public GamePhase getPhase() {
        return phase;
    }

    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addPlayer(Player player) {
        if (phase == GamePhase.WAITING && players.size() < 6) {
            players.add(player);
        }
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        if (players.isEmpty()) {
            return;
        }
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } while (!players.get(currentPlayerIndex).isActive());
    }

    public void startGame() {
        if (players.size() >= 2) {
            phase = GamePhase.PLAYING;
            rollAllDices();
            currentBid = null;
        }
    }

    public void rollAllDices() {
        for (Player player : players) {
            player.rollDices();
        }
    }

    public int countDices(int value, boolean includePaco) {
        int count = 0;
        for (Player player : players) {
            for (Dice dice : player.dices) {
                if (includePaco && dice.getValue() == Dice.Face.PACO) {
                    count++;
                } else if (dice.getNumericValue() == value) {
                    count++;
                }
            }
        }
        return count;
    }
}
