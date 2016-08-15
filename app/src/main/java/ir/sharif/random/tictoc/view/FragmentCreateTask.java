package ir.sharif.random.tictoc.view;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.Entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class FragmentCreateTask extends Fragment implements DatePickerDialog.OnDateSetListener {
    private Task task = new Task("temp");
    private CallBack callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_task_create, container, false);
        Button button = (Button) root.findViewById(R.id.buttonTimePick);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment newFragment = new DatePickerFragment();
//                newFragment.show(getFragmentManager(), "datePicker");
                PersianCalendar persianCalendar = new PersianCalendar();

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        FragmentCreateTask.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        button = (Button) root.findViewById(R.id.addTask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) root.findViewById(R.id.title);
                task.setTitle(title.getText().toString());
                callback.onCreateTaskClicked(task);
            }
        });

        return root;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        String string = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        task.setDate(string);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (CallBack) context;
    }

    public interface CallBack {
        public void onCreateTaskClicked(Task task);
    }
}
