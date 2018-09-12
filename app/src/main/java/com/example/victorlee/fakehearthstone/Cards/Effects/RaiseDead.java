package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Cards.Monster;
import com.example.victorlee.fakehearthstone.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class RaiseDead extends Effect {

    private int numOfRaises;

    @Builder
    private RaiseDead(int numOfRaises, int effectCost) {
        super(effectCost);

        this.numOfRaises = numOfRaises;
    }

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
}
