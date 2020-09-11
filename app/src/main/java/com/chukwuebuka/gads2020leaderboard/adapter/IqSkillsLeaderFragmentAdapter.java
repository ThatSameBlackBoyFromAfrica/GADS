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
import com.chukwuebuka.gads2020leaderboard.data.SkillLeader;

import java.util.List;

public class IqSkillsLeaderFragmentAdapter extends RecyclerView.Adapter<IqSkillsLeaderFragmentAdapter.IqSkillsLeaderViewHolder> {

    List<SkillLeader> skillLeaders;

    public IqSkillsLeaderFragmentAdapter() { }

    @NonNull
    @Override
    public IqSkillsLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.iq_skills_leaders_list_item, parent, false);
        return new IqSkillsLeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IqSkillsLeaderViewHolder holder, int position) {
        SkillLeader skillLeader = skillLeaders.get(position);
        holder.bind(skillLeader);
        holder.imSkillImage.setImageResource(R.drawable.skill_iq_trimmed);
    }

    @Override
    public int getItemCount() {
        return (skillLeaders != null) ? skillLeaders.size() : 0;
    }

    public void setData(List<SkillLeader> skillLeaders){
        this.skillLeaders = skillLeaders;
        notifyDataSetChanged();
    }


    public static class IqSkillsLeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvIqName;
        TextView tvIqSkill;
        ImageView imSkillImage;

        public IqSkillsLeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIqName = itemView.findViewById(R.id.tv_iq_name);
            tvIqSkill = itemView.findViewById(R.id.tv_iq_skill);
            imSkillImage = itemView.findViewById(R.id.imageView);
        }

        public void bind(SkillLeader leader){
            tvIqName.setText(leader.getName());
            tvIqSkill.setText(String.format("%s skill IQ Score, %s", leader.getScore(), leader.getCountry()));
        }
    }
}
