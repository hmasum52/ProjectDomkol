package team.memoryleak.domkol.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.databinding.CardHomePageServiceBinding;

public class HomePageMenuAdapter extends RecyclerView.Adapter<HomePageMenuAdapter.ViewHolder> {
    public static final String TAG = "NearByPeopleAdapter->";

    public interface OnMenuClickedListener {
        void onMenuClicked(String title);
    }
    private OnMenuClickedListener onMenuClickedListener;
    List<String> titleList;
    List<Integer> iconList;

    public HomePageMenuAdapter() {
        titleList = new ArrayList<>(Arrays.asList("Track", "Scan", "Exit Guide", "Notification"));
        iconList = new ArrayList<>(Arrays.asList(
                R.drawable.ic_map_location_dot_solid,
                R.drawable.ic_magnifying_glass_location_solid,
                R.drawable.ic_person_running_solid,
                R.drawable.ic_bell_solid
        ));
    }

    public void setOnMenuClickedListener(OnMenuClickedListener onMenuClickedListener) {
        this.onMenuClickedListener = onMenuClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_page_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titleList.get(position);
        int logo = iconList.get(position);

        holder.mVB.title.setText(title);
        holder.mVB.icon.setImageResource(logo);

        holder.mVB.getRoot().setOnClickListener(view -> {
            if(onMenuClickedListener!=null){
                onMenuClickedListener.onMenuClicked(title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardHomePageServiceBinding mVB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVB = CardHomePageServiceBinding.bind(itemView);
        }
    }
}
