package com.example.victorlee.fakehearthstone.backend;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.victorlee.fakehearthstone.backend.Cards.Card;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Exceptions.NoCardsLeft;
import com.example.victorlee.fakehearthstone.backend.Exceptions.NotEnoughMana;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Victor Lee on 6/16/2018.
 */

@Getter
@Setter
public class Player extends BaseObservable {
    private Deck deck;
    private Hand hand;
    private Field field;
    private Graveyard graveyard;
    private int life;
    private String name;
    private int magicCurr;
    private int attack;
    private boolean canAttack;
    private Drawable image;

    public Player(String name, Drawable drawable) {
        this.deck = new Deck();
        this.hand = new Hand();
        this.field = new Field();
        this.graveyard = new Graveyard();
        this.life = 30;
        this.name = name;
        this.magicCurr = 0;
        this.attack = 0;
        this.canAttack = true;
        this.image = drawable;
    }

    @Bindable
    public int getLife(){
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
        notifyPropertyChanged(BR.life);
    }

    @Bindable
    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        notifyPropertyChanged(BR.attack);
    }

    public void play(int handIndex, Player currentPlayer, Player opponentPlayer) throws Exception {
        int cost = checkIfPlayerHasEnoughMana(hand.getCard(handIndex).getCost());

        hand.getCard(handIndex).play(currentPlayer, opponentPlayer);
        hand.removeCard(handIndex);
        this.magicCurr -= cost;
    }

    public void play(int handIndex, int fieldIndex, Player targerPlayer) throws Exception {
        int cost = checkIfPlayerHasEnoughMana(hand.getCard(handIndex).getCost());

        hand.getCard(handIndex).play(fieldIndex, targerPlayer);
        hand.removeCard(handIndex);
        this.magicCurr -= cost;
    }

    public void use(int fieldIndex, Player currentPlayer, Player opponentPlayer) throws Exception {
        int cost = checkIfPlayerHasEnoughMana(field.getAMonstersEffectCost(fieldIndex));

        field.activate(fieldIndex, currentPlayer, opponentPlayer);
        this.magicCurr -= cost;
    }

    public void use(int fieldIndex, Player targerPlayer, int targetFieldIndex) throws Exception {
        int cost = checkIfPlayerHasEnoughMana(field.getAMonstersEffectCost(fieldIndex));

        field.activate(fieldIndex, targerPlayer, targetFieldIndex);
        this.magicCurr -= cost;
    }

    public void getAttacked(int damage) {
        setLife(this.life-damage);
    }

    public void attack(int fieldIndex, int damage) throws InvalidIndex {
        field.monsterGetsAttacked(fieldIndex, damage);
    }

    public void draw(int numOfCardsToDraw) throws NoCardsLeft {
        while (numOfCardsToDraw > 0) {
            Card card = deck.draw();
            hand.draw(card);
            numOfCardsToDraw--;
        }
    }

    public void incrementMagicCurr(int magicCurrIncrease) {
        this.magicCurr += magicCurrIncrease;
    }

    private int checkIfPlayerHasEnoughMana(int cost) throws InvalidIndex, NotEnoughMana {
        if (cost > magicCurr) {
            System.out.println("Not enough magic to play card");
            throw new NotEnoughMana();
        }
        return cost;
    }
}