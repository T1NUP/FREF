package com.example.fref;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView imageView= findViewById(R.id.iv);
        TextView t= findViewById(R.id.nm);
        Animation animation,animation2;
        animation= AnimationUtils.loadAnimation(Splash.this,R.anim.fadein);
        //animation2= AnimationUtils.loadAnimation(Splash.this,R.anim.bounce);
        imageView.startAnimation(animation);
        t.startAnimation(animation);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ite= new Intent(Splash.this,MainActivity2.class);
                startActivity(ite);

                finish();//now this activity will not come again
            }
        },3000);
    }
}
