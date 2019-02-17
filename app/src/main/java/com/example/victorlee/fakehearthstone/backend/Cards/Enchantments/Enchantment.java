package com.example.victorlee.fakehearthstone.backend.Cards.Enchantments;

import com.example.victorlee.fakehearthstone.backend.Cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.backend.Cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 9/1/2018.
 */

public abstract class Enchantment extends Monster {
    protected Monster component;

    public Enchantment(Monster component) {
        this.component = component;
    }

    public Enchantment() {
        super();
    }

    @Override
    public void play(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        System.out.println("Card needs an index");
        throw new InvalidIndex();
    }

    @Override
    public void play(int fieldIndex, Player player) throws Exception {
        player.getField().addEnchantmentToMonster(fieldIndex, this);
    }

    @Override
    public Effect getEffect() {
        return component.getEffect();
    }

    @Override
    public boolean getCanAttack() {
        return component.getCanAttack();
    }

    @Override
    public void setCanAttack(boolean canAttack) {
        component.setCanAttack(canAttack);
    }

    public void addComponent(Monster component) {
        this.component = component;
    }

    @Override
    public Monster getComponent() {
        return component;
    }
}
