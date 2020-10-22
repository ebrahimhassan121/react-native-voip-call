package com.ajith.voipcall;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RNVoipBroadcastReciever extends  BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Application applicationContext = (Application) context.getApplicationContext();

        RNVoipNotificationHelper rnVoipNotificationHelper = new RNVoipNotificationHelper(applicationContext);
        int notificationId = intent.getIntExtra("notificationId",0);
        RNVoipSendData sendData=new RNVoipSendData(RNVoipCallModule.reactContext);
        try {
            StringBuilder builder = new StringBuilder("Extras:\n");
            for (String key : intent.getExtras().keySet()) { //extras is the Bundle containing info
                Object value = intent.getExtras().get(key); //get the current object
                builder.append(key).append(": ").append(value).append("\n"); //add the key-value pair to the
            }
            Log.i("Extras",builder.toString()); //log the data or use it as needed.

        }catch (Exception e){

        }
        switch (intent.getAction()){
            case "callDismiss":
                Log.d("missed",notificationId+"");
                RNVoipRingtunePlayer.getInstance(context).stopMusic();
                rnVoipNotificationHelper.clearNotification(notificationId);
                   sendData.sentEventToJsModule(intent);
//                 rnVoipNotificationHelper.showMissCallNotification(intent.getStringExtra("missedCallTitle"), intent.getStringExtra("missedCallBody"), intent.getStringExtra("callerId"));
                break;
            case "callTimeOut":
                sendData.sentEventToJsModule(intent);
                // rnVoipNotificationHelper.showMissCallNotification(intent.getStringExtra("missedCallTitle"), intent.getStringExtra("missedCallBody"), intent.getStringExtra("callerId"));
                break;
            default:
                break;
        }

    }
}
