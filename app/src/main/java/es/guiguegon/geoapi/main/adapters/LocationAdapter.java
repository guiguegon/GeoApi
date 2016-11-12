package es.guiguegon.geoapi.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.data.models.Location;

/**
 * Created by Guille on 12/11/2016.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Location> locations;
    private LocationItemListener locationItemListener;

    public LocationAdapter() {
        locations = new ArrayList<>();
    }

    public void setLocationItemListener(LocationItemListener locationItemListener) {
        this.locationItemListener = locationItemListener;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    public void addLocation(Location location) {
        locations.add(location);
        notifyItemInserted(locations.size());
    }

    public void clear() {
        locations.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Location location = locations.get(position);
        fillLocation((LocationViewHolder) holder, location);
    }

    private void fillLocation(LocationViewHolder holder, Location location) {
        holder.locationName.setText(location.getName());
        holder.itemView.setOnClickListener(v -> {
            if (locationItemListener != null) {
                locationItemListener.onLocationItemClick(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public interface LocationItemListener {
        void onLocationItemClick(Location location);
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.location_name)
        TextView locationName;

        public LocationViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
