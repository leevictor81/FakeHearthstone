package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.backend.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Summon extends Effect {

    private Monster monster;
    private int numOfSummons;

    @Builder
    private Summon(Monster monster, int numOfSummons, int effectCost) {
        super(effectCost);

        this.monster = monster;
        this.numOfSummons = numOfSummons;
    }

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer)  {
        for (int i = 0; i < numOfSummons; i++) {
            try {
                currentPlayer.getField().summon(monster);
            } catch (FieldMaxException e) {
                return;
            }
        }
    }

    @Override
    public void activate(int fieldIndex, Player player) throws IndexNotNeeded {
        System.out.println("Index not needed");
        throw new IndexNotNeeded();
    }
}
