package team.memoryleak.domkol.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import team.memoryleak.domkol.R;
import team.memoryleak.domkol.databinding.CardPeopleNearbyBinding;

public class NearByPeopleAdapter extends RecyclerView.Adapter<NearByPeopleAdapter.ViewHolder> {
    public static final String TAG = "NearByPeopleAdapter->";

    List<String> list;

    public NearByPeopleAdapter(){
        list = new ArrayList<>(Arrays.asList("Masum", "Sabit", "Tamim"));
        Log.d(TAG, "NearByPeopleAdapter: "+list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_people_nearby, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = list.get(position);
        holder.mVB.nameTv.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardPeopleNearbyBinding mVB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVB = CardPeopleNearbyBinding.bind(itemView);
        }
    }
}


