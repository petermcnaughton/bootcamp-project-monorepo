package com.sky.journeys.skyjourneys.cardslider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.interfaces.CardAdapter;
import com.sky.journeys.skyjourneys.models.WishlistItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CardFragment extends Fragment {
    private WishlistItem wishlistItem;
    private CardView cardView;

    private static int currentCardNum;
    private int numCards;

    public static Fragment getInstance(int position, List<WishlistItem> dataset) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("id", dataset.get(position).getId());
        args.putString("location", dataset.get(position).getLocation());
        args.putString("image", dataset.get(position).getImage());
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cardslider, container, false);

        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
        currentCardNum = getArguments().getInt("position") + 1;

        TextView location = (TextView) view.findViewById(R.id.location);
        ImageView background = (ImageView) view.findViewById(R.id.background);

//        Uncomment for fragment deletion button
//        ImageView deleteButton = (ImageView)view.findViewById(R.id.xbutton);

        location.setText(getArguments().getString("location"));
        Picasso.get().load(getArguments().getString("image")).resize(1800, 2500).centerCrop().into(background);

//        Uncomment for fragment deletion popup
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case DialogInterface.BUTTON_POSITIVE:
//                                Toast.makeText(getActivity(), "Wishlist location removed!", Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    }
//                };
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Do you want to remove this location from your wishlist?").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
//
//            }
//        });


        return view;
    }

    public CardView getCardView() {
        return cardView;
    }

}
