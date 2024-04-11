package com.example.indoor;

import androidx.appcompat.app.AppCompatActivity;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView support = findViewById(R.id.textView);
        TextView bluteooth = findViewById(R.id.textView2);
        TextView devices = findViewById(R.id.textView3);
        String pre = "";
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        support.setText("Supported");
        if (bluetoothAdapter == null) {
            support.setText("Not Supported");
        }

        if (bluetoothAdapter.isEnabled()) {
            bluteooth.setText("Bluetooth Turned On");

        }
        else{
            bluteooth.setText("Turn On Bluetooth");
        }


        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

         final BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    devices.setText(pre+deviceName.toString());
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                }
            }
        };

        registerReceiver(receiver, filter);
    }
}