package com.example.victorlee.fakehearthstone;

import com.example.victorlee.fakehearthstone.Cards.Card;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Lee on 7/29/2018.
 */

public class Hand {
    private List<Card> hand;
    private int numOfCardsInHand;

    public Hand() {
        this.hand = new ArrayList<>();
        this.numOfCardsInHand = 0;
    }

    public int getNumOfCardsInHand() {
        return numOfCardsInHand;
    }

    public Card getCard(int handIndex) throws InvalidIndex {
        if (handIndex > numOfCardsInHand) {
            System.out.println("Invalid hand index.");
            throw new InvalidIndex();
        }

        return hand.get(handIndex-1);
    }

    public void removeCard(int handIndex) throws InvalidIndex {
        if (handIndex > numOfCardsInHand) {
            System.out.println("Invalid hand index.");
            throw new InvalidIndex();
        }

        hand.remove(handIndex-1);
        numOfCardsInHand--;
    }

    public void draw(Card card) {
        hand.add(card);
        numOfCardsInHand++;
    }
}
