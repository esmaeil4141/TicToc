package ir.sharif.random.tictoc.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public class FragmentTaskList extends ListFragment {
    public CallBack callBack;
    //    ArrayList<Task> tasks;
    private TaskArrayAdapter adapter;
    public FragmentTaskList(){}

    public void updateTaskList(ArrayList<Task> tasks) {
        adapter.updateTaskList(tasks);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task_list_main, container, false);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Task> tasks  = new ArrayList<>();
        tasks.add(new Task("shayan","alaki"));tasks.add(new Task("ali","alaki"));
        adapter = new TaskArrayAdapter(getActivity(), R.layout.list_item, tasks);
        setListAdapter(adapter);
        callBack.onTaskListReady();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (CallBack)context;
    }

    public interface CallBack {
        void onTaskListReady();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
