package com.example.victorlee.fakehearthstone;

import com.example.victorlee.fakehearthstone.Cards.Monsters.BaseMonster;
import com.example.victorlee.fakehearthstone.Exceptions.FieldMaxException;
import com.example.victorlee.fakehearthstone.Exceptions.InvalidIndex;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;


/**
 * Created by Victor Lee on 6/25/2018.
 */
public class FieldTest {
    private static final int TEST_ATTACK = 5;
    private static final int TEST_DEFENSE = 3;
    private static final int TEST_EFFECT_COST = 1;

    private Field field;

    private BaseMonster testMonster;

    @Before
    public void setUp() {
        this.field = new Field();
        this.testMonster = BaseMonster.builder()
                .attack(TEST_ATTACK)
                .defense(TEST_DEFENSE)
                .build();
    }

    @Test
    public void getACardsAttack() throws FieldMaxException, InvalidIndex {
        field.summon(testMonster);
        assertEquals(TEST_ATTACK, field.getAMonstersAttack(1));
    }

    @Test
    public void getAttack() throws FieldMaxException, InvalidIndex {
        int damage = 3;
        field.summon(testMonster);
        field.monsterGetsAttacked(1, damage);

        assertEquals(TEST_DEFENSE - damage, testMonster.getDefense());
    }

    @Test (expected = FieldMaxException.class)
    public void whenSummoningMonsterExceedsFieldCount_thenThrowException() throws FieldMaxException {
        field.summon(testMonster);
        field.summon(testMonster);
        field.summon(testMonster);
        field.summon(testMonster);
        field.summon(testMonster);
        field.summon(testMonster);
    }
}