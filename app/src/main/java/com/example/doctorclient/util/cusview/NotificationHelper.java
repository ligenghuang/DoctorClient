package com.example.doctorclient.util.cusview;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.data.MySp;

public class NotificationHelper {
    private static final String CHANNEL_ID="channel_id";   //通道渠道id
    public static final String  CHANEL_NAME="医生端"; //通道渠道名称


    @TargetApi(Build.VERSION_CODES.O)
    public static  void  show(Context context, MessageDto messageDto){
        NotificationChannel channel = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //创建 通知通道  channelid和channelname是必须的（自己命名就好）
            channel = new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        }

        if (MySp.getVibration(context)){
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{
                    100, 200, 300
            });
        }else {
            channel.enableVibration(false);
            channel.setVibrationPattern(new long[]{0});
        }
        if (MySp.getVoice(context)){
            Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            channel.setSound(sound,Notification.AUDIO_ATTRIBUTES_DEFAULT);
        }else {
            channel.setSound(null,null);
        }
        //todo 点击事件
//        Intent intent = new Intent(context, MessageDetailActivity.class);
//        intent.putExtra("touserId", messageDto.getTouserid());
//        intent.putExtra("askId", askId);

//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        channel.enableLights(true);
        Notification notification;

        //获取Notification实例   获取Notification实例有很多方法处理    在此我只展示通用的方法（虽然这种方式是属于api16以上，但是已经可以了，毕竟16以下的Android机很少了，如果非要全面兼容可以用）
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //向上兼容 用Notification.Builder构造notification对象
            notification = new Notification.Builder(context,CHANNEL_ID)
                    .setContentTitle("收到一条新信息")
                    .setContentText(messageDto.getClassX().equals("txt")?messageDto.getNote():"[图片]")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                    .setTicker("1")
                    .setAutoCancel(true)
                    .build();
        }else {
            //向下兼容 用NotificationCompat.Builder构造notification对象
            notification = new NotificationCompat.Builder(context)
                    .setContentTitle("收到一条新信息")
                    .setContentText(messageDto.getClassX().equals("txt")?messageDto.getNote():"[图片]")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                    .setTicker("1")
                    .setAutoCancel(true)
                    .build();
        }


        //发送通知
        int  notifiId=1;
        //创建一个通知管理器
        NotificationManager   notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(notifiId,notification);

    }
}
