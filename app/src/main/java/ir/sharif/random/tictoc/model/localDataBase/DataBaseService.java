package ir.sharif.random.tictoc.model.localDataBase;

import java.util.ArrayList;

import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/14/2016.
 */
public interface DataBaseService {
    Task create(Task task);

    ArrayList<Task> findAllTasks();

    boolean removeTaskById(long id);
}
