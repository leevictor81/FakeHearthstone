package com.example.victorlee.fakehearthstone.backend.Cards;

import com.example.victorlee.fakehearthstone.backend.Player;

import lombok.Getter;

/**
 * Created by Victor Lee on 6/17/2018.
 */

@Getter
public abstract class Card {
    private String name;
    private int cost;
    private String description;
    private boolean firstTurn;

    public abstract void play(Player currentPlayer, Player opponentPlayer) throws Exception;
    public abstract void play(int fieldIndex, Player player) throws Exception;
}
