package app.ispinger;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.app.Dialog;
import java.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        SharedPreferences sharedPref =
                getActivity().getSharedPreferences("ispinger_sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int flag = getArguments().getInt("flag");
        String time= String.valueOf(hourOfDay) + ":" + String.valueOf(minute);

        if (flag == 0){
            TextView startBtn = (TextView) getActivity().findViewById(R.id.startBtn);
            startBtn.setText(time);
            editor.putString("startTime",time);
            editor.apply();

        } else if (flag == 1){
            TextView stopBtn = (TextView) getActivity().findViewById(R.id.stopBtn);
            stopBtn.setText(time);
            editor.putString("stopTime",time);
            editor.apply();
        }
    }
}