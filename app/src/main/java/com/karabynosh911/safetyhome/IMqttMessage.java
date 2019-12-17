package com.karabynosh911.safetyhome;

public class IMqttMessage {

    public final String topic;

    public String message;

    public IMqttMessage(String topic, String message){
        this.topic = topic;
        this.message = message;
    }
}
