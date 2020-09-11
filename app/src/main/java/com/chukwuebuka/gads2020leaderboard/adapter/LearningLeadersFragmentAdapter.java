package com.chukwuebuka.gads2020leaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.data.LearningLeader;

import java.util.List;

public class LearningLeadersFragmentAdapter extends RecyclerView.Adapter<LearningLeadersFragmentAdapter.LearningLeaderViewHolder> {

    private List<LearningLeader> learningLeaders;

    public LearningLeadersFragmentAdapter() { }

    @NonNull
    @Override
    public LearningLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.learning_leaders_list_item, parent, false);
        return new LearningLeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeaderViewHolder holder, int position) {
        LearningLeader learningLeader = learningLeaders.get(position);
        holder.bind(learningLeader);
        holder.ivLearning.setImageResource(R.drawable.top_learner);
    }

    @Override
    public int getItemCount() {
        return (learningLeaders != null) ? learningLeaders.size() : 0;
    }

    public void setData(List<LearningLeader> learningLeaders){
        this.learningLeaders = learningLeaders;
        notifyDataSetChanged();
    }

    public static class LearningLeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvLearnerName;
        TextView tvHoursLearnt;
        ImageView ivLearning;

        public LearningLeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLearnerName = itemView.findViewById(R.id.tv_learner_name);
            tvHoursLearnt = itemView.findViewById(R.id.tv_hours_learnt);
            ivLearning = itemView.findViewById(R.id.ivLearning);
        }

        public void bind(LearningLeader leader){
            tvLearnerName.setText(leader.getName());
            tvHoursLearnt.setText(String.format("%s Learning hours, %s", leader.getHours(), leader.getCountry()));
        }
    }
}
