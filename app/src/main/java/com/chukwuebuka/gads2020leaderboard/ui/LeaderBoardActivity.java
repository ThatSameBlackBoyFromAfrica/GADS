package com.chukwuebuka.gads2020leaderboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.adapter.LeaderBoardPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new LeaderBoardPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }


    public void onClickSubmit(View view) {
        Intent intent = new Intent(this, ProjectSubmissionActivity.class);
        startActivity(intent);
    }
}