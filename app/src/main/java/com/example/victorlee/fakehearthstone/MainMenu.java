package com.example.victorlee.fakehearthstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.victorlee.fakehearthstone.Cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainMenu extends AppCompatActivity {

    private GameConsole gameConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        String NAME1 = "GET NAME1";
        String NAME2 = "GET NAME2";
        Player player1 = new Player(NAME1);
        Player player2 = new Player(NAME2);
        gameConsole = new GameConsole(player1, player2, false);

        // Get string name of card wanted for deck
        Stack<Card> player1Deck = intializeDeck(new ArrayList<String>());
        Stack<Card> player2Deck = intializeDeck(new ArrayList<String>());
        gameConsole.game(player1Deck, player2Deck);
    }

    private Stack<Card> intializeDeck(List<String> deckWithString) {
        Stack<Card> deck = new Stack<>();

        for (String cardName : deckWithString){
            // map cardName to card
            Card card = null;
            deck.add(card);
        }

        return deck;
    }
}
