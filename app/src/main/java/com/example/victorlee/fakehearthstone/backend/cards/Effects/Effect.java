package com.example.victorlee.fakehearthstone.backend.cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 8/6/2018.
 */

public abstract class Effect {
    protected int cost;

    public Effect() {
    }

    public Effect(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public abstract void activate(Player currentPlayer, Player opponentPlayer) throws Exception;
    public abstract void activate(int fieldIndex, Player player) throws Exception;
}
