package com.karabynosh911.safetyhome;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityVM extends ViewModel {

    private static final String TAG = "MainActivityVM";

    private MessageManager messageManager = SafetyHomeApp.instance.getMessageManager();

    private MediatorLiveData<String> alertLiveData  = new MediatorLiveData<>();

    public LiveData<String> getAlertLiveData() {
        return alertLiveData;
    }

    public MainActivityVM() {
        alertLiveData.addSource(messageManager.getMessageLiveData(), mqttMessage -> {
            if (mqttMessage.topic.equals(Topic.ALERT)) {
                Log.d(TAG, "on alert received");
                alertLiveData.setValue(mqttMessage.message);
            }
        });
    }
}
