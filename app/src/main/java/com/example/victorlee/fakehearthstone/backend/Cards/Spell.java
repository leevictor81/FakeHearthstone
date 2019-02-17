package com.example.victorlee.fakehearthstone.backend.Cards;

import com.example.victorlee.fakehearthstone.backend.Cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 8/26/2018.
 */

public class Spell extends Card {
    private Effect effect;

    public Spell() {}

    public Spell(Effect effect) {
        this.effect = effect;
    }

    @Override
    public void play(Player currentPlayer, Player opponentPlayer) throws Exception {
        effect.activate(currentPlayer, opponentPlayer);
    }

    @Override
    public void play(int fieldIndex, Player player) throws Exception {
        effect.activate(fieldIndex, player);
    }

    public static Spell builder() {
        return new Spell();
    }

    public Spell effect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public Spell build() {
        return this;
    }
}