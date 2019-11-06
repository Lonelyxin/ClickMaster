package com.diandian.clickmaster.utility;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by lbeliuxin on 19-11-6
 */
public class PermissionUtil {

    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    public static boolean hasOverlaysPermission(Context context) {
        return Settings.canDrawOverlays(context);
    }

    public static void requestForOverlaysPermission(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
    }

    public static boolean isAccessibilitySettingsOn(Context context, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        if (accessibilityEnabled == 1) {
            String enabledServices = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (enabledServices != null) {
                TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
                mStringColonSplitter.setString(enabledServices);
                String service = context.getPackageName() + "/" + clazz.getCanonicalName();
                while (mStringColonSplitter.hasNext()) {
                    if (mStringColonSplitter.next().equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void requestForAccessibilityPermission(Activity activity) {
        activity.startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
    }
}
