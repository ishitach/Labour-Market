package com.example.tirthgajjar.code103.ContractorSide.Adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tirth Gajjar on 3/26/2016.
 */
public class Laborer implements Parcelable {


    public String name;
    public String phone_num;
    public String password;
    public boolean gender;
    public int age;
    public int rating;
    public String villageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
   /* public Laborer(String name, String phone_num, boolean gender,int age,float rating,String villageName){
        this.name=name;
        this.phone_num=phone_num;
        this.gender=gender;
        this.age=age;
        this.rating=rating;
        this.villageName=villageName;
    }
*/
}
