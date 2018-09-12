package com.example.victorlee.fakehearthstone.Cards.Effects;

import com.example.victorlee.fakehearthstone.Cards.Monster;
import com.example.victorlee.fakehearthstone.Exceptions.IndexNotNeeded;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;
import com.example.victorlee.fakehearthstone.Field;
import com.example.victorlee.fakehearthstone.Graveyard;
import com.example.victorlee.fakehearthstone.Player;

import lombok.Builder;

/**
 * Created by Victor Lee on 8/30/2018.
 */

public class Aoe extends Effect {
    private int defenseChange;
    private int targets;

    @Builder
    private Aoe(int defenseChange, int targets, int effectCost) {
        super(effectCost);
        this.defenseChange = defenseChange;
        this.targets = targets;
    }

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
}
