package com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tirthgajjar.code103.R;

/**
 * Created by Tirth Gajjar on 3/19/2016.
 */
public class Message2 extends NotificationData.ViewHolder {


    private final TextView textView;
    public static RatingBar ratingBar;

    public Message2(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.d(TAG, "Element " + getPosition() + " clicked.");
            }
        });
        textView = (TextView) itemView.findViewById(R.id.textView);
        ratingBar =(RatingBar) itemView.findViewById(R.id.myRating);

    }

    public TextView getTextView() {
        return textView;
    }
    public RatingBar getRatingBar(){
        return ratingBar;
    }
}