package com.tale.prettybundle.sample.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tale.prettybundle.Extra;
import com.tale.prettybundle.PrettyBundle;

/**
 * Created by giang on 2/27/15.
 */
public class TestPrimaryTypeService extends Service {

    @Extra int requestId;

    @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        PrettyBundle.inject(this, intent);
        return super.onStartCommand(intent, flags, startId);
    }
}
