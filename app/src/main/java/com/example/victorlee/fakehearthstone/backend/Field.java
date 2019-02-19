package com.example.victorlee.fakehearthstone.backend;

import com.example.victorlee.fakehearthstone.backend.cards.Enchantments.Enchantment;
import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.backend.Exceptions.InvalidIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Lee on 6/16/2018.
 */

public class Field {
    private static final int MAX_MONTERS_ON_FIELD = 5;

    private List<Monster> monsters;
    private int numOfMonsters;

    public Field() {
        this.monsters = new ArrayList<>();
        this.numOfMonsters = 0;
    }

    public int getNumOfMonsters() {
        return numOfMonsters;
    }

    public int getAMonstersAttack(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        return monsters.get(fieldIndex-1).getAttack();
    }

    public int getAMonstersDefense(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        return monsters.get(fieldIndex-1).getDefense();
    }

    public int getAMonstersEffectCost(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        return monsters.get(fieldIndex-1).getEffect().getCost();
    }

    public boolean getAMonstersCanAttack(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        return monsters.get(fieldIndex-1).getCanAttack();
    }

    public void setAMonstersCanAttack(int fieldIndex, boolean canAttack) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        monsters.get(fieldIndex-1).setCanAttack(canAttack);
        return;
    }

    public void monsterGetsAttacked(int fieldIndex, int damage) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        changeStats(fieldIndex, 0, -1*damage);
    }

    public void activate(int fieldIndex, Player currentPlayer, Player opponentPlayer) throws Exception {
        checkIfValidFieldIndex(fieldIndex);
        monsters.get(fieldIndex-1).getEffect().activate(currentPlayer, opponentPlayer);
    }

    public void activate(int fieldIndex, Player targerPlayer, int targetFieldIndex) throws Exception {
        checkIfValidFieldIndex(fieldIndex);
        monsters.get(fieldIndex-1).getEffect().activate(targetFieldIndex, targerPlayer);
    }

    public void summon(Monster monster) throws FieldMaxException {
        if (numOfMonsters >= MAX_MONTERS_ON_FIELD) {
            System.out.println("Already max amount of monster on the field. Can't summon.");
            throw new FieldMaxException();
        }

        monsters.add(numOfMonsters, monster);
        numOfMonsters++;
    }

    public void changeStats(int fieldIndex, int attackChange, int defenseChange) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        monsters.get(fieldIndex-1).changeStats(attackChange, defenseChange);
    }

    public void addEnchantmentToMonster(int fieldIndex, Enchantment enchantment) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        enchantment.addComponent(monsters.get(fieldIndex-1));
        monsters.set(fieldIndex-1, enchantment);
    }

    public void disenchantAMonster(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        int i = fieldIndex - 1;
        Monster monster = monsters.get(i).getComponent();
        if (monster != null) {
            monsters.set(i, monster);
        }
    }

    public Monster removeFromField(int fieldIndex) throws InvalidIndex {
        checkIfValidFieldIndex(fieldIndex);
        Monster removed = monsters.remove(fieldIndex-1);
        numOfMonsters--;
        return removed;
    }

    private void checkIfValidFieldIndex(int fieldIndex) throws InvalidIndex {
        if (fieldIndex > numOfMonsters) {
            System.out.println("Invalid field index.");
            throw new InvalidIndex();
        }
    }
}
