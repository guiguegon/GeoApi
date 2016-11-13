package es.guiguegon.geoapi.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Location implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private String name;
    private Bbox bbox;
    private String lat;
    private String lng;

    protected Location(Parcel in) {
        name = in.readString();
        bbox = (Bbox) in.readValue(Bbox.class.getClassLoader());
        lat = in.readString();
        lng = in.readString();
    }

    public String getName() {
        return name;
    }

    public Bbox getBbox() {
        return bbox;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(bbox);
        dest.writeString(lat);
        dest.writeString(lng);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        if (name != null ? !name.equals(location.name) : location.name != null) {
            return false;
        }
        if (bbox != null ? !bbox.equals(location.bbox) : location.bbox != null) {
            return false;
        }
        if (lat != null ? !lat.equals(location.lat) : location.lat != null) {
            return false;
        }
        return lng != null ? lng.equals(location.lng) : location.lng == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (bbox != null ? bbox.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", bbox=" + bbox +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    public boolean hasBbox() {
        return null != bbox;
    }
}
