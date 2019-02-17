package com.example.victorlee.fakehearthstone.backend.Cards.Enchantments;

import com.example.victorlee.fakehearthstone.backend.Cards.Monster;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public class Buff extends Enchantment {
    private int attackChange;
    private int defenseChange;

    public Buff() {}

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

    public static Buff builder() {
        return new Buff();
    }

    public Buff attackChange(int attackChange) {
        this.attackChange = attackChange;
        return this;
    }

    public Buff defenseChange(int defenseChange) {
        this.defenseChange = defenseChange;
        return this;
    }

    public Buff component(Monster component){
        this.component = component;
        return this;
    }

    public Buff build() {
        return this;
    }
}
