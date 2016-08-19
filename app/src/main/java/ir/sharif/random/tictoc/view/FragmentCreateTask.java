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
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class FragmentCreateTask extends Fragment implements DatePickerDialog.OnDateSetListener {
    private String title;
    private String date;
    private CallBack callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task_create, container, false);
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
                EditText titleBox = (EditText) FragmentCreateTask.this.getActivity().findViewById(R.id.title);
                title = titleBox.getText().toString();
                if (title != null && !title.equals("") && date != null) {
                    callback.onCreateTaskClicked(new Task(title, date));
                }
                else{
                    Toast.makeText(FragmentCreateTask.this.getContext()
                            ,"Title or Date or both are not set",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        date = (new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));

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
