package team.memoryleak.domkol.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.databinding.FragmentMapsBinding;
import team.memoryleak.domkol.databinding.MainFragmentBinding;
import team.memoryleak.domkol.ui.adapter.HomePageMenuAdapter;
import team.memoryleak.domkol.ui.adapter.NearByPeopleAdapter;
import team.memoryleak.domkol.ui.main.MainViewModel;

public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment->";

    private MainViewModel mViewModel;

    MainFragmentBinding mVB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mVB = MainFragmentBinding.inflate(inflater, container, false);
        return mVB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        HomePageMenuAdapter adapter = new HomePageMenuAdapter();
        mVB.menuRV.setAdapter(adapter);

        adapter.setOnMenuClickedListener(title -> {
            if (title.equalsIgnoreCase("track")) {
                NavHostFragment.findNavController(this).navigate(R.id.mapsFragment);
            } else if (title.equalsIgnoreCase("scan")) {
                NavHostFragment.findNavController(this).navigate(R.id.scanPeopleFragment);
            }else if(title.equalsIgnoreCase("Exit Guide")){
                NavHostFragment.findNavController(this).navigate(R.id.floorMapFragment);
            }
        });
    }
}