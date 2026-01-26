package com.perudo.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private String name;
    public List<Dice> dices;
    private int diceCount;
    private boolean active;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.dices = new ArrayList<>();
        this.diceCount = 5;
        this.active = true;
        rollDices();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public boolean isActive() {
        return active && diceCount > 0;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void rollDices() {
        dices.clear();
        for (int i = 0; i < diceCount; i++) {
            dices.add(new Dice());
        }
    }

    public void loseDice() {
        if (diceCount > 0) {
            diceCount--;
        }
        if (diceCount == 0) {
            active = false;
        }
    }
}
