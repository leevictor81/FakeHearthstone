package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Victor Lee on 8/6/2018.
 */

@Getter
@AllArgsConstructor
public abstract class Effect {
    private int cost;

    public abstract void activate(Player currentPlayer, Player opponentPlayer) throws Exception;
    public abstract void activate(int fieldIndex, Player player) throws Exception;
}
