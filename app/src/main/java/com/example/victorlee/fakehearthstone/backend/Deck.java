package com.example.victorlee.fakehearthstone.backend;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.victorlee.fakehearthstone.backend.Cards.Card;
import com.example.victorlee.fakehearthstone.backend.Exceptions.NoCardsLeft;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Victor Lee on 7/29/2018.
 */

public class Deck extends BaseObservable {
    private Stack<Card> deck;
    private int numOfCardsInDeck;

    public Deck() {
        this.deck = new Stack<>();
        this.numOfCardsInDeck = 0;
    }

    @Bindable
    public int getNumOfCardsInDeck() {
        return this.numOfCardsInDeck;
    }

    public void setNumOfCardsInDeck(int numOfCardsInDeck) {
        this.numOfCardsInDeck = numOfCardsInDeck;
        notifyPropertyChanged(BR.numOfCardsInDeck);
        return;
    }

    public void initialize(Stack<Card> deck) {
        this.deck.addAll(deck);
        setNumOfCardsInDeck(deck.size());
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() throws NoCardsLeft {
        if (numOfCardsInDeck < 1) {
            System.out.println("No more cards left to draw from deck.");
            throw new NoCardsLeft();
        }

        setNumOfCardsInDeck(numOfCardsInDeck-1);
        return deck.pop();
    }
}
