package ir.sharif.random.tictoc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.Entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class TaskArrayAdapter extends ArrayAdapter<Task> {

    Context context;
    int row_resource_xml;
    ArrayList<Task> tasks;

    public void updateTaskList(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public TaskArrayAdapter(Context context, int resource , ArrayList<Task> tasks) {
        super(context, resource);
        this.context=context;
        this.row_resource_xml=resource;
        this.tasks=tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View row=convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(row_resource_xml,parent,false );

        TextView title = (TextView) row.findViewById(R.id.title);
        TextView number = (TextView)row.findViewById(R.id.Date);

        title.setText(tasks.get(position).getTitle());
        number.setText(tasks.get(position).getDate());
        return row;

    }
}
