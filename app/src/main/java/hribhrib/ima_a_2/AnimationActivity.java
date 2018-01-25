package hribhrib.ima_a_2;

import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimationActivity extends AppCompatActivity {

    ArrayList<TextView> wordList = new ArrayList<TextView>();
    ConstraintLayout ll;

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

        ll = findViewById(R.id.animation);


        splitString(myString);

        for (TextView t : wordList) {
            ll.addView(t);
        }

        ValueAnimator animation = ValueAnimator.ofFloat(0f, 700f);
        animation.setDuration(2000);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                float animatedValue = (float) updatedAnimation.getAnimatedValue();
                float tmp = 0;

                for (TextView t : wordList) {
                    t.setTranslationY(animatedValue - tmp);
                    tmp += t.getHeight() + 10;
                }
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
}