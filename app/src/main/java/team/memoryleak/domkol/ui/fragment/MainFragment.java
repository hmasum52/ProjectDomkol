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

        NearByPeopleAdapter adapter = new NearByPeopleAdapter();
        mVB.peopleNearbyRV.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        mVB.searchNearByLottie.addAnimatorListener(new Animator.AnimatorListener() {
            private int cnt = 0;

            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onResume():onAnimationStart: lottieAnimation started");
                mVB.peopleNearbyRV.setVisibility(View.GONE);
                mVB.rescanBtn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onResume():onAnimationEnd: lottieAnimation finished. hiding lottie animation");
                //hide progress bar
                mVB.searchNearByLottie.setVisibility(View.GONE);
                mVB.peopleNearbyRV.setVisibility(View.VISIBLE);
                mVB.rescanBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                cnt++;
                Log.d(TAG, "onAnimationRepeat: cnt : " + cnt);

                mVB.searchNearByLottie.cancelAnimation();
                //hide progress bar
                mVB.searchNearByLottie.setVisibility(View.GONE);
                mVB.peopleNearbyRV.setVisibility(View.VISIBLE);
                mVB.rescanBtn.setVisibility(View.VISIBLE);


            }
        });

        mVB.rescanBtn.setOnClickListener(view -> {
            Log.d(TAG, "rescan");
        });
    }
}