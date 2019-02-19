package com.example.victorlee.fakehearthstone.backend.cards.Monsters;

import com.example.victorlee.fakehearthstone.backend.cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 6/17/2018.
 */

public class BaseMonster extends Monster {
    private int attack;
    private int defense;
    private Effect effect;
    private boolean canAttack = true;

    private BaseMonster() {}

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public Effect getEffect() {
        return effect;
    }

    @Override
    public boolean getCanAttack() {
        return canAttack;
    }

    @Override
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
        return;
    }

    public static BaseMonster builder() {
        return new BaseMonster();
    }

    public BaseMonster attack(int attack) {
        this.attack = attack;
        return this;
    }

    public BaseMonster defense(int defense) {
        this.defense = defense;
        return this;
    }

    public BaseMonster effect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public BaseMonster canAttack(boolean canAttack) {
        this.canAttack = canAttack;
        return this;
    }

    public BaseMonster name(String name) {
        this.name = name;
        return this;
    }

    public BaseMonster cost(int cost) {
        this.cost = cost;
        return this;
    }

    public BaseMonster build() {
        return this;
    }

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
