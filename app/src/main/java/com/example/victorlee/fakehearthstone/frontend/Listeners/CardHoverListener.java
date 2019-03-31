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
    private int handIndex;

    public CardHoverListener(View playView, GameConsole gameConsole, int handIndex) {
        this.playView = playView;
        this.gameConsole = gameConsole;
        this.handIndex = handIndex;
    }

    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Card card = gameConsole.getCurrentPlayer().getHand().getCard(handIndex);
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
