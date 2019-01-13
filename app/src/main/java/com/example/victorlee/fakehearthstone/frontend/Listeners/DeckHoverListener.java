package com.example.victorlee.fakehearthstone.frontend.Listeners;

import android.view.MotionEvent;
import android.view.View;

import com.example.victorlee.fakehearthstone.R;

public class DeckHoverListener implements View.OnTouchListener {
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            v.findViewById(R.id.deckSize).setVisibility(View.VISIBLE);
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            v.findViewById(R.id.deckSize).setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
