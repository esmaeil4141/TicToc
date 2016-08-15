package ir.sharif.random.tictoc.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.Entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class FragmentTaskList extends Fragment {
//    ArrayList<Task> tasks;
    TaskArrayAdapter adapter;
    public void updateTaskList(ArrayList<Task> tasks) {
        adapter.updateTaskList(tasks);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task_list_main,container,false);
        ListView list = (ListView) root.findViewById(R.id.myList);
        list.setAdapter(adapter);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskArrayAdapter(getContext(),R.layout.list_item,new ArrayList<Task>(0));
    }
}
