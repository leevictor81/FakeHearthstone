package com.example.victorlee.fakehearthstone.backend.cards.Effects;

import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class RaiseDead extends Effect {
    private int numOfRaises;

    private RaiseDead() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer)  {
        for (int i = 0; i < numOfRaises; i++) {
            Monster monster = currentPlayer.getGraveyard().removeFromGraveyard();
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

    public static RaiseDead builder() {
        return new RaiseDead();
    }

    public RaiseDead numOfRaises(int numOfRaises) {
        this.numOfRaises = numOfRaises;
        return this;
    }

    public RaiseDead effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public RaiseDead build() {
        return this;
    }
}
