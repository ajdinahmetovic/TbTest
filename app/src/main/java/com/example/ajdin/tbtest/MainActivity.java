package com.example.ajdin.tbtest;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText editText;
    List<ScanResult> scanResults;
    String macAddr;
    Button button;
    WifiManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         manager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        manager.getScanResults();
        scanResults = manager.getScanResults();


        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                macAddr = editText.getText().toString();
                System.out.println("clicked");

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (true){
                            manager.startScan();
                            scanResults = manager.getScanResults();


                            for(int i = 0;i<scanResults.size();i++){
                                if(scanResults.get(i).BSSID.equals(macAddr)){
                                    //text.setText("TITOOOOOO JE RUSKI SPIJUUUUUUUN");
                                    System.out.println("found");
                                } else {
                                    //text.setText("No signal");
                                    //System.out.println("Noo");
                                }
                                //System.out.println(scanResults.get(i).SSID + ":::::" + scanResults.get(i).BSSID);
                            }
                        }


                    }
                }).start();
            }
        });



        text = findViewById(R.id.found);
        editText = findViewById(R.id.input);




        WifiManager manager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        manager.getScanResults();
        scanResults = manager.getScanResults();

        for(int i = 0;i<scanResults.size();i++){
            System.out.println(scanResults.get(i).SSID + ":::::" + scanResults.get(i).BSSID);
        }



    }
}
