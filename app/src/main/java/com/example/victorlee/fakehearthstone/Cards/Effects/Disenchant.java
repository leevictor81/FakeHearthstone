package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Disenchant extends Effect {
    private int numOfDisenchant;

    @Builder
    private Disenchant(int numOfDisenchant, int effectCost) {
        super(effectCost);

        this.numOfDisenchant = numOfDisenchant;
    }

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
}
