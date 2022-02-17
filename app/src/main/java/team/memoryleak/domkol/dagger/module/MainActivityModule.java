package team.memoryleak.domkol.dagger.module;

import androidx.activity.result.ActivityResultCaller;

import dagger.Module;
import dagger.Provides;
import team.memoryleak.domkol.MainActivity;
import team.memoryleak.domkol.dagger.anotation.MainActivityScope;

@Module
public class MainActivityModule{
    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @MainActivityScope
    @Provides
    ActivityResultCaller provideActivityResultCaller(){return  mainActivity;}
}
