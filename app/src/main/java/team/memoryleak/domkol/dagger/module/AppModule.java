package team.memoryleak.domkol.dagger.module;

import android.app.Application;
import android.content.Context;


import dagger.Module;
import dagger.Provides;
import team.memoryleak.domkol.dagger.anotation.AppScope;
import team.memoryleak.domkol.ui.main.App;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @AppScope
    @Provides
    App provideApp() {
        return app;
    }

    @AppScope
    @Provides
    Application provideApplication() {
        return app;
    }

    @AppScope
    @Provides
    Context provideContext() {
        return app.getApplicationContext();
    }
}
