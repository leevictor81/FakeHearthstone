package com.example.victorlee.fakehearthstone.frontend.Listeners;

import android.view.DragEvent;
import android.view.View;

import com.example.victorlee.fakehearthstone.backend.GameConsole;

public class HeroDragListener implements View.OnDragListener {
    GameConsole gameConsole;

    public HeroDragListener(GameConsole gameConsole){
        this.gameConsole = gameConsole;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                gameConsole.command("attack 0");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}
