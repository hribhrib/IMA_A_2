package hribhrib.ima_a_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    LinearLayout list;

    AlertDialog.Builder myBuilder;
    AlertDialog alertEmpty;

    TextView tv;

    int tvID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBuilder = new AlertDialog.Builder(this);

        myBuilder.setTitle("Input Error");
        myBuilder.setMessage("Empty String is not allowed!");
        myBuilder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        alertEmpty = myBuilder.create();


        input = (EditText) findViewById(R.id.inputText);

        input.setOnEditorActionListener(enterListener);

        list = (LinearLayout) findViewById(R.id.list);


    }

    TextView.OnEditorActionListener enterListener = new TextView.OnEditorActionListener() {

        public boolean onEditorAction(TextView inputView, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    ) {
                if (inputView.getText().toString().trim().equals("")) {
                    alertEmpty.show();
                } else {
                    tv = new TextView(list.getContext());
                    tv.setText(inputView.getText());

                    tv.setHeight(60);
                    tv.setTextSize(20);
                    tv.setId(tvID++);

                    tv.setOnClickListener(touchListener);

                    list.addView(tv);

                    input.setText("");
                }
            }
            return true;
        }
    };



    TextView.OnClickListener touchListener = new TextView.OnClickListener(){
        @Override
        public void onClick(View v) {
            TextView tmp = findViewById(v.getId());
            launchAnimationActivity(tmp.getText().toString());
        }

    };

    private void launchAnimationActivity(String str) {
        Intent intent = new Intent(this, AnimationActivity.class);
        intent.putExtra("String", str);
        startActivity(intent);
    }
}
