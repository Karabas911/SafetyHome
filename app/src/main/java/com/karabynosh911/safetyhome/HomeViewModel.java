package com.karabynosh911.safetyhome;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "MainActivityVM";

    private MessageManager messageManager = SafetyHomeApp.instance.getMessageManager();

    private MediatorLiveData<Integer> statusLiveData = new MediatorLiveData<>();

    public LiveData<Integer> getStatusData() {
        return statusLiveData;
    }

    public HomeViewModel() {
        statusLiveData.addSource(messageManager.getMessageLiveData(), mqttMessage -> {
            if (mqttMessage.topic.equals(Topic.STATUS)) {
                Log.d(TAG, "on alert received");
                statusLiveData.setValue(Integer.valueOf(mqttMessage.message));
            }
        });
        getStatus();
    }

    public void activateSecurity() {
        messageManager.publishMessage(Topic.CHANGE_STATUS, "1");
    }

    public void deactivateSecurity() {
        messageManager.publishMessage(Topic.CHANGE_STATUS, "2");
    }

    public void getStatus() {
        messageManager.publishMessage(Topic.GET_STATUS,"GetHouseStatus");
    }
}
