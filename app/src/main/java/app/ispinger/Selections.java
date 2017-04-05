package app.ispinger;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class Selections extends AppCompatActivity {
    int flag;
    private static final String TAG = "Testing: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);
        Log.d(TAG, "TEST");
    }

    public void onStartBtn(View v){
        flag = 0;
        TimePicker(flag);
        Log.d(TAG, "TEST");
    }

    public void onStopBtn(View v){
        flag = 1;
        TimePicker(flag);
    }

    public void turnOn(){

    }

    public void TimePicker(int flag){
        DialogFragment newFragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putInt("flag", flag);
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(),"TimePicker");
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(Selections.this, MainActivity.class);
        startActivity(intent);
    }
}
