package com.chukwuebuka.gads2020leaderboard.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chukwuebuka.gads2020leaderboard.ui.IqSkillsLeadersFragment;
import com.chukwuebuka.gads2020leaderboard.ui.LearningLeadersFragment;

public class LeaderBoardPagerAdapter extends FragmentPagerAdapter {


    public LeaderBoardPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningLeadersFragment();
            case 1:
                return new IqSkillsLeadersFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Learning Leaders";
            case 1:
                return "Skill IQ Leaders";
        }
        return null;
    }
}
