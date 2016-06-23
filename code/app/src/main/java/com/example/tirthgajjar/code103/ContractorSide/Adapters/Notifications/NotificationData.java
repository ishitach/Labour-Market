package com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments.Notification;
import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.A_laborers_list;
import com.example.tirthgajjar.code103.R;

/**
 * Created by Tirth Gajjar on 3/19/2016.
 */

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class NotificationData extends RecyclerView.Adapter<NotificationData.ViewHolder> {
    private static final String TAG = "NotificationData";



    private String[] mDataSet;
    private int[] notificationTypes;
    private static final int TYPE1 = 0, TYPE2 = 1, TYPE3 = 2;


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

        }


    }



    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
 /*   public NotificationData(String[] dataSet, int[] notificationTypes) {
        mDataSet = dataSet;
        this.notificationTypes = notificationTypes;

    }*/
    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)

    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be

        if(NotificationsHelper.myList.get(position) instanceof NotificationMessage1){
            NotificationMessage1 notificationMessage1 =(NotificationMessage1)NotificationsHelper.myList.get(position);
            return notificationMessage1.getId();
        }
        if(NotificationsHelper.myList.get(position) instanceof NotificationMessage2){
            NotificationMessage2 notificationMessage2 = (NotificationMessage2)NotificationsHelper.myList.get(position);
            return notificationMessage2.getId();
        }
       return 0;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.

        View v;
        switch (viewType) {

            case 0:
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.notification_type1, viewGroup, false);

                return new Message1(v);

            case 1:
                v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.text_row_item, viewGroup, false);

                return new Message2(v);

            default:
                return new Message1((LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_type1, viewGroup, false)));


        }

    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //     Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        switch (viewHolder.getItemViewType()) {
            case 0:
                NotificationMessage2 notificationMessage2 = (NotificationMessage2)NotificationsHelper.myList.get(position);
                Message1 message1= (Message1) viewHolder;
                message1.getTextView().setText(notificationMessage2.getText());
                break;
            case 1:
                NotificationMessage1 notificationMessage1 = (NotificationMessage1)NotificationsHelper.myList.get(position);
                Message2 message2 = (Message2) viewHolder;
                message2.getTextView().setText(notificationMessage1.getText());
                message2.getRatingBar().setRating(notificationMessage1.getRating());
                break;
            default:

        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return NotificationsHelper.myList.size();
    }
}
