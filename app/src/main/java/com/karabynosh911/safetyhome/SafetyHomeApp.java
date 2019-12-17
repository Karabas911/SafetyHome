package com.karabynosh911.safetyhome;

import android.app.Application;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;

public class SafetyHomeApp extends Application {

    private String TAG = "SafetyHomeApp";

    final String serverUri = "tcp://farmer.cloudmqtt.com:10894";

    private MessageManager messageManager;

    public static SafetyHomeApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client = new MqttAndroidClient(
                getApplicationContext(),
                serverUri,
                clientId);
        messageManager = new MessageManager(client);
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
