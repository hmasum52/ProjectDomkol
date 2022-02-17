package team.memoryleak.domkol.dagger.component;

import dagger.Subcomponent;
import team.memoryleak.domkol.MainActivity;
import team.memoryleak.domkol.dagger.anotation.MainActivityScope;
import team.memoryleak.domkol.dagger.module.MainActivityModule;

@MainActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    //void inject(SignInFragment signInFragment);

    //void inject(HomeFragment homeFragment);

    @Subcomponent.Builder
    interface Builder{
        MainActivityComponent build();
        Builder activityModule(MainActivityModule mainActivityModule);
    }
}
