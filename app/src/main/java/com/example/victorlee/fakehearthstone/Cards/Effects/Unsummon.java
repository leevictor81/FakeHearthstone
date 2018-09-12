package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Cards.Card;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Unsummon extends Effect {

    @Builder
    private Unsummon(int effectCost) {
        super(effectCost);
    }

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void activate(int fieldIndex, Player player) throws InvalidIndex {
        Card removed = player.getField().removeFromField(fieldIndex);
        player.getHand().draw(removed);
    }
}
