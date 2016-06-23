package com.example.tirthgajjar.code103.ContractorSide.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tirthgajjar.code103.ContractorSide.laborers_lists.A_laborers_list;
import com.example.tirthgajjar.code103.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tirth Gajjar on 3/26/2016.
 */
public class Laborer_adapter extends RecyclerView.Adapter<Laborer_adapter.viewHolder> {

    LayoutInflater inflater;
    RecyclerView recyclerView;
    Context ctx;
    Boolean isInChoiceMode = false;

    List<Laborer> laborerList;

    public Laborer_adapter(Context context, List<Laborer> data) {
        inflater = LayoutInflater.from(context);
        laborerList = data;

        ctx = context;
    }

    @Override
    public Laborer_adapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.laborers_details_row, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

 /*   @Override
    public boolean onInterceptTouchEvent(RecyclerView rv,MotionEvent e){

        GestureDetectorCompat gestureDetectorcompat = new GestureDetectorCompat(ctx, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if(!isInChoiceMode){
                    isInChoiceMode=true;
       //             showActionModeToolbar();

                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    child.setHapticFeedbackEnabled(true);
                    child.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                    int selectedPosition = recyclerView.getChildAdapterPosition(child);

                    if(selectedPosition!=-1){
                        addItemToSelectedList(laborerList.get(selectedPosition).phone_num);
                        notifyDataSetChanged();
         //               updateToolBar();
                    }
                }
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                if(!isInChoiceMode){
                    isInChoiceMode=true;
     //               showActionModeToolbar();

                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    child.setHapticFeedbackEnabled(true);
                    child.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                    int selectedPosition = recyclerView.getChildAdapterPosition(child);

                    if(selectedPosition!=-1){
                        addItemToSelectedList(laborerList.get(selectedPosition).phone_num);
                        notifyDataSetChanged();
       //                 updateToolBar();
                    }
                }


            }



            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        gestureDetectorcompat.onTouchEvent(e);
        return false;

    }*/

  /*  private void addItemToSelectedList(String phone_num) {
        if (A_laborers_list.selectedItemList.contains(phone_num)) {
            A_laborers_list.selectedItemList.add(phone_num);
        } else {
            Iterator<String> i = A_laborers_list.selectedItemList.iterator();
            while (i.hasNext()) {
                String listPhone = i.next();
                if (listPhone.equals(phone_num)) {
                    i.remove();
                }
            }
            if (A_laborers_list.selectedItemList.size() < 1) {
                isInChoiceMode = false;

            }
        }
    }*/


    @Override
    public void onBindViewHolder(Laborer_adapter.viewHolder holder, int position) {
        Laborer current = laborerList.get(position);
        holder.name.setText(current.getName());
        holder.ratingBar.setRating(current.getRating());
        holder.age.setText(Integer.toString(current.getAge()));
        holder.phone_num.setText(current.getPhone_num());
        holder.village_name.setText(current.getVillageName());
        if (current.isGender() == true) {
            holder.gender.setText("Male");
        } else {
            holder.gender.setText("Female");
        }
        if (A_laborers_list.selectedLaborers != null && A_laborers_list.selectedLaborers.size() > 0) {

            //      setViewIndicatingChoiceMode(holder);
            boolean isItemInTheList = isItemInList(laborerList.get(position).phone_num);
            if (!isItemInTheList) {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
        } else {
            //        setViewIndicatingNormalMode(holder);
        }


        System.out.println("Tirth :" + holder.village_name);
    }

    private boolean isItemInList(String phone_num) {
        return A_laborers_list.selectedLaborers.contains(phone_num);

    }

    private void setItemSelected(viewHolder holder) {

        holder.itemView.setBackgroundColor(Color.WHITE);
        Toast.makeText(ctx, holder.name.getText(), Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        if (!laborerList.isEmpty()) {
            return laborerList.size();
        } else
            return 0;
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView name, age, phone_num, village_name, gender;
        RatingBar ratingBar;

        private ArrayList<Laborer> laborerList = new ArrayList<>();
        private viewHolder viewholder;
        Laborer mLaborer = new Laborer();

        public viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.LDC_name);
            age = (TextView) itemView.findViewById(R.id.LDC_age);
            phone_num = (TextView) itemView.findViewById(R.id.LDC_phoneNum);
            gender = (TextView) itemView.findViewById(R.id.LDC_gender);
            village_name = (TextView) itemView.findViewById(R.id.LDC_village);
            ratingBar = (RatingBar) itemView.findViewById(R.id.LDC_ratingBar);

        }


    }
}