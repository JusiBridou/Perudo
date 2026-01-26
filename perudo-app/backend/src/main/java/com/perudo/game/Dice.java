package com.perudo.game;

import java.util.Random;

public class Dice {
    public enum Face {
        PACO(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);
        
        private final int value;
        
        Face(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    private Face value;
    private static final Random random = new Random();

    public Dice() {
        roll();
    }

    public void roll() {
        int randomValue = random.nextInt(6) + 1;
        this.value = Face.values()[randomValue - 1];
    }

    public int getNumericValue() {
        return value.value;
    }

    public Face getValue() {
        return value;
    }

    public void setValue(Face value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.equals(Face.PACO) ? "Paco" : String.valueOf(value.value);
    }
}
