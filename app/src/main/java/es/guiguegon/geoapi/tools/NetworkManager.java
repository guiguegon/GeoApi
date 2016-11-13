package es.guiguegon.geoapi.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.exceptions.NetworkConnectionException;
import javax.inject.Inject;
/**
 * Created by guiguegon on 12/11/2016.
 */

/**
 * Connectivity manager
 */
public class NetworkManager {

    @Inject
    public NetworkManager() {
    }

    private static NetworkInfo getActiveNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null ? cm.getActiveNetworkInfo() : null;
    }

    /**
     * Checks if there is any active network
     */
    public boolean isInternetAvailable(Context context) {
        NetworkInfo activeNetwork = getActiveNetwork(context);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Checks if the active connection is {@link ConnectivityManager#TYPE_WIFI}
     */
    public boolean isWifiConnected(Context context) {
        NetworkInfo activeNetwork = getActiveNetwork(context);
        return activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Checks if Internet is available or throws a {@link NetworkConnectionException}
     *
     * @param context required param
     * @throws NetworkConnectionException
     */
    public void checkConnectivity(Context context) throws NetworkConnectionException {
        if (!isInternetAvailable(context)) {
            showNoInternetConnectionDialog(context);
            throw new NetworkConnectionException();
        }
    }

    /**
     * Returns if the active network is {@link ConnectivityManager#TYPE_WIFI}. If there is no
     * internet will throw a {@link
     * NetworkConnectionException}
     */
    public boolean checkWifi(Context context) throws NetworkConnectionException {
        checkConnectivity(context);
        return isWifiConnected(context);
    }

    private void showNoInternetConnectionDialog(Context context) {
        new AlertDialog.Builder(context).setTitle(R.string.connectivity_title)
                .setMessage(R.string.connectivity_message)
                .setPositiveButton(R.string.ok_button, (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .create()
                .show();
    }
}
