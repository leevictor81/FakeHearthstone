package com.example.victorlee.fakehearthstone.frontend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.victorlee.fakehearthstone.R;
import com.example.victorlee.fakehearthstone.backend.cards.AllCards;
import com.example.victorlee.fakehearthstone.backend.cards.Card;
import com.example.victorlee.fakehearthstone.backend.GameConsole;
import com.example.victorlee.fakehearthstone.backend.Player;
import com.example.victorlee.fakehearthstone.databinding.PlayBinding;
import com.example.victorlee.fakehearthstone.frontend.Listeners.CardHoverListener;
import com.example.victorlee.fakehearthstone.frontend.Listeners.DeckHoverListener;
import com.example.victorlee.fakehearthstone.frontend.Listeners.HeroDragListener;
import com.example.victorlee.fakehearthstone.frontend.Listeners.HeroTouchListener;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Victor Lee on 12/22/2018.
 */

public class Play extends AppCompatActivity {
    private static final String NAME1 = "GET NAME1";
    private static final String NAME2 = "GET NAME2";

    private GameConsole gameConsole;
    private PlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Player currentPlayer = new Player(NAME1, getResources().getDrawable(R.drawable.pepehands));
        Player opponentPlayer = new Player(NAME2, getResources().getDrawable(R.drawable.monkas));
        gameConsole = new GameConsole(currentPlayer, opponentPlayer, true);

        Stack<Card> currentPlayerDeck = new Stack<>();
        Stack<Card> opponentPlayerDeck = new Stack<>();

        Map<String, Card> allCards = AllCards.getAllCards();
        addToDeck(allCards, currentPlayerDeck);
        addToDeck(allCards, opponentPlayerDeck);

        gameConsole.start(currentPlayerDeck, opponentPlayerDeck);

        binding = DataBindingUtil.setContentView(this, R.layout.play);
        binding.setGameConsole(gameConsole);

        setFullScreen();
        setUpHeroAttacking();
        setUpDeckHover();
        setUpCardPreview();
    }

    private void addToDeck(Map<String, Card> allCards, Stack<Card> deck) {
        deck.push(allCards.get("Air Elemental"));
        deck.push(allCards.get("Fireball"));
        deck.push(allCards.get("Earth Elemental"));
        deck.push(allCards.get("Potion Seller"));
        deck.push(allCards.get("Banish"));
        deck.push(allCards.get("Fire Elemental"));
        deck.push(allCards.get("Novice Pyromancer"));
        deck.push(allCards.get("Apprentice Summoner"));
        deck.push(allCards.get("Unsummon"));
        deck.push(allCards.get("Disenchant"));
        deck.push(allCards.get("RaiseDead"));
        deck.push(allCards.get("Blizzard"));
    }

    @Override
    public void onBackPressed() {
        // Disable Back button
    }

    private void setFullScreen() {
        View overlay = findViewById(R.id.play);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void setUpDeckHover() {
        findViewById(R.id.currentPlayerDeck).setOnTouchListener(new DeckHoverListener());
        findViewById(R.id.opponentPlayerDeck).setOnTouchListener(new DeckHoverListener());
    }

    private void setUpHeroAttacking() {
        ImageView target = findViewById(R.id.target);
        findViewById(R.id.currentPlayer).setOnTouchListener(new HeroTouchListener(gameConsole, target));
        findViewById(R.id.opponentPlayer).setOnDragListener(new HeroDragListener(gameConsole));
    }

    private void setUpCardPreview() {
        LinearLayout hand = findViewById(R.id.currentPlayerHand);
        for (int i = 0; i < hand.getChildCount(); i++) {
            View card = hand.getChildAt(i);
            card.setOnTouchListener(new CardHoverListener(findViewById(R.id.play), gameConsole, i+1));
        }
    }

    public void endTurn(View view) {
        gameConsole.command("end");
        setUpHeroAttacking();
    }

    public void incrementAttack(View view) {
        gameConsole.getCurrentPlayer().setAttack(5);
    }

    public void decrementAttack(View view) {
        gameConsole.getCurrentPlayer().setAttack(0);
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
