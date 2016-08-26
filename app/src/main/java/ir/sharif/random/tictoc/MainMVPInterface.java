package ir.sharif.random.tictoc;

import java.util.ArrayList;

import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public interface MainMVPInterface {

    public interface ProvidedPresenterOps {
        void onCreate();

        void onConfigurationChanged(RequiredViewOps view);

        void onDestroy(boolean isChangingConfig);

        void newTaskCreationClicked();

        void createNewTask(Task task);

        void onTaskListViewCreated();

        void onTaskListItemClick(Task task);

        void onEditTaskClicked(Task task);

    }

    public interface ProvidedModelOps {
        void onDestroy();

        void createTask(Task task);

        ArrayList<Task> getAllTasks();

        void removeTask(Task task);
    }

    public interface RequiredViewOps {
        void showTaskCreationView();

        void showTaskListView();

        void showAllTasks(ArrayList<Task> tasks);

        void showTaskEditView(Task task);
    }

    public interface RequiredPresenterOps {

    }
}
