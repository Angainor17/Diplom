package com.example.angai.diplom.location;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class SendLocationService extends IntentService {

    public SendLocationService() {
        super("SendLocationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
