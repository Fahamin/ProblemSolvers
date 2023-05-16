package com.tbl.problemsolvers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionManager;

public class TransitionsActivity extends AppCompatActivity {
    ViewGroup rootContainer;
    Scene scene1;
    Scene scene2;
    Transition transitionMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions);

        rootContainer =
                (ViewGroup) findViewById(R.id.rootContainer);

        transitionMgr = TransitionInflater.from(this)
                .inflateTransition(R.drawable.transition);

        scene1 = Scene.getSceneForLayout(rootContainer,
                R.layout.scene1_layout, this);

        scene2 = Scene.getSceneForLayout(rootContainer,
                R.layout.scene2_layout, this);

        scene2.enter();
    }

    public void goToScene2 (View view)
    {
     //   view.startAnimation(AnimationUtils.loadAnimation(TransitionsActivity.this, R.anim.button_clik));

        Animator scale = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat(View.ROTATION_X, 1, 1.5f, 1),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
        );
        scale.setDuration(1000);
        scale.start();

       // TransitionManager.go(scene2, transitionMgr);
    }

    public void goToScene1 (View view)
    {
        view.startAnimation(AnimationUtils.loadAnimation(TransitionsActivity.this, R.anim.button_clik));
        TransitionManager.go(scene1, transitionMgr);
    }

}