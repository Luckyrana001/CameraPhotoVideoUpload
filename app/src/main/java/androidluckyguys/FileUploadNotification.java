package androidluckyguys;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by lucky on 29/06/2017.
 */

public class FileUploadNotification {
    public static NotificationManager mNotificationManager;
    static NotificationCompat.Builder builder;
    static Context context;
    static int NOTIFICATION_ID = 111;
    static FileUploadNotification fileUploadNotification;

    /*public static FileUploadNotification createInsance(Context context) {
        if(fileUploadNotification == null)
            fileUploadNotification = new FileUploadNotification(context);

        return fileUploadNotification;
    }*/
    public FileUploadNotification(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("start uploading...")
                .setContentText("file name")
                .setSmallIcon(android.R.drawable.stat_sys_upload)
                .setProgress(100, 0, false)
                .setAutoCancel(false);
    }

    public static void updateNotification(String percent, String fileName, String contentText) {
        try {
            builder.setContentText(contentText)
                    .setContentTitle(fileName)
                    //.setSmallIcon(android.R.drawable.stat_sys_download)
                    .setOngoing(true)
                    .setContentInfo(percent + "%")
                    .setProgress(100, Integer.parseInt(percent), false);

            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
            if (Integer.parseInt(percent) == 100)
                deleteNotification();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("Error...Notification.", e.getMessage() + ".....");
            e.printStackTrace();
        }
    }

    public static void failUploadNotification(/*int percentage, String fileName*/) {
        Log.e("downloadsize", "failed notification...");

        if (builder != null) {
       /* if (percentage < 100) {*/
            builder.setContentText("Uploading Failed")
                    //.setContentTitle(fileName)
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done)
                    .setOngoing(false);
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        /*} else {
            mNotificationManager.cancel(NOTIFICATION_ID);
            builder = null;
        }*/
        } else {
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    }

    public static void deleteNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
        builder = null;
    }
}