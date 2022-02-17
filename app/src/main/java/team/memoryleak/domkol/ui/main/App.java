package team.memoryleak.domkol.ui.main;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import team.memoryleak.domkol.dagger.component.AppComponent;
import team.memoryleak.domkol.dagger.component.DaggerAppComponent;
import team.memoryleak.domkol.dagger.module.AppModule;

public class App extends Application {
    private AppComponent appComponent;
    public static final String CHANNEL_ID = "team.memoryleak.domkol";

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        //createNotificationChannel();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "notif chanel"
                    , NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}