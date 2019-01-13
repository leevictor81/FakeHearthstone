package com.example.victorlee.fakehearthstone.frontend.Listeners;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.victorlee.fakehearthstone.backend.GameConsole;

public class HeroTouchListener implements View.OnTouchListener {
    GameConsole gameConsole;
    ImageView target;

    public HeroTouchListener(GameConsole gameConsole, ImageView target) {
        this.gameConsole = gameConsole;
        this.target = target;
    }

    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (!gameConsole.getCurrentPlayer().isCanAttack()) {
                return false;
            }

            int[] location = new int[2];
            v.getLocationInWindow(location);

            target.setX(location[0]);
            target.setY(location[1]);
            target.setVisibility(View.VISIBLE);

            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(target);
            target.startDrag(data, shadowBuilder, target, 0);
            target.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
