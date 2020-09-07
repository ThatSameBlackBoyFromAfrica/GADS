package com.chukwuebuka.gads2020leaderboard.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.adapter.IqSkillsLeaderFragmentAdapter;
import com.chukwuebuka.gads2020leaderboard.data.SkillLeader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IqSkillsLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IqSkillsLeadersFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_iq_skills_leaders, container, false);

        RecyclerView rvIqSkill = view.findViewById(R.id.rv_iq_skill_list);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        IqSkillsLeaderFragmentAdapter skillsAdapter = new IqSkillsLeaderFragmentAdapter();

        rvIqSkill.setLayoutManager(linearLayout);
        rvIqSkill.setAdapter(skillsAdapter);

        skillsAdapter.setData(SkillLeader.getSkillLeaders());

        return view.getRootView();
    }
}