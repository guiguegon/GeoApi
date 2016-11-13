package es.guiguegon.geoapi.features.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.data.models.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guille on 12/11/2016.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Location> locations;
    private LocationItemListener locationItemListener;
    private boolean cachedLocation;

    public LocationAdapter() {
        locations = new ArrayList<>();
    }

    public void setLocationItemListener(LocationItemListener locationItemListener) {
        this.locationItemListener = locationItemListener;
    }

    public void addLocation(Location location) {
        locations.add(location);
        notifyItemInserted(locations.size());
    }

    public void deleteLocation(Location location) {
        int position = locations.indexOf(location);
        if (position != -1) {
            locations.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        locations.clear();
        notifyDataSetChanged();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    public void setCachedLocation(boolean cachedLocation) {
        this.cachedLocation = cachedLocation;
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Location location = locations.get(position);
        fillLocation((LocationViewHolder) holder, location);
    }

    private void fillLocation(LocationViewHolder holder, Location location) {
        holder.locationName.setText(location.getName());
        holder.itemView.setOnClickListener(v -> {
            if (null != locationItemListener) {
                locationItemListener.onLocationItemClick(location);
            }
        });
        holder.locationDelete.setOnClickListener(v -> {
            if (null != locationItemListener) {
                locationItemListener.onLocationDeleteClick(location);
            }
        });
        manageDeleteImage(holder);
    }

    private void manageDeleteImage(LocationViewHolder holder) {
        if (cachedLocation) {
            holder.locationDelete.setVisibility(View.VISIBLE);
        } else {
            holder.locationDelete.setVisibility(View.GONE);
        }
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

        void onLocationDeleteClick(Location location);
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.location_name)
        TextView locationName;
        @BindView(R.id.location_delete)
        ImageView locationDelete;

        public LocationViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
