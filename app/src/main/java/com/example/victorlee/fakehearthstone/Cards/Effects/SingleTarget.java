package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 8/6/2018.
 */


public class SingleTarget extends Effect {
    private int attackChange;
    private int defenseChange;

    @Builder
    private SingleTarget(int attackChange, int defenseChange, int effectCost) {
        super(effectCost);

        this.attackChange = attackChange;
        this.defenseChange = defenseChange;
    }

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex {
        player.getField().changeStats(fieldIndex, attackChange, defenseChange);
    }
}
