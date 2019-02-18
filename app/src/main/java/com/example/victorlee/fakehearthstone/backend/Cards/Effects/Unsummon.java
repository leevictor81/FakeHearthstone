package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Cards.Card;
import com.example.victorlee.fakehearthstone.backend.Exceptions.HandMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Unsummon extends Effect {

    private Unsummon() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex, HandMaxException {
        Card removed = player.getField().removeFromField(fieldIndex);
        player.getHand().draw(removed);
    }

    public static Unsummon builder() {
        return new Unsummon();
    }

    public Unsummon effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public Unsummon build() {
        return this;
    }
}
