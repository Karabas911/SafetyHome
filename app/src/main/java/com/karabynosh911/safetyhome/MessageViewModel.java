package com.karabynosh911.safetyhome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageViewModel extends ViewModel {

    public LiveData<List<Message>> getMessagesData() {
        return messagesData;
    }

    private MutableLiveData<List<Message>> messagesData = new MutableLiveData<>();

    public MessageViewModel(){
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("10:05:36 17.10.19", "Intruding to the house was detected"));
        messageList.add(new Message("12:10:36 17.10.19", "System activated"));
        messageList.add(new Message("13:15:36 17.10.19", "System deactivated"));
        messageList.add(new Message("10:05:36 17.10.19", "Intruding to the house was detected"));
        messageList.add(new Message("10:05:36 17.10.19", "Intruding to the house was detected"));
        messagesData.setValue(messageList);
    }
}
