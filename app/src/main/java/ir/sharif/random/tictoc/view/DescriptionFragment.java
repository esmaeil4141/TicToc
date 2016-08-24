package ir.sharif.random.tictoc.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import ir.sharif.random.tictoc.R;

public class DescriptionFragment extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(String description);
    }

    NoticeDialogListener mListener;

    void attachListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.create_task_description, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.task_create_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = (EditText) view.findViewById(R.id.descriptionText);
                        mListener.onDialogPositiveClick(editText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.task_create_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DescriptionFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
