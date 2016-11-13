package es.guiguegon.geoapi.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Weather implements Parcelable {

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

    protected Weather(Parcel in) {
        temperature = in.readString();
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temperature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weather weather = (Weather) o;
        return temperature != null ? temperature.equals(weather.temperature)
                : weather.temperature == null;
    }

    @Override
    public int hashCode() {
        return temperature != null ? temperature.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature='" + temperature + '\'' +
                '}';
    }
}