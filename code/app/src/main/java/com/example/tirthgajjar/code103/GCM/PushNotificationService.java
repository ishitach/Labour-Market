package com.example.tirthgajjar.code103.GCM;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications.NotificationMessage1;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications.NotificationMessage2;
import com.example.tirthgajjar.code103.ContractorSide.Adapters.Notifications.NotificationsHelper;
import com.example.tirthgajjar.code103.ContractorSide.Swipe_tabs.SwipeTab;
import com.example.tirthgajjar.code103.ContractorSide.utils.Const;
import com.example.tirthgajjar.code103.LaborerSide.LaborerNotification;
import com.example.tirthgajjar.code103.LaborerSide.SwipeTabLaborer;
import com.example.tirthgajjar.code103.R;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by kundan on 10/22/2015.
 */
public class PushNotificationService extends GcmListenerService {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(String from, Bundle data) {
        int NotificationType = data.getInt("NotificationType");
        String message = data.getString("Message");
        String title=data.getString("Title");
        String phone_num= data.getString("Phone");
        if(NotificationType==1){

            String startdate = data.getString("StartDate");
            String enddate = data.getString("EndDate");
            String city = data.getString("City");

            NotificationMessage2 notificationMessage2 = new NotificationMessage2(message, phone_num,startdate ,enddate, city);
            NotificationsHelper.myList.add(notificationMessage2);
        }
        if (NotificationType==2){

            int Rating = data.getInt("Rating");
            String ratedBy = data.getString("RatedBy");

            NotificationMessage1 notificationMessage1= new NotificationMessage1(message,phone_num,ratedBy,Rating);
            NotificationsHelper.myList.add(notificationMessage1);
        }

//        String message2 = data.getString("Message");
//        Log.e("Message",message+""+message2);
        //createNotification(mTitle, push_msg);

//        int icon = R.drawable.ic_cast_on_1_light;
        long when = System.currentTimeMillis();
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent;
        if(Const.USER_IS.equals("Contractor")) {
            intent = new Intent(PushNotificationService.this, SwipeTab.class);
        }else{
            intent = new Intent(PushNotificationService.this, SwipeTabLaborer.class);
        }
        intent.putExtra("message",data);
        PendingIntent  pending=PendingIntent.getActivity(PushNotificationService.this, 0, intent, 0);
        Notification notification;

        notification = new Notification.Builder(PushNotificationService.this)
                .setContentTitle(title  )
                .setContentText(
                        message).setSmallIcon(R.drawable.unnamed)
                .setContentIntent(pending).setWhen(when).setAutoCancel(true)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        nm.notify(0, notification);

    }
}
