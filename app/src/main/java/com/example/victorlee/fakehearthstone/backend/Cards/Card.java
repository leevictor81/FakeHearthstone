package com.example.victorlee.fakehearthstone.backend.Cards;

import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 6/17/2018.
 */

public abstract class Card {
    private String name;
    private int cost;
    private String description;
    private boolean firstTurn;

    public abstract void play(Player currentPlayer, Player opponentPlayer) throws Exception;
    public abstract void play(int fieldIndex, Player player) throws Exception;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}