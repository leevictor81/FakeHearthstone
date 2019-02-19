package com.example.victorlee.fakehearthstone.backend;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.victorlee.fakehearthstone.backend.cards.Card;
import com.example.victorlee.fakehearthstone.backend.Exceptions.HandMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Lee on 7/29/2018.
 */

public class Hand extends BaseObservable {
    private static final int MAX_CARDS_IN_HAND = 6;

    private List<Card> hand;
    private int numOfCardsInHand;

    public Hand() {
        this.hand = new ArrayList<>();
        this.numOfCardsInHand = 0;
    }

    @Bindable
    public int getNumOfCardsInHand() {
        return numOfCardsInHand;
    }

    public void setNumOfCardsInHand(int numOfCardsInHand) {
        this.numOfCardsInHand = numOfCardsInHand;
        notifyPropertyChanged(BR.numOfCardsInDeck);
    }

    public Card getCard(int handIndex) {
        if (handIndex > numOfCardsInHand) {
            System.out.println("Invalid hand index.");
        }

        return hand.get(handIndex-1);
    }

    public void removeCard(int handIndex) throws InvalidIndex {
        if (handIndex > numOfCardsInHand) {
            System.out.println("Invalid hand index.");
            throw new InvalidIndex();
        }

        hand.remove(handIndex-1);
        setNumOfCardsInHand(numOfCardsInHand-1);

    }

    public void draw(Card card) throws HandMaxException {
        if (numOfCardsInHand >= MAX_CARDS_IN_HAND) {
            System.out.println("Already max amount of cards in hand. Can't draw.");
            throw new HandMaxException();
        }

        hand.add(card);
        setNumOfCardsInHand(numOfCardsInHand+1);
    }
}
