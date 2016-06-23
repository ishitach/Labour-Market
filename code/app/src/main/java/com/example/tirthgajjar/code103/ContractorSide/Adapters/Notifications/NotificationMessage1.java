package com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications;

import android.widget.Button;

import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.swipetab_fragments.Notification;

/**
 * Created by Tirth Gajjar on 4/8/2016.
 */
public class NotificationMessage1 {

    int id=0;
    String text;
    String phoneNumber;
    String raterName;
    int Rating;

    public NotificationMessage1(String text, String PhoneNum, String RaterName, int Rating){
        this.text=text;
        this.phoneNumber=PhoneNum;
        this.raterName=RaterName;
        this.Rating=Rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRaterName() {
        return raterName;
    }

    public void setRaterName(String raterName) {
        this.raterName = raterName;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int rating) {
        Rating = rating;
    }
}
