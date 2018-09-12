package com.example.victorlee.fakehearthstone.Cards.Monsters;

import com.example.victorlee.fakehearthstone.Cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.Cards.Monster;
import com.example.victorlee.fakehearthstone.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Victor Lee on 6/17/2018.
 */

@Getter
@Builder
public class BaseMonster extends Monster {
    private int attack;
    private int defense;
    private Effect effect;
    private boolean canAttack = false;

    public void changeStats(int attChange, int defChange) {
        this.attack += attChange;
        this.defense += defChange;
    }

    @Override
    public void play(Player currentPlayer, Player opponentPlayer) throws FieldMaxException {
        currentPlayer.getField().summon(this);
    }

    @Override
    public void play(int fieldIndex, Player player) throws Exception {
        throw new IndexNotNeeded();
    }

    @Override
    public Monster getComponent() {
        return null;
    }
}
