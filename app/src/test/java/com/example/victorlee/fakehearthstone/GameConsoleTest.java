package com.example.victorlee.fakehearthstone;

import com.example.victorlee.fakehearthstone.backend.cards.Card;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Aoe;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Banish;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Disenchant;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Effect;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.RaiseDead;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.SingleTarget;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Summon;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Unsummon;
import com.example.victorlee.fakehearthstone.backend.cards.Enchantments.Buff;
import com.example.victorlee.fakehearthstone.backend.cards.Monster;
import com.example.victorlee.fakehearthstone.backend.cards.monsters.BaseMonster;
import com.example.victorlee.fakehearthstone.backend.cards.Spell;
import com.example.victorlee.fakehearthstone.backend.GameConsole;
import com.example.victorlee.fakehearthstone.backend.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static com.example.victorlee.fakehearthstone.FakeSystemIn.StubbedInputStream.stubInputStream;
import static org.junit.Assert.assertEquals;

/**
 * Created by Victor Lee on 8/4/2018.
 */
public class GameConsoleTest {
    private Player player1;
    private Player player2;

    private Stack<Card> player1Deck;
    private Stack<Card> player2Deck;

    private GameConsole gameConsole;

    @Before
    public void setUp() {
        this.player1 = new Player("player1", null);
        this.player1Deck = new Stack<Card>();
        this.player2 = new Player("player2", null);
        this.player2Deck = new Stack<Card>();
        this.gameConsole = new GameConsole(player1, player2, true);
    }

    @Test
    public void player1SummonsMonster_andAttackPlayer2Face() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster1 = BaseMonster.builder()
                .attack(5)
                .defense(4)
                .canAttack(true)
                .build();
            Card monster2 = BaseMonster.builder()
                .attack(4)
                .defense(4)
                .canAttack(true)
                .build();

            player1Deck.add(monster1);
            player2Deck.add(monster2);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("attack 1")
                .addInstruction("end")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(5, player1.getField().getAMonstersAttack(1));
        assertEquals(25, player2.getLife());
    }

    @Test
    public void player1SummonsMonster_andPlayer2UsesSpellToDamageIt() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                .attack(5)
                .defense(4)
                .canAttack(true)
                .build();
            Effect singleTargetEffect = SingleTarget.builder()
                .attackChange(0)
                .defenseChange(-3)
                .effectCost(0)
                .build();
            Spell singleTargetSpell = Spell.builder()
                .effect(singleTargetEffect)
                .build();

            player1Deck.add(monster);
            player2Deck.add(singleTargetSpell);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1 2 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(1, player1.getField().getNumOfMonsters());
        assertEquals(1, player1.getField().getAMonstersDefense(1));
    }

    @Test
    public void player1And2SummonsMonsters_andPlayer2UsesAoeToDestroyAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster1 = BaseMonster.builder()
                .attack(5)
                .defense(4)
                .canAttack(true)
                .build();
            Card monster2 = BaseMonster.builder()
                .attack(5)
                .defense(4)
                .canAttack(true)
                .build();
            Effect aoeEffect = Aoe.builder()
                .defenseChange(-4)
                .targets(3)
                .effectCost(0)
                .build();
            Spell aoeSpell = Spell.builder()
                .effect(aoeEffect)
                .build();

            player1Deck.add(aoeSpell);
            player1Deck.add(monster1);
            player2Deck.add(aoeSpell);
            player2Deck.add(monster2);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 3")
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 3")
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(0, player1.getField().getNumOfMonsters());
        assertEquals(2, player1.getGraveyard().getNumOfCardsInGraveyard());
        assertEquals(0, player2.getField().getNumOfMonsters());
        assertEquals(2, player2.getGraveyard().getNumOfCardsInGraveyard());
    }

    @Test
    public void player1SummonsMonsters_andPlayer2UsesBanishToDestroyIt() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                .attack(5)
                .defense(100)
                .canAttack(true)
                .build();
            Effect banishEffect = Banish.builder()
                .effectCost(0)
                .build();
            Spell banishSpell = Spell.builder()
                .effect(banishEffect)
                .build();

            player1Deck.add(monster);
            player2Deck.add(banishSpell);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1 2 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(0, player1.getField().getNumOfMonsters());
        assertEquals(1, player1.getGraveyard().getNumOfCardsInGraveyard());
    }

    @Test
    public void player1SummonsMonsters_andPlayer2UsesUnsummonToReturnCard() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                .attack(5)
                .defense(100)
                .canAttack(true)
                .build();
            Effect unsummonEffect = Unsummon.builder()
                .effectCost(0)
                .build();
            Spell unsummonSpell = Spell.builder()
                .effect(unsummonEffect)
                .build();

            player1Deck.add(monster);
            player2Deck.add(unsummonSpell);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1 2 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(0, player1.getField().getNumOfMonsters());
        assertEquals(4, player1.getHand().getNumOfCardsInHand());
    }

    @Test
    public void player1SummonsMonsters_andPlayer2BuffsIt() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                    .attack(5)
                    .defense(3)
                    .canAttack(true)
                    .build();
            Buff buff = Buff.builder()
                    .attackChange(3)
                    .defenseChange(3)
                    .build();

            player1Deck.add(monster);
            player2Deck.add(buff);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1 2 1")
                .addInstruction("play 1 2 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(1, player1.getField().getNumOfMonsters());
        assertEquals(11, player1.getField().getAMonstersAttack(1));
        assertEquals(9, player1.getField().getAMonstersDefense(1));
    }

    @Test
    public void player1SummonsMonsters_andPlayer2BuffsItAndPlayer1DisenchantIt() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                .attack(5)
                .defense(3)
                .canAttack(true)
                .build();
            Buff buff = Buff.builder()
                .attackChange(3)
                .defenseChange(3)
                .build();

            player1Deck.add(monster);
            player2Deck.add(buff);
        }
        Effect disenchantEffect = Disenchant.builder()
                .numOfDisenchant(4)
                .effectCost(0)
                .build();
        Spell disenchantSpell = Spell.builder()
                .effect(disenchantEffect)
                .build();
        player1Deck.add(disenchantSpell);

        System.setIn(stubInputStream()
                .addInstruction("play 2")
                .addInstruction("end")
                .addInstruction("play 1 2 1")
                .addInstruction("play 1 2 1")
                .addInstruction("play 1 2 1")
                .addInstruction("end")
                .addInstruction("play 1 1 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(1, player1.getField().getNumOfMonsters());
        assertEquals(5, player1.getField().getAMonstersAttack(1));
        assertEquals(3, player1.getField().getAMonstersDefense(1));
    }

    @Test
    public void player1SummonsMonsters_andPlayer2DestroysIt_andPlayer1RaisesDeadAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            Card monster = BaseMonster.builder()
                    .attack(5)
                    .defense(3)
                    .canAttack(true)
                    .build();
            Effect aoeEffect = Aoe.builder()
                    .defenseChange(-4)
                    .targets(2)
                    .effectCost(0)
                    .build();
            Spell aoeSpell = Spell.builder()
                    .effect(aoeEffect)
                    .build();

            player1Deck.add(monster);
            player2Deck.add(aoeSpell);
        }
        Effect raiseDeadEffect = RaiseDead.builder()
                .numOfRaises(4)
                .effectCost(0)
                .build();
        Spell raiseDeadSpell = Spell.builder()
                .effect(raiseDeadEffect)
                .build();
        player1Deck.add(raiseDeadSpell);

        System.setIn(stubInputStream()
                .addInstruction("play 2")
                .addInstruction("play 2")
                .addInstruction("play 2")
                .addInstruction("end")
                .addInstruction("play 1")
                .addInstruction("end")
                .addInstruction("play 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(3, player1.getField().getNumOfMonsters());
    }

    @Test
    public void player1UsesSummonSpell_toSummonMonsters() throws Exception {
        for (int i = 0; i < 10; i++) {
            Monster monster = BaseMonster.builder()
                    .attack(5)
                    .defense(3)
                    .canAttack(true)
                    .build();
            Effect summonEffect = Summon.builder()
                    .monster(monster)
                    .numOfSummons(5)
                    .effectCost(0)
                    .build();
            Spell summonSpell = Spell.builder()
                    .effect(summonEffect)
                    .build();

            player1Deck.add(summonSpell);
            player2Deck.add(summonSpell);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("play 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(5, player1.getField().getNumOfMonsters());
    }

    @Test
    public void player1UsesMonsterSummonEffect_toSummonMonster() throws Exception {
        for (int i = 0; i < 10; i++) {
            Monster monster = BaseMonster.builder()
                    .attack(2)
                    .defense(1)
                    .canAttack(true)
                    .build();
            Effect summonEffect = Summon.builder()
                    .monster(monster)
                    .numOfSummons(2)
                    .effectCost(0)
                    .build();
            Monster effectMonster = BaseMonster.builder()
                    .attack(2)
                    .defense(1)
                    .effect(summonEffect)
                    .canAttack(true)
                    .build();

            player1Deck.add(effectMonster);
            player2Deck.add(effectMonster);
        }

        System.setIn(stubInputStream()
                .addInstruction("play 1")
                .addInstruction("use 1")
                .addInstruction("quit")
                .end()
        );

        gameConsole.game(player1Deck, player2Deck);

        assertEquals(3, player1.getField().getNumOfMonsters());
    }
}