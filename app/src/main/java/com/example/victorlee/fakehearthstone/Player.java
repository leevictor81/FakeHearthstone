package com.example.victorlee.fakehearthstone;

import com.example.victorlee.fakehearthstone.Cards.Card;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Exceptions.NoCardsLeft;
import com.example.victorlee.fakehearthstone.Exceptions.NotEnoughMana;

import lombok.Getter;

/**
 * Created by Victor Lee on 6/16/2018.
 */


@Getter
public class Player {
    private Deck deck;
    private Hand hand;
    private Field field;
    private Graveyard graveyard;
    private int life;
    private String name;
    private int magicCurr;

    public Player(String name) {
        this.deck = new Deck();
        this.hand = new Hand();
        this.field = new Field();
        this.graveyard = new Graveyard();
        this.life = 30;
        this.name = name;
        this.magicCurr = 1;
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
        life -= damage;
    }

    public void attack(int fieldIndex, int damage) throws InvalidIndex {
        field.monsterGetsAttacked(fieldIndex, damage);
    }

    public int getLife() {
        return life;
    }

    public String getName() {
        return name;
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
