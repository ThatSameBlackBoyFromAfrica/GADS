package com.chukwuebuka.gads2020leaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.adapter.LearningLeadersFragmentAdapter;
import com.chukwuebuka.gads2020leaderboard.data.LearningLeader;

public class LearningLeadersFragment extends Fragment {

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        RecyclerView rvLearningLeader = view.findViewById(R.id.rv_learning_list);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        LearningLeadersFragmentAdapter learningAdapter = new LearningLeadersFragmentAdapter();

        rvLearningLeader.setLayoutManager(linearLayout);
        rvLearningLeader.setAdapter(learningAdapter);

        learningAdapter.setData(LearningLeader.getLearningLeaders());

        return view.getRootView();
    }
}