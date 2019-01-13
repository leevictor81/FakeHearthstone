package com.example.victorlee.fakehearthstone.backend.Cards.Effects;

import com.example.victorlee.fakehearthstone.backend.Cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Banish extends Effect {

    @Builder
    private Banish(int effectCost) {
        super(effectCost);
    }

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
}
