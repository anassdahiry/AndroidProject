package com.ensas.project.nevergetlost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton logoApp;
    Button startButton;
    Animation frombottom,fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoApp = (ImageButton) findViewById(R.id.logo);
        startButton = (Button) findViewById(R.id.btnStart);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        logoApp.setAnimation(fromtop);
        startButton.setAnimation(frombottom);
        startButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        finish();
        Intent intent = new Intent(SplashActivity.this,MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //int pid = android.os.Process.myPid();
        //android.os.Process.killProcess(pid);
    }
}
