package com.example.victorlee.fakehearthstone.backend.cards.Effects;

import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Banish extends Effect {

    private Banish() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex {
        Monster monster = player.getField().removeFromField(fieldIndex);
        player.getGraveyard().addToGraveyard(monster);
    }

    public static Banish builder() {
        return new Banish();
    }

    public Banish effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public Banish build() {
        return this;
    }
}
