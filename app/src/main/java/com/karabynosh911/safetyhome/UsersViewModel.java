package com.karabynosh911.safetyhome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsersViewModel extends ViewModel {

    private MutableLiveData<List<User>> userData = new MutableLiveData<>();

    public LiveData<List<User>> getUserData() {
        return userData;
    }

    public UsersViewModel(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("Father", true));
        userList.add(new User("Mother", false));
        userList.add(new User("Son", false));
        userList.add(new User("Daughter", false));
        userData.setValue(userList);
    }
}
