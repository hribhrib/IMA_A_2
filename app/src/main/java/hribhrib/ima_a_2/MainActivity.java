package hribhrib.ima_a_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.inputText);

        input.setOnEditorActionListener(enterListener);

        list = (LinearLayout) findViewById(R.id.list);

    }

    TextView.OnEditorActionListener enterListener = new TextView.OnEditorActionListener() {

        public boolean onEditorAction(TextView inputView, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    ) {
                System.out.println("test");
                TextView tv = new TextView(list.getContext());
                tv.setText(inputView.getText());


                list.addView(tv);
            }
            return true;
        }
    };
}
