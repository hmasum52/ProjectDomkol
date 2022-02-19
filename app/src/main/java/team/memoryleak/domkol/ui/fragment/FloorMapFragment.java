package team.memoryleak.domkol.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.databinding.FragmentFloorMapBinding;
import team.memoryleak.domkol.databinding.FragmentMapsBinding;
import team.memoryleak.domkol.databinding.MainFragmentBinding;


public class FloorMapFragment extends Fragment {

    FragmentFloorMapBinding mVB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mVB = FragmentFloorMapBinding.inflate(inflater, container, false);
        return mVB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String url = "https://tamimehsan.github.io/Domkol-Router/#/trial";
        mVB.floorMapWebView.loadUrl(url);
        WebSettings webSettings = mVB.floorMapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
    }
}