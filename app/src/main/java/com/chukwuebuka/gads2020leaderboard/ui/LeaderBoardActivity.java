package com.chukwuebuka.gads2020leaderboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.Util.JsonUtil;
import com.chukwuebuka.gads2020leaderboard.adapter.LeaderBoardPagerAdapter;
import com.chukwuebuka.gads2020leaderboard.data.LearningLeader;
import com.chukwuebuka.gads2020leaderboard.data.SkillLeader;
import com.google.android.material.tabs.TabLayout;

import java.net.URL;
import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        new LeaderBoardQueryTask().execute();

        Button bnSubmit = findViewById(R.id.bn_submit);
        bnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });

        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new LeaderBoardPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }

    private void onSubmit() {
        Intent intent = new Intent(this, ProjectSubmissionFragment.class);
        startActivity(intent);
    }

    private static class LeaderBoardQueryTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {
            String[] results = new String[2];
            results[0] = JsonUtil.getJsonLearningLeader();
            results[1] = JsonUtil.getJsonSkillLeader();
            return results;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            LearningLeader.setLearningLeaders(JsonUtil.getLearningLeaderFromJson(strings[0]));
            SkillLeader.setSkillLeaders(JsonUtil.getIqSkillLeaderFromJson(strings[1]));
        }
    }
}