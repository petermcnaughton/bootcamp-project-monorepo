package com.sky.journeys.skyjourneys.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.AchievementResources;
import com.sky.journeys.skyjourneys.models.Achievement;
import com.sky.journeys.skyjourneys.pages.achievements.AchievementDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {
    private Context context;
    private List<Achievement> achievements;

    public AchievementAdapter(Context context, List<Achievement> achievements) {
        this.context = context;
        this.achievements = achievements;
    }

    @NonNull
    @Override
    public AchievementAdapter.AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.achievement_card, parent, false);
        AchievementAdapter.AchievementViewHolder viewHolder = new AchievementAdapter.AchievementViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementAdapter.AchievementViewHolder holder, int position) {
        final Achievement achievement = achievements.get(position);
        holder.card.setTag(achievement);
        holder.name.setText(achievement.getName());
        holder.background.setBackgroundColor(context.getResources().getColor(new AchievementResources(achievement.getId()).getColor()));
        Picasso.get().load(achievement.getImagePath()).into(holder.image);
        if (!achievement.isUnlocked()) {
            holder.background.getBackground().setAlpha(127);
            holder.lock.setVisibility(View.VISIBLE);
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Locked: " + achievement.getDescription(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Achievement achievement = (Achievement) view.getTag();
                    Intent intent = new Intent(context, AchievementDetailActivity.class);
                    intent.putExtra("id", achievement.getId());
                    intent.putExtra("name", achievement.getName());
                    intent.putExtra("description", achievement.getDescription());
                    intent.putExtra("image", achievement.getImagePath());
                    intent.putExtra("prize", achievement.getPrize());
                    intent.putExtra("prizeImage", achievement.getPrizeImagePath());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    class AchievementViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        RelativeLayout background;
        ImageView image;
        ImageView lock;

        public AchievementViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            name = itemView.findViewById(R.id.achievement_name);
            background = itemView.findViewById(R.id.background);
            image = itemView.findViewById(R.id.icon);
            lock = itemView.findViewById(R.id.lock);
        }
    }

    public Context getContext() {
        return context;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }
}
