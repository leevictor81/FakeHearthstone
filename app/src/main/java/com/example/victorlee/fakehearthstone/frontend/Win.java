package com.example.victorlee.fakehearthstone.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.victorlee.fakehearthstone.R;

public class Win extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);
    }

    @Override
    public void onBackPressed() {
        // Disable Back button
    }

    public void toggleHomeScreenButton(View view) {
        Intent intent = new Intent(this, MainMenu.class);

        startActivity(intent);
    }
}
