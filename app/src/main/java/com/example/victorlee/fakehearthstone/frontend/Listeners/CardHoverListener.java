package com.example.victorlee.fakehearthstone.frontend.Listeners;

import android.databinding.DataBindingUtil;
import android.view.MotionEvent;
import android.view.View;

import com.example.victorlee.fakehearthstone.R;
import com.example.victorlee.fakehearthstone.backend.GameConsole;
import com.example.victorlee.fakehearthstone.backend.cards.Card;

public class CardHoverListener implements View.OnTouchListener {
    private View playView;
    private GameConsole gameConsole;
    private int index;
    private boolean hand;

    public CardHoverListener(View playView, GameConsole gameConsole, int index, boolean hand) {
        this.playView = playView;
        this.gameConsole = gameConsole;
        this.index = index;
        this.hand = hand;
    }

    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Card card = hand
                    ? gameConsole.getCurrentPlayer().getHand().getCard(index)
                    : gameConsole.getCurrentPlayer().getField().getMonster(index);
            gameConsole.setCardPreview(card);
            playView.findViewById(R.id.cardPreview).setVisibility(View.VISIBLE);
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            playView.findViewById(R.id.cardPreview).setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
