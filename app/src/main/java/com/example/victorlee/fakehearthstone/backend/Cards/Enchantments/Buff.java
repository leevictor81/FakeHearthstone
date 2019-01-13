package com.example.victorlee.fakehearthstone.backend.Cards.Enchantments;

import com.example.victorlee.fakehearthstone.backend.Cards.Monster;

import lombok.Builder;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Buff extends Enchantment {
    private int attackChange;
    private int defenseChange;

    @Builder
    public Buff(int attackChange, int defenseChange, Monster component) {
        super(component);

        this.attackChange = attackChange;
        this.defenseChange = defenseChange;
    }

    @Override
    public int getAttack() {
        return super.getComponent().getAttack() + attackChange;
    }

    @Override
    public int getDefense() {
        return super.getComponent().getDefense() + defenseChange;
    }

    @Override
    public void changeStats(int attChange, int defChange) {
        super.getComponent().changeStats(attChange, defChange);
    }
}
