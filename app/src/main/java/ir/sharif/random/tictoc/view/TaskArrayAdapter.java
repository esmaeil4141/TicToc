package ir.sharif.random.tictoc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class TaskArrayAdapter extends ArrayAdapter<Task> {

    Context context = null;
    int row_resource_xml = 0;
    List<Task> tasks = null;

    public void updateTaskList(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public TaskArrayAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.row_resource_xml = resource;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return this.tasks.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(row_resource_xml, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView date = (TextView) convertView.findViewById(R.id.Date);

        title.setText(tasks.get(position).getTitle());
        date.setText(tasks.get(position).getDate());
        return convertView;

    }
}
