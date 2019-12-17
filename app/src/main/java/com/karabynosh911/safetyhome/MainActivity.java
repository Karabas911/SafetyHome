package com.karabynosh911.safetyhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainActivityVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityVM.class);
        viewModel.getAlertLiveData().observe(this, this::showNotification);
    }

    private void showNotification(String message) {
        Log.d(TAG, "showNotification:"+ message);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("ALLERT")
                .setMessage(message)
                .setPositiveButton("OK", (d,v)->{
                    d.dismiss();
                })
                .create();
        dialog.show();
    }
}
