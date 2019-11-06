package com.diandian.clickmaster.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class ClickerAccessibilityService extends AccessibilityService {

    private String TAG = "ClickerAccessibilityService";

    public ClickerAccessibilityService() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent, event: " + event);
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");

    }

    private void clickScreen() {
        int x = 0;
        int y = 0;
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder.
                addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
                .build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d(TAG,"点击结束" + gestureDescription.getStrokeCount());
//                next(next, nextTime);
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.d(TAG,"点击取消");
            }
        }, null);
    }

    private void slideScreen() {

    }
}
