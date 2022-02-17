package team.memoryleak.domkol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import team.memoryleak.domkol.dagger.component.AppComponent;
import team.memoryleak.domkol.dagger.component.MainActivityComponent;
import team.memoryleak.domkol.dagger.module.MainActivityModule;
import team.memoryleak.domkol.ui.main.App;
import team.memoryleak.domkol.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
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