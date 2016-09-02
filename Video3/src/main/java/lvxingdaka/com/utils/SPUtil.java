package lvxingdaka.com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPUtil {
    private static SharedPreferences getSP(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getString(Context context, String key) {
        return getSP(context).getString(key, "");
    }

    public static String getString(Context context, String key, String defValue) {
        return getSP(context).getString(key, defValue);
    }
    public static Boolean getBoolean(Context context, String key) {
        return getSP(context).getBoolean(key, false);
    }
    public static Boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSP(context).getBoolean(key, defaultValue);
    }

    public static int getInt(Context context, String key) {
        return getSP(context).getInt(key, 0);
    }
    public static int getInt(Context context, String key, int defaultValue) {
        return getSP(context).getInt(key, defaultValue);
    }
    public static long getLong(Context context, String key) {
        return getSP(context).getLong(key, 0);
    }
    public static long getLong(Context context, String key, long defaultValue) {
        return getSP(context).getLong(key, defaultValue);
    }

    public static void putString(Context context, String key, String value) {
        getSP(context).edit().putString(key, value).commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSP(context).edit().putBoolean(key, value).commit();
    }

    public static void putInt(Context context, String key, int value) {
        getSP(context).edit().putInt(key, value).commit();
    }

    public static void putLong(Context context, String key, long value) {
        getSP(context).edit().putLong(key, value).commit();
    }
}
