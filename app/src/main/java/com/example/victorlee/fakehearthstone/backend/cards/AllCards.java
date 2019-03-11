package com.example.victorlee.fakehearthstone.backend.cards;

import com.example.victorlee.fakehearthstone.backend.cards.Effects.Aoe;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Banish;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Disenchant;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.RaiseDead;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.SingleTarget;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Summon;
import com.example.victorlee.fakehearthstone.backend.cards.Effects.Unsummon;
import com.example.victorlee.fakehearthstone.backend.cards.monsters.BaseMonster;

import java.util.HashMap;
import java.util.Map;

public class AllCards {
    public static Map<String, Card> getAllCards() {
        Map<String, Card> allCards = new HashMap<>();

        // MONSTERS
        allCards.put("Air Elemental",
                BaseMonster.builder()
                .name("Air Elemental")
                .cost(0)
                .description("")
                .attack(1)
                .defense(1)
                .effect(null)
                .build()
        );
        allCards.put("Earth Elemental",
                BaseMonster.builder()
                .name("Earth Elemental")
                .cost(3)
                .description("")
                .attack(4)
                .defense(4)
                .effect(null)
                .build()
        );
        allCards.put("Fire Elemental",
                BaseMonster.builder()
                .name("Fire Elemental")
                .cost(2)
                .description("Whenever an opponent's minion enters play, deal 1 damage to it.")
                .attack(2)
                .defense(2)
                .effect(SingleTarget.builder().attackChange(0).defenseChange(-1).effectCost(0).build())
                .build()
        );
        allCards.put("Potion Seller",
                BaseMonster.builder()
                .name("Potion Seller")
                .cost(2)
                .description("At the end of your turn, all your minions gain +0/+1.")
                .attack(1)
                .defense(3)
                .effect(Aoe.builder().attackChange(0).defenseChange(1).targets(1).effectCost(0).build())
                .build()
        );
        allCards.put("Novice Pyromancer",
                BaseMonster.builder()
                .name("Novice Pyromancer")
                .cost(1)
                .description("Deal 1 damage to target minion")
                .attack(0)
                .defense(1)
                .effect(SingleTarget.builder().attackChange(0).defenseChange(-1).effectCost(1).build())
                .build()
        );
        allCards.put("Apprentice Summoner",
                BaseMonster.builder()
                .name("Apprentice Summoner")
                .cost(1)
                .description("Summon a 1/1 air elemental")
                .attack(1)
                .defense(1)
                .effect(Summon.builder().monster((Monster) allCards.get("Air Elemental")).numOfSummons(1).effectCost(1).build())
                .build()
        );
        allCards.put("Cloud Drake",
                BaseMonster.builder()
                .name("Cloud Drake")
                .cost(3)
                .description("Whenever one of your minions is destroyed, summon a 1/1 air elemental")
                .attack(2)
                .defense(2)
                .effect(Summon.builder().monster((Monster) allCards.get("Air Elemental")).numOfSummons(1).effectCost(0).build())
                .build()
        );
//        Need flip effect
//        allCards.put("Lady Luck",
//                BaseMonster.builder()
//                .name("Lady Luck")
//                .cost(1)
//                .description("Flip: Give a target minion +1/+1")
//                .attack(1)
//                .defense(1)
//                .effect(SingleTarget.builder().attackChange(1).defenseChange(1).effectCost(1).build())
//                .build()
//        );
//        Need flip effect and draw effect
//        allCards.put("King of Spades",
//                BaseMonster.builder()
//                .name("King of Spades")
//                .cost(3)
//                .description("Whenever you draw a card, Flip: Draw an extra card")
//                .attack(1)
//                .defense(4)
//                .effect()
//                .build()
//        );


        // SPELLS
        // need to add rituals
        allCards.put("Banish",
                Spell.builder()
                .name("Banish")
                .cost(2)
                .description("Destroy target minion or ritual")
                .effect(Banish.builder().effectCost(0).build())
                .build()
        );
        allCards.put("Unsummon",
                Spell.builder()
                .name("Unsummon")
                .cost(1)
                .description("Put target minion on the bottom of its owner's deck")
                .effect(Unsummon.builder().effectCost(0).build())
                .build()
        );
//        need to add rituals
//        allCards.put("Recharge",
//                Spell.builder()
//                        .name("Recharge")
//                        .cost(1)
//                        .description("Your ritual gains 3 charges")
//                        .effect(Unsummon.builder().effectCost(0).build())
//                        .build()
//        );
        allCards.put("Disenchant",
                Spell.builder()
                .name("Disenchant")
                .cost(1)
                .description("Destroy the top enchantment on target minion")
                .effect(Disenchant.builder().numOfDisenchant(1).effectCost(0).build())
                .build()
        );
        allCards.put("RaiseDead",
                Spell.builder()
                .name("RaiseDead")
                .cost(1)
                .description("Resurrect the top minion in your graveyard")
                .effect(RaiseDead.builder().numOfRaises(1).effectCost(0).build())
                .build()
        );
        allCards.put("Blizzard",
                Spell.builder()
                .name("Blizzard")
                .cost(3)
                .description("Deal 2 damage to all minions")
                .effect(Aoe.builder().attackChange(0).defenseChange(-2).targets(3).effectCost(0).build())
                .build()
        );
        allCards.put("Fireball",
                Spell.builder()
                .name("Fireball")
                .cost(1)
                .description("Deal 2 damage to a minion")
                .effect(SingleTarget.builder().attackChange(0).defenseChange(-2).effectCost(0).build())
                .build()
        );
//        need draw effect
//        allCards.put("Pot of Greed",
//                Spell.builder()
//                .name("Pot of Greed")
//                .cost(1)
//                .description("Draw 2 cards")
//                .effect()
//                .build()
//        );

        return allCards;
    }
}
