package hribhrib.ima_a_2;

import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimationActivity extends AppCompatActivity {

    ArrayList<TextView> wordList = new ArrayList<TextView>();
    TextView tv;
    ConstraintLayout ll;
    int layoutHeight= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        String myString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                myString = null;
            } else {
                myString = extras.getString("String");
            }
        } else {
            myString = (String) savedInstanceState.getSerializable("String");
        }


//       tv = new TextView(this);
        //      tv.setTextSize(20);
        //    tv.setText(myString);

        ll = findViewById(R.id.animation);


        splitString(myString);

        for (TextView t : wordList) {
            ll.addView(t);
        }

        ll.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalListenerClass());

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        System.out.println(metrics.heightPixels);
        System.out.println("layoutHeight: "+layoutHeight);

        ValueAnimator animation = ValueAnimator.ofFloat(0f, 500f);
        animation.setDuration(1000);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float) updatedAnimation.getAnimatedValue();

                float tmp = 0;

                for (TextView t : wordList) {
                    t.setTranslationY(animatedValue - tmp);
                    tmp += t.getHeight() + 10;

                }

                System.out.println(animatedValue);
            }
        });

    }

    private void splitString(String str) {
        String[] ar = str.split("\\s+");
        for (String s : ar) {
            TextView tmp = new TextView(ll.getContext());
            tmp.setTextSize(20);
            tmp.setText(s);
            wordList.add(tmp);
        }
    }


    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            View v = (View) findViewById(R.id.animation);
            layoutHeight = (v.getHeight());
        }
    }
}