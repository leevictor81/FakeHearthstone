package com.example.victorlee.fakehearthstone.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.victorlee.fakehearthstone.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        setFullScreen();
    }

    @Override
    public void onBackPressed() {
        // Disable Back button
    }

    private void setFullScreen() {
        View overlay = findViewById(R.id.main_menu);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void togglePlayButton(View view) {
        Intent intent = new Intent(this, Play.class);

        startActivity(intent);
    }
}
