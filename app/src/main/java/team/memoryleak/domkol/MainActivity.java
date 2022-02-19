package team.memoryleak.domkol;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.window.SplashScreen;
import android.window.SplashScreenView;

import team.memoryleak.domkol.dagger.component.AppComponent;
import team.memoryleak.domkol.dagger.component.MainActivityComponent;
import team.memoryleak.domkol.dagger.module.MainActivityModule;
import team.memoryleak.domkol.ui.main.App;
import team.memoryleak.domkol.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private MainActivityComponent mainActivityComponent;

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @RequiresApi(api = 31)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ProjectDomkol);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setUpNavigationUI();

        getMainActivityComponent().inject(this);
    }

    private void setUpNavigationUI() {
        appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.mainFragment) // top level fragment
                .build();

        navController = Navigation.findNavController(this, R.id.mainActv_nav_host_frag);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }


    /**
     * creates {@link MainActivityComponent} if not created yet
     * @return the {@link MainActivityComponent} instance
     */
    public MainActivityComponent getMainActivityComponent(){
        if(mainActivityComponent == null){
            AppComponent appComponent = ((App) getApplication()).getAppComponent();
            mainActivityComponent = appComponent.activityComponentBuilder()
                    .activityModule(new MainActivityModule(this))
                    .build();
        }
        return mainActivityComponent;
    }
}