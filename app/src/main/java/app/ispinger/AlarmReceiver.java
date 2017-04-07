package app.ispinger;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    private final String Period = "period";
    private final String Wifi = "wifi";

    public AlarmReceiver(Context context,Bundle bundle){
        AlarmManager alarmMgr =
                (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        String curTime = String.valueOf(time.getTimeInMillis());
        Activity activity = (Activity) context;
        SharedPreferences sharedPref = activity.getSharedPreferences("ispinger_sharedPreferences",
                Context.MODE_PRIVATE);

        long startmillis =
                getTimeMillis(sharedPref.getString("startHour", curTime),
                        sharedPref.getString("startMin", curTime));
        long stopMillis =
                getTimeMillis(sharedPref.getString("stopHour", curTime),
                        sharedPref.getString("stopMin", curTime));
        if (stopMillis<startmillis){
            stopMillis= stopMillis + 86400000; //24 hours in milliseconds
        }

        long period = stopMillis-startmillis;
        bundle.putLong(Period,period);
        intent.putExtra(Period, bundle);

        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                startmillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean wifi = intent.getBundleExtra(Wifi).getBoolean(Wifi);
        long timePeriod = intent.getBundleExtra(Period).getLong(Period);
        long startTime = System.currentTimeMillis();

        //check if wifi before running task

        while( startTime + timePeriod >  System.currentTimeMillis()){
            Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
        }
    }

    public long getTimeMillis(String Hour, String Min){
        int hourOfDay = Integer.valueOf(Hour);
        int minute = Integer.valueOf(Min);

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return (calendar.getTimeInMillis());
    }
}
