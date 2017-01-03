package com.example.thugm.gosafe_v1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.*;

/**
 * Created by Felix on 1/3/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
        finish();
    }
}