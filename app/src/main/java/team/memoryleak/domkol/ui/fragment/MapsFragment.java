package team.memoryleak.domkol.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.databinding.FragmentMapsBinding;

/**
 * @author Hasan Masum
 *
 * @see <a href="https://developers.google.com/maps/documentation/android-sdk/start">android map quick start</a>
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback{

    public static final String TAG = "MapsFragment->";
    FragmentMapsBinding mVB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mVB =  FragmentMapsBinding.inflate(inflater, container, false);
        return mVB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap();
    }

    /**
     * get the map fragment and start the MapAsync
     */
    private void initMap() {
        Log.d(TAG, "initMap: ");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        try {
            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: google map is ready");

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng latLng = new LatLng(23.8851391,90.3153899);
        Marker marker = addMarker(googleMap, latLng, "Southern Clothings LTD");
        marker.showInfoWindow();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }

    public Bitmap getMarkerBitmap(Context context){
        Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), R.drawable.fire);
        return Bitmap.createScaledBitmap(bitmap, 100 , 100, false);
    }

    private Marker addMarker(GoogleMap map, LatLng latLng, String title){
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(getMarkerBitmap(getContext()));
        return map.addMarker(new MarkerOptions()
                .position(latLng)
                .flat(true)
                .icon(bitmapDescriptor)
                .title(title)

        );
    }
}