package ir.sharif.random.tictoc.view;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class FragmentCreateTask extends Fragment implements DatePickerDialog.OnDateSetListener, DescriptionFragment.NoticeDialogListener {
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String description;
    private int repeat = 0;
    private CallBack callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task_create, container, false);

        Button buttonDatePick = (Button) root.findViewById(R.id.buttonDatePick);
        buttonDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        Button startTimeButton = (Button) root.findViewById(R.id.buttonStartTimePick);
        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                startTime = hourOfDay + ":" + minute + ":" + "00";
                            }
                        }, 0, 0, true);
                timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
            }
        });
        final Button endTimeButton = (Button) root.findViewById(R.id.buttonEndTimePick);
        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                endTime = hourOfDay + ":" + minute + ":" + "00";
                            }
                        }, 0, 0, true);
                timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        Spinner dropdown = (Spinner) root.findViewById(R.id.periodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.repeatModes,
                android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        repeat = 0;
                        break;
                    case 1:
                        repeat = 7;
                        break;
                    case 2:
                        repeat = 30;
                        break;
                    case 3:
                        repeat = 365;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button addTask = (Button) root.findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleBox = (EditText) FragmentCreateTask.this.getActivity().findViewById(R.id.title);
                title = titleBox.getText().toString();
                if (!title.equals("") && date != null) {
                    callback.onCreateTaskClicked(new Task(title, date)
                            .setStartTime(startTime).setEndTime(endTime).setRepeatPeriod(repeat)
                            .setDescription(description));
                } else {
                    Toast.makeText(FragmentCreateTask.this.getContext()
                            , "Title or Date or both are not set", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button buttonDescription = (Button) root.findViewById(R.id.buttonDescription);
        buttonDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                descriptionFragment.show(getFragmentManager(), "DescriptionDialog");
                descriptionFragment.injectListener(FragmentCreateTask.this);
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

    @Override
    public void onDialogPositiveClick(String description) {
        this.description = description;
    }

    public interface CallBack {
        void onCreateTaskClicked(Task task);
    }
}

