package com.diandian.clickmaster.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlertService extends Service {

    public AlertService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
