package team.memoryleak.domkol.dagger.component;


import dagger.Component;
import team.memoryleak.domkol.dagger.anotation.AppScope;
import team.memoryleak.domkol.dagger.module.AppModule;
import team.memoryleak.domkol.dagger.module.NetworkModule;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent{
    MainActivityComponent.Builder activityComponentBuilder();
}
