package com.example.victorlee.fakehearthstone.backend.cards;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.victorlee.fakehearthstone.backend.Player;

/**
 * Created by Victor Lee on 6/17/2018.
 */

public abstract class Card extends BaseObservable {
    private String name;
    private int cost;
    private String description;
    private boolean firstTurn;

    public abstract void play(Player currentPlayer, Player opponentPlayer) throws Exception;
    public abstract void play(int fieldIndex, Player player) throws Exception;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
        notifyPropertyChanged(BR.cost);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
