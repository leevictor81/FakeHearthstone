package com.example.victorlee.fakehearthstone;

import com.example.victorlee.fakehearthstone.Cards.Card;
import com.example.victorlee.fakehearthstone.Exceptions.NoCardsLeft;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Victor Lee on 7/29/2018.
 */

public class Deck {
    private Stack<Card> deck;
    private int numOfCardsInDeck;

    public Deck() {
        this.deck = new Stack<>();
        this.numOfCardsInDeck = 0;
    }

    public void initialize(Stack<Card> deck) {
        this.deck.addAll(deck);
        this.numOfCardsInDeck = deck.size();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() throws NoCardsLeft {
        if (numOfCardsInDeck < 1) {
            System.out.println("No more cards left to draw from deck.");
            throw new NoCardsLeft();
        }

        numOfCardsInDeck--;
        return deck.pop();
    }
}
