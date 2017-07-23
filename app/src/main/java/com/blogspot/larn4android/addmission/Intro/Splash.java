package com.blogspot.larn4android.addmission.Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blogspot.larn4android.addmission.MainActivity;
import com.blogspot.larn4android.addmission.R;

/**
 * Created by Minto on 9/6/2016.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final ImageView iv = (ImageView)findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);

        an.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}

