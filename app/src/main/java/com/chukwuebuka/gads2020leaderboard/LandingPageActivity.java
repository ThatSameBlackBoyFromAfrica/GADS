package com.chukwuebuka.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chukwuebuka.gads2020leaderboard.ui.LeaderBoardActivity;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        ImageView imGads = findViewById(R.id.launch_image);
        imGads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPageActivity.this, LeaderBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}