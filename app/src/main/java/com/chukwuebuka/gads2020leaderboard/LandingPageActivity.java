package com.chukwuebuka.gads2020leaderboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.chukwuebuka.gads2020leaderboard.Util.JsonUtil;
import com.chukwuebuka.gads2020leaderboard.data.LearningLeader;
import com.chukwuebuka.gads2020leaderboard.data.SkillLeader;
import com.chukwuebuka.gads2020leaderboard.ui.LeaderBoardActivity;

public class LandingPageActivity extends AppCompatActivity {

    public ImageView imGads;
    public ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        pbLoading = findViewById(R.id.pb_data_loading);
        imGads = findViewById(R.id.launch_image);

        if (isNetworkConnected()){
            new LeaderBoardQueryTask().execute();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert!!!")
                    .setCancelable(false)
                    .setMessage("To access this App feature, there has to be access to a strong Network")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    private class LeaderBoardQueryTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            imGads.setVisibility(View.INVISIBLE);
            pbLoading.setVisibility(View.VISIBLE);
        }

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

            imGads.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(LandingPageActivity.this, LeaderBoardActivity.class);
            startActivity(intent);
        }
    }

    private Boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        Network activeNetwork = connectivityManager.getActiveNetwork();

        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);

        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }
}