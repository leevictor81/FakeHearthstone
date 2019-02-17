package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;


/**
 * Created by Victor Lee on 8/6/2018.
 */


public class SingleTarget extends Effect {
    private int attackChange;
    private int defenseChange;

    private SingleTarget() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex {
        player.getField().changeStats(fieldIndex, attackChange, defenseChange);
    }

    public static SingleTarget builder() {
        return new SingleTarget();
    }

    public SingleTarget attackChange(int attackChange) {
        this.attackChange = attackChange;
        return this;
    }

    public SingleTarget defenseChange(int defenseChange) {
        this.defenseChange = defenseChange;
        return this;
    }

    public SingleTarget effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public SingleTarget build() {
        return this;
    }
}
