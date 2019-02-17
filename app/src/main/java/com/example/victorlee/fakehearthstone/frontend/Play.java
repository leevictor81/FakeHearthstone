package com.example.victorlee.fakehearthstone.frontend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.victorlee.fakehearthstone.R;
import com.example.victorlee.fakehearthstone.backend.Cards.Card;
import com.example.victorlee.fakehearthstone.backend.Cards.Monsters.BaseMonster;
import com.example.victorlee.fakehearthstone.backend.GameConsole;
import com.example.victorlee.fakehearthstone.backend.Player;
import com.example.victorlee.fakehearthstone.databinding.PlayBinding;
import com.example.victorlee.fakehearthstone.frontend.Listeners.DeckHoverListener;
import com.example.victorlee.fakehearthstone.frontend.Listeners.HeroDragListener;
import com.example.victorlee.fakehearthstone.frontend.Listeners.HeroTouchListener;

import java.util.List;
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
        gameConsole = new GameConsole(currentPlayer, opponentPlayer, false);

        Stack<Card> currentPlayerDeck = new Stack<>();
        Stack<Card> opponentPlayerDeck = new Stack<>();
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

            currentPlayerDeck.add(monster1);
            opponentPlayerDeck.add(monster2);
        }
        gameConsole.start(currentPlayerDeck, opponentPlayerDeck);

        binding = DataBindingUtil.setContentView(this, R.layout.play);
        binding.setGameConsole(gameConsole);

        setFullScreen();
        setUpHeroAttacking();
        setUpDeckHover();
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
