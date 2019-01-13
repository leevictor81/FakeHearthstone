package com.example.victorlee.fakehearthstone.backend.Cards;

import com.example.victorlee.fakehearthstone.backend.Cards.Effects.Effect;

/**
 * Created by Victor Lee on 6/17/2018.
 */

public abstract class Monster extends Card {
    public abstract int getAttack();
    public abstract int getDefense();
    public abstract void changeStats(int attChange, int defChange);
    public abstract Monster getComponent();
    public abstract Effect getEffect();
    public abstract boolean getCanAttack();
    public abstract void setCanAttack(boolean canAttack);
}
