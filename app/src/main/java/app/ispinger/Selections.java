package app.ispinger;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class Selections extends AppCompatActivity {
    int flag;
    private static final String TAG = "Testing: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);
        final SharedPreferences sharedPref =
                getSharedPreferences("ispinger_sharedPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        SwitchCompat switchBtn = (SwitchCompat) findViewById(R.id.simpleSwitch);
        String startTime = sharedPref.getString("startTime", null);
        String stopTime = sharedPref.getString("stopTime", null);
        Boolean wifi = sharedPref.getBoolean("wifi", false);

        if(wifi){
            switchBtn.setChecked(true);
        }
        if(startTime != null){
            startBtn.setText(startTime);
        }
        if(stopTime != null){
            stopBtn.setText(stopTime);
        }

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                editor.putBoolean("wifi",isChecked);
                editor.apply();
            }
        });
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
