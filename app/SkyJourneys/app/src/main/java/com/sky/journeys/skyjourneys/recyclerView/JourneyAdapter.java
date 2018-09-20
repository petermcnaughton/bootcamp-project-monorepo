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
import com.sky.journeys.skyjourneys.models.CurrentJourneyResult;
import com.sky.journeys.skyjourneys.pages.myjourneys.JourneyInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> {
    private Context context;
    private List<CurrentJourneyResult> journeys;

    public JourneyAdapter(Context context, List<CurrentJourneyResult> journeys) {
        this.context = context;
        this.journeys = journeys;
    }

    @NonNull
    @Override
    public JourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.journey_card, parent, false);
        JourneyViewHolder viewHolder = new JourneyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyViewHolder holder, int position) {
        CurrentJourneyResult journey = journeys.get(position);
        holder.card.setTag(journey);
        holder.to.setText(journey.getTo());
        holder.dates.setText(journey.getOutboundDate() + " - " + journey.getInboundDate());
        Picasso.get().load(journey.getImage()).fit().centerCrop().into(holder.image);

        if (context.getClass().getSimpleName().equals("CurrentBooking")) {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CurrentJourneyResult journey = (CurrentJourneyResult) view.getTag();
                    Intent intent = new Intent(context, JourneyInfoActivity.class);
                    intent.putExtra("id", journey.getId());
                    intent.putExtra("location", journey.getTo());
                    intent.putExtra("dates", journey.getOutboundDate() + " - " + journey.getInboundDate());
                    intent.putExtra("image", journey.getImage());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    class JourneyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView to;
        TextView dates;
        ImageView image;

        public JourneyViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            to = itemView.findViewById(R.id.to);
            dates = itemView.findViewById(R.id.dates);
            image = itemView.findViewById(R.id.background_image);
        }
    }

    public Context getContext() {
        return context;
    }

    public List<CurrentJourneyResult> getJourneys() {
        return journeys;
    }
}
