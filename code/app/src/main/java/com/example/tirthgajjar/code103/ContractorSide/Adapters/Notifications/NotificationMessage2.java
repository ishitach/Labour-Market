package com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications;

/**
 * Created by Tirth Gajjar on 4/8/2016.
 */
public class NotificationMessage2 {

    int id=1;
    String text;
    String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String startDate;
    String endDate;
    String city;

    public NotificationMessage2(String text, String PhoneNum, String startDate, String endDate, String city){
        this.text=text;
        this.phoneNumber=PhoneNum;
        this.startDate=startDate;
        this.endDate=endDate;
        this.city=city;
    }
}
