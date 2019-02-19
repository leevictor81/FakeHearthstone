package com.example.victorlee.fakehearthstone.backend.cards.Effects;

import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.backend.Field;
import com.example.victorlee.fakehearthstone.backend.Graveyard;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 8/30/2018.
 */

public class Aoe extends Effect {
    private int defenseChange;
    private int targets;

    private Aoe() {}

    @Override
    public void activate(Player currentPlayer, Player opponentPlayer) throws InvalidIndex {
        Field currentPlayerField = currentPlayer.getField();
        Graveyard currentPlayerGraveyard = currentPlayer.getGraveyard();
        int currentPlayerNumOfMonsters = currentPlayerField.getNumOfMonsters();
        Field opponentPlayerField = opponentPlayer.getField();
        int opponentPlayerNumOfMonsters = opponentPlayerField.getNumOfMonsters();
        Graveyard opponentPlayerGraveyard = opponentPlayer.getGraveyard();

        if (targets == 1) {
            changeStatsForAllMonsters(currentPlayerNumOfMonsters, currentPlayerField, currentPlayerGraveyard);
        } else if (targets == 2) {
            changeStatsForAllMonsters(opponentPlayerNumOfMonsters, opponentPlayerField, opponentPlayerGraveyard);
        } else if (targets == 3) {
            changeStatsForAllMonsters(currentPlayerNumOfMonsters, currentPlayerField, currentPlayerGraveyard);
            changeStatsForAllMonsters(opponentPlayerNumOfMonsters, opponentPlayerField, opponentPlayerGraveyard);
        }
    }

    @Override
    public void activate(int fieldIndex, Player player) throws IndexNotNeeded {
        throw new IndexNotNeeded();
    }

    private void changeStatsForAllMonsters(int numOfMonsters, Field field, Graveyard graveyard) throws InvalidIndex {
        while(numOfMonsters > 0) {
            field.changeStats(numOfMonsters, 0, defenseChange);

            if (field.getAMonstersDefense(numOfMonsters) <= 0) {
                Monster monster = field.removeFromField(numOfMonsters);
                graveyard.addToGraveyard(monster);
            }

            numOfMonsters--;
        }
    }

    public static Aoe builder() {
        return new Aoe();
    }

    public Aoe defenseChange(int defenseChange) {
        this.defenseChange = defenseChange;
        return this;
    }

    public Aoe targets(int targets) {
        this.targets = targets;
        return this;
    }

    public Aoe effectCost(int effectCost) {
        this.cost = effectCost;
        return this;
    }

    public Aoe build() {
        return this;
    }
}
