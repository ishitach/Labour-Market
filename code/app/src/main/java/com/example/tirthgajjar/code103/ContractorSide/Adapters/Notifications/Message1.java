package com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tirthgajjar.code103.ContractorSide.Adapters.Laborer_adapter;
import com.example.tirthgajjar.code103.R;

/**
 * Created by Tirth Gajjar on 3/19/2016.
 */
public class Message1 extends NotificationData.ViewHolder {

    private final TextView textView;
    private final Button accept;
    private final Button reject;



    public Message1(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.d(TAG, "Element " + getPosition() + " clicked.");


            }
        });
        textView = (TextView) itemView.findViewById(R.id.notification_type1_text);
        accept = (Button) itemView.findViewById(R.id.acceptButton);
        reject = (Button) itemView.findViewById(R.id.rejectButton);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You have accepted the request");
                accept.setVisibility(View.INVISIBLE);
                reject.setVisibility(View.INVISIBLE);
                itemView.setBackgroundColor(0xFF9E9E9E);
                setIsRecyclable(false);


                NotificationsHelper.myList.get(getAdapterPosition());


            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public TextView getTextView() {
        return textView;
    }
}