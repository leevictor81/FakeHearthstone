package com.example.victorlee.fakehearthstone.backend.cards;

import com.example.victorlee.fakehearthstone.backend.cards.Effects.Effect;
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

    public Spell name(String name) {
        setName(name);
        return this;
    }

    public Spell cost(int cost) {
        setCost(cost);
        return this;
    }

    public Spell description(String description) {
        setDescription(description);
        return this;
    }

    public Spell effect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public Spell build() {
        return this;
    }
}