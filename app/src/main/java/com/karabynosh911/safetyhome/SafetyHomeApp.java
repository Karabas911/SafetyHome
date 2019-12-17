package com.karabynosh911.safetyhome;

import android.app.Application;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SafetyHomeApp extends Application {

    private String TAG = "SafetyHomeApp";
    final String serverUri = "tcp://farmer.cloudmqtt.com:10894";
    String clientId = "ExampleAndroidClient";
    final String subscriptionTopic = "exampleAndroidTopic";
    final String publishTopic = "exampleAndroidPublishTopic";
    final String publishMessage = "Hello World!";
    MqttAndroidClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        initMQTTClient();
    }

    private void initMQTTClient() {
        String clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(
                        getApplicationContext(),
                        serverUri,
                        clientId);
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
                    client.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {

                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) {
                            String msqString = new String(message.getPayload());
                            Log.d(TAG, "messageArrived: topic = " + topic + " message = " + msqString);
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {

                        }
                    });
                    subscribeToTopic();
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

    private void subscribeToTopic() {
        String topic = "security_alert/intruder";
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "subscribeToTopic: onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    Log.d(TAG, "subscribeToTopic: onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.d(TAG, "subscribeToTopic: exception");
        }
    }
//
//        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), serverUri, clientId);
//        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
//            @Override
//            public void connectComplete(boolean reconnect, String serverURI) {
//
//                if (reconnect) {
//                    addToHistory("Reconnected to : " + serverURI);
//                    // Because Clean Session is true, we need to re-subscribe
//                    subscribeToTopic();
//                } else {
//                    addToHistory("Connected to: " + serverURI);
//                }
//            }
//
//            @Override
//            public void connectionLost(Throwable cause) {
//                addToHistory("The Connection was lost.");
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                addToHistory("Incoming message: " + new String(message.getPayload()));
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//
//            }
//        });
//
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setAutomaticReconnect(true);
//        mqttConnectOptions.setCleanSession(false);
//
//        try {
//            addToHistory("Connecting to " + serverUri);
//            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
//                    disconnectedBufferOptions.setBufferEnabled(true);
//                    disconnectedBufferOptions.setBufferSize(100);
//                    disconnectedBufferOptions.setPersistBuffer(false);
//                    disconnectedBufferOptions.setDeleteOldestMessages(false);
//                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
//                    subscribeToTopic();
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    addToHistory("Failed to connect to: " + serverUri);
//                }
//            });
//
//        } catch (MqttException ex){
//            ex.printStackTrace();
//        }
//
//    }
//
//    private void addToHistory(String mainText){
//        Log.d(TAG, "LOG: " + mainText);
//    }
//
//    public void subscribeToTopic(){
//        try {
//            mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    addToHistory("Subscribed!");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    addToHistory("Failed to subscribe");
//                }
//            });
//
//            mqttAndroidClient.subscribe(subscriptionTopic, 0, new IMqttMessageListener() {
//                @Override
//                public void messageArrived(String topic, MqttMessage message) throws Exception {
//                    // message Arrived!
//                    System.out.println("Message: " + topic + " : " + new String(message.getPayload()));
//                }
//            });
//
//        } catch (MqttException ex){
//            System.err.println("Exception whilst subscribing");
//            ex.printStackTrace();
//        }
//    }
//
//    public void publishMessage(){
//        try {
//            MqttMessage message = new MqttMessage();
//            message.setPayload(publishMessage.getBytes());
//            mqttAndroidClient.publish(publishTopic, message);
//            addToHistory("Message Published");
//            if(!mqttAndroidClient.isConnected()){
//                addToHistory(mqttAndroidClient.getBufferedMessageCount() + " messages in buffer.");
//            }
//        } catch (MqttException e) {
//            System.err.println("Error Publishing: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
