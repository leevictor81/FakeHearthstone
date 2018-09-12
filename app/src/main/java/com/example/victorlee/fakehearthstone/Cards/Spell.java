package com.example.victorlee.fakehearthstone.Cards;

import com.example.victorlee.fakehearthstone.Cards.Card;
import com.example.victorlee.fakehearthstone.Cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 8/26/2018.
 */

@Builder
public class Spell extends Card {
    private Effect effect;

    @Override
    public void play(Player currentPlayer, Player opponentPlayer) throws Exception {
        effect.activate(currentPlayer, opponentPlayer);
    }

    @Override
    public void play(int fieldIndex, Player player) throws Exception {
        effect.activate(fieldIndex, player);
    }
}