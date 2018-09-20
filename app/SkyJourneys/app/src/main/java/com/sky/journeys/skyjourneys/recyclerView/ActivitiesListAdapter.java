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
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.models.ActivitiesListResult;

import com.sky.journeys.skyjourneys.pages.myjourneys.ActivityPage;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.ActivitiesViewHolder> {
    private Context context;
    private List<ActivitiesListResult> activitiesListResults;
    private Picasso picasso;

    public ActivitiesListAdapter(Context context, List<ActivitiesListResult> activitiesListResults, Picasso picasso) {
        this.context = context;
        this.activitiesListResults = activitiesListResults;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_activities_list, parent, false);
        ActivitiesViewHolder viewHolder = new ActivitiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, int position) {
        ActivitiesListResult activity = activitiesListResults.get(position);
        holder.card.setTag(activity);
        holder.title.setText(activity.getTitle());
        holder.date.setText(activity.getDate());
        holder.city.setText(activity.getCity());
        picasso.load(activity.getImage())
                .resize(400, 300)
                .into(holder.image);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitiesListResult activity = (ActivitiesListResult) view.getTag();
                Intent intent = new Intent(context, ActivityPage.class);
                intent.putExtra("id",activity.getId());
                intent.putExtra("title", activity.getTitle());
                intent.putExtra("date", activity.getTitle());
                intent.putExtra("city", activity.getTitle());
                intent.putExtra("duration", activity.getTitle());
                intent.putExtra("number-of-people", activity.getTitle());
                intent.putExtra("image", activity.getImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return activitiesListResults.size();


    }

    static class ActivitiesViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title;
        TextView date;
        TextView city;
        ImageView image;

        public ActivitiesViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            city = itemView.findViewById(R.id.city);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
        }
    }
}
