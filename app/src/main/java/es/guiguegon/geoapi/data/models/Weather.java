package es.guiguegon.geoapi.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Weather implements Parcelable, ClusterItem {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
    private String temperature;
    private String lat;
    private String lng;

    protected Weather(Parcel in) {
        temperature = in.readString();
        lat = in.readString();
        lng = in.readString();
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(Double.valueOf(lat), Double.valueOf(lng));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temperature);
        dest.writeString(lat);
        dest.writeString(lng);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature='" + temperature + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}