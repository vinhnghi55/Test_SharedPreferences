package com.example.test_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String SHARED_PREFERENCES_NAME = "vinh_nghi";
    private final String MY_NAME = "my_name";
    private final String AGE = "age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "weight";

    private Button btnSaveData;
    private Button btnReadData;
    private Button btnRemoveKey;
    private Button btnRemoveAll;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
        btnRemoveKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeKey(MY_NAME);
                readData();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeALL();
                readData();
            }
        });
    }

    private void setWidget() {
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        btnRemoveKey = (Button) findViewById(R.id.btn_remove_by_key);
        btnRemoveAll = (Button) findViewById(R.id.btn_remove_all);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MY_NAME, "Le Huyen My");
        editor.putInt(AGE, 20);
        editor.putBoolean(IS_SINGLE, true);
        editor.putLong(WEIGHT, 53);

        //K trả về
        editor.apply();

        //Trả về true hoặc false
//                editor.commit();
        Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
    }

    public void readData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(MY_NAME, "");
        int age = sharedPreferences.getInt(AGE, 0);
        boolean isSingle = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT, 0);
        String address = sharedPreferences.getString("ADDRESS", "Ho Chi Minh");

        Log.d(TAG, "vinhnghi: " + "\n"
                + "Name: " + name + "\n"
                + "Age: " + age + "\n"
                + "Single: " + isSingle + "\n"
                + "Weight: " + weight + "\n"
                + "Address: " + address);
    }

    public void removeKey(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(key);
        editor.apply();
    }

    public void removeALL(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }
}
