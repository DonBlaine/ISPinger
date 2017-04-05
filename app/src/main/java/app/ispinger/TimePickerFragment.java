package app.ispinger;



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
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){

        int flag = getArguments().getInt("flag");

        if (flag == 0){
            TextView startBtn = (TextView) getActivity().findViewById(R.id.startBtn);
            startBtn.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        } else if (flag == 1){
            TextView stopBtn = (TextView) getActivity().findViewById(R.id.stopBtn);
            stopBtn.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }
    }
}