package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Summon extends Effect {
    private Monster monster;
    private int numOfSummons;

    private Summon() {}

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

    public static Summon builder() {
        return new Summon();
    }

    public Summon monster(Monster monster) {
        this.monster = monster;
        return this;
    }

    public Summon numOfSummons(int numOfSummons) {
        this.numOfSummons = numOfSummons;
        return this;
    }

    public Summon effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public Summon build() {
        return this;
    }
}
