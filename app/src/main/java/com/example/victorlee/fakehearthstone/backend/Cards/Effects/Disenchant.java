package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Disenchant extends Effect {
    private int numOfDisenchant;

    private Disenchant() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex {
        for(int i = 0; i < numOfDisenchant; i++) {
            player.getField().disenchantAMonster(fieldIndex);
        }
    }

    public static Disenchant builder() {
        return new Disenchant();
    }

    public Disenchant numOfDisenchant(int numOfDisenchant) {
        this.numOfDisenchant = numOfDisenchant;
        return this;
    }

    public Disenchant effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public Disenchant build() {
        return this;
    }
}
