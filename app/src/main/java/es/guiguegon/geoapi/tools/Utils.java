package es.guiguegon.geoapi.tools;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.EditText;
import timber.log.Timber;

/**
 * Created by Guille on 12/11/2016.
 */

public class Utils {

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
     * shows keyboard
     */
    public static void showKeyboard(Activity activity) {
        try {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        } catch (Exception e) {
            Timber.wtf(e, "[showKeyboard]");
        }
    }
}
