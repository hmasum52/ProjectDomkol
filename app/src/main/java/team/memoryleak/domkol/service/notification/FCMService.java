package team.memoryleak.domkol.service.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.ui.main.App;

/**
 * @author Hasan Masum
 * @see <a href="https://firebase.google.com/docs/cloud-messaging/android/client">Official docs</a>
 */
public class FCMService extends FirebaseMessagingService {

    public static final String TAG = "FCMService->";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: Message received from: " + remoteMessage.getFrom());

        // if doesn't have any notification
        if (remoteMessage.getNotification() == null) {
            return;
        }

        // get the notification
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Log.d(TAG, "onMessageReceived: title: " + notification.getTitle());
        Log.d(TAG, "onMessageReceived: body: " + notification.getBody());


        renderNotification(notification);
    }


    public void renderNotification(RemoteMessage.Notification notification) {
        // https://stackoverflow.com/questions/27394016/how-does-one-use-glide-to-download-an-image-into-a-bitmap
        // https://stackoverflow.com/questions/24840282/load-image-from-url-in-notification-android


        // create android notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(FCMService.this, App.CHANNEL_ID);
        builder.setSmallIcon(R.drawable.app_logo);
        builder.setContentTitle(notification.getTitle());
        builder.setContentText(notification.getBody());
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        try {
            FutureTarget<Bitmap> futureTarget = Glide.with(this).asBitmap().load(notification.getImageUrl()).submit();
            Bitmap bitmap = futureTarget.get();
            builder.setLargeIcon(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        builder.setAutoCancel(true);
        Notification notificationUi = builder.build();

        // render notification by NotificationManager
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt();
        manager.notify(notificationId, notificationUi);

    }
}
