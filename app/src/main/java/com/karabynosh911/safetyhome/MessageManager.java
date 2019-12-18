package com.karabynosh911.safetyhome;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MessageManager {

    private static final String TAG = "MessageManager";

    private final MqttAndroidClient client;

    private MutableLiveData<IMqttMessage> messageLiveData = new MutableLiveData<>();

    public MessageManager(MqttAndroidClient client) {
        this.client = client;
        connectToBroker();
    }

    private void connectToBroker() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setUserName("vmlcuwww");
        options.setPassword("rTpTTX_FPnJV".toCharArray());
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Client connection success");
                    subscribeToTopic(Topic.ALERT);
                    subscribeToTopic(Topic.STATUS);
                    initCallback();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "Client connection failure: " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.d(TAG, "Exception: " + e.getMessage());
        }
    }

    private void initCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.d(TAG, "messageArrived: topic = "+ topic);
                IMqttMessage msg = new IMqttMessage(topic, new String(message.getPayload()));
                messageLiveData.setValue(msg);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void subscribeToTopic(String topic) {
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "subscribeToTopic " + topic + ": onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    Log.d(TAG, "subscribeToTopic " + topic + ": onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.d(TAG, "subscribeToTopic " + topic + ": exception");
        }
    }

    public  void publishMessage(String topic, String msg){
        if(client == null){
            return;
        }
        try {
            byte[] encodedPayload = msg.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    public LiveData<IMqttMessage> getMessageLiveData() {
        return messageLiveData;
    }
}
