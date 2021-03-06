package app.ispinger;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref =
                getSharedPreferences("ispinger_sharedPreferences", Context.MODE_PRIVATE);

        Boolean alarmOn = sharedPref.getBoolean("alarm", false);
        Button alarm = (Button) findViewById(R.id.alarmBtn);

        if(alarmOn){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                alarm.setBackground( getResources().getDrawable(R.drawable.greenroundbutton));
            }else{
                alarm.setBackgroundDrawable( getResources().getDrawable(R.drawable.greenroundbutton) );
            }
        }
    }

    public void startAlarm(){
        SharedPreferences sharedPref =
                getSharedPreferences("ispinger_sharedPreferences", Context.MODE_PRIVATE);
        Boolean wifi = sharedPref.getBoolean("wifi", false);
        Bundle bundle = new Bundle();
        bundle.putBoolean("wifi",wifi);
        AlarmReceiver alarm = new AlarmReceiver(this, bundle);

    }

    public void stopAlarm(){
        //                Intent intent = new Intent(this, AlarmReceive.class);
//                PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                alarmManager.cancel(sender);
    }

    public void turnOn(View v){
        SharedPreferences sharedPref =
                getSharedPreferences("ispinger_sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Boolean alarmOn = sharedPref.getBoolean("alarm", false);
        Boolean wifi = sharedPref.getBoolean("wifi", false);
        String startTime = sharedPref.getString("startTime", null);
        String stopTime = sharedPref.getString("stopTime", null);
        Button alarm = (Button) findViewById(R.id.alarmBtn);

        if (startTime != null & stopTime != null){
            if (alarmOn){
                editor.putBoolean("alarm",false);
                editor.apply();
                alarm.setText(R.string.Off);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    alarm.setBackground( getResources().getDrawable(R.drawable.redroundbutton));
                }else{
                    alarm.setBackgroundDrawable( getResources().getDrawable(R.drawable.redroundbutton) );
                }

                stopAlarm();
            }
            else{
                editor.putBoolean("alarm",true);
                editor.apply();
                alarm.setText(R.string.On);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    alarm.setBackground( getResources().getDrawable(R.drawable.greenroundbutton));
                }else{
                    alarm.setBackgroundDrawable( getResources().getDrawable(R.drawable.greenroundbutton) );
                }

                startAlarm();
            }
        } else{
            Toast.makeText(MainActivity.this, "Error: Please enter your settings to turn on feature.",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void goSettings(View view)
    {
        Intent intent = new Intent(MainActivity.this, Selections.class);
        startActivity(intent);
    }

    public void goAbout(View view)
    {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
}
