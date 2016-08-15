package ir.sharif.random.tictoc.model.localDataBase;

import java.util.ArrayList;

import ir.sharif.random.tictoc.model.Entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public interface DataBaseService {
    public Task create(Task task);
    public ArrayList<Task> findAllTasks();
}
