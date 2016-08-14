package ir.sharif.random.tictoc;

import android.content.Context;

import java.util.ArrayList;

import ir.sharif.random.tictoc.model.Task;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public interface MainMVPInterface {

    public interface ProvidedPresenterOps {
        void onConfigurationChanged(RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void newTaskCreationClicked();
        void createNewTask(Task task);
        void onCreate();
    }

    public interface ProvidedModelOps {
        void onDestroy();
        void createTask(Task task);
        ArrayList<Task> getAllTasks();
    }

    public interface RequiredViewOps {
        void goToTaskCreationView();
        void showAllTasks(ArrayList<Task> tasks);
    }

    public interface RequiredPresenterOps {

    }
}
