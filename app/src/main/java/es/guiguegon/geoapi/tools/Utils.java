package es.guiguegon.geoapi.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.EditText;
import timber.log.Timber;

/**
 * Created by Guille on 12/11/2016.
 */

public class Utils {

    public static final int REQUEST_CODE_PERMISSION = 142;
    public static final int REQUEST_CODE_PERMISSION_SETTINGS = 556;

    private Utils() {
    }

    /**
     * Return an EditText widget text
     */
    public static String toString(EditText editText) {
        return editText.getText().toString();
    }

    /**
     * Checks if a String is not empty (""), not null and not whitespace only.
     * Avoid using TextUtils
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null and not whitespace
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Checks if a String is whitespace, empty ("") or null.
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Closes keyboard
     */
    public static void hideKeyboard(Activity activity) {
        try {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } catch (Exception e) {
            Timber.wtf(e, "[hideKeyboard]");
        }
    }

    /**
     * Open application settings. It may be used to let the user grant permissions.
     *
     * @param activity calling activity
     */
    public static void showAppSettings(Activity activity, int requestCode) {
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + activity.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        activity.startActivityForResult(i, requestCode);
    }
}
