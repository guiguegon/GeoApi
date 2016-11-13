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
}