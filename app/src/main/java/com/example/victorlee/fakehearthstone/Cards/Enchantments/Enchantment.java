package com.example.victorlee.fakehearthstone.Cards.Enchantments;

import com.example.victorlee.fakehearthstone.Cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.Cards.Monster;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Victor Lee on 9/1/2018.
 */

@Getter
@NoArgsConstructor
public abstract class Enchantment extends Monster {

    private Monster component;

    public Enchantment(Monster component) {
        this.component = component;
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

    public void addComponent(Monster component) {
        this.component = component;
    }
}
