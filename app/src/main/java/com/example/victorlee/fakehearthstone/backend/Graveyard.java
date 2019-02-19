package com.example.victorlee.fakehearthstone.backend;

import com.example.victorlee.fakehearthstone.backend.cards.Monster;

import java.util.Stack;

/**
 * Created by Victor Lee on 7/29/2018.
 */

public class Graveyard {
    private Stack<Monster> graveyard;
    private int numOfCardsInGraveyard;

    public Graveyard() {
        this.graveyard = new Stack<>();
        this.numOfCardsInGraveyard = 0;
    }

    public int getNumOfCardsInGraveyard() {
        return numOfCardsInGraveyard;
    }

    public void addToGraveyard(Monster monster) {
        graveyard.add(monster);
        numOfCardsInGraveyard++;
    }

    public Monster removeFromGraveyard() {
        return graveyard.pop();
    }
}
