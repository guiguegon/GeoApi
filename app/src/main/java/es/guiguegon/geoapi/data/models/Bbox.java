package es.guiguegon.geoapi.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guille on 12/11/2016.
 */

public class Bbox implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Bbox> CREATOR = new Parcelable.Creator<Bbox>() {
        @Override
        public Bbox createFromParcel(Parcel in) {
            return new Bbox(in);
        }

        @Override
        public Bbox[] newArray(int size) {
            return new Bbox[size];
        }
    };
    private double south;
    private double east;
    private double north;
    private double west;

    protected Bbox(Parcel in) {
        south = in.readDouble();
        east = in.readDouble();
        north = in.readDouble();
        west = in.readDouble();
    }

    public double getSouth() {
        return south;
    }

    public double getEast() {
        return east;
    }

    public double getNorth() {
        return north;
    }

    public double getWest() {
        return west;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(south);
        dest.writeDouble(east);
        dest.writeDouble(north);
        dest.writeDouble(west);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bbox bbox = (Bbox) o;
        if (Double.compare(bbox.south, south) != 0) {
            return false;
        }
        if (Double.compare(bbox.east, east) != 0) {
            return false;
        }
        if (Double.compare(bbox.north, north) != 0) {
            return false;
        }
        return Double.compare(bbox.west, west) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(south);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(east);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(north);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(west);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Bbox{" +
                "south=" + south +
                ", east=" + east +
                ", north=" + north +
                ", west=" + west +
                '}';
    }
}