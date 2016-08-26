package ir.sharif.random.tictoc.presenter;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import ir.sharif.random.tictoc.MainMVPInterface;
import ir.sharif.random.tictoc.model.MainModel;
import ir.sharif.random.tictoc.model.entity.Task;
import ir.sharif.random.tictoc.model.localDataBase.DataBaseService;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public class MainPresenter implements MainMVPInterface.RequiredPresenterOps
        , MainMVPInterface.ProvidedPresenterOps {
    // Layer View reference
    private WeakReference<MainMVPInterface.RequiredViewOps> mView;
    // Layer Model reference
    private MainMVPInterface.ProvidedModelOps mModel;

    // Configuration change state
    private boolean mIsChangingConfig;

    private Task editingTask;

    public void setEditingTask(Task editingTask) {
        this.editingTask = editingTask;
    }

    public Task getEditingTask() {
        return editingTask;
    }

    //constructor method
    public MainPresenter(MainMVPInterface.RequiredViewOps mView, DataBaseService service) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new MainModel(this,service);
    }

    /**
     * Sent from Activity after a configuration changes
     * @param view  View reference
     */
    @Override
    public void onConfigurationChanged(MainMVPInterface.RequiredViewOps view) {
        this.mView = new WeakReference<>(view);
    }

    /**
     * Receives {@link ir.sharif.random.tictoc.view.MainView#onDestroy()} event
     * @param isChangingConfig  Config change state
     */
    @Override
    public void onDestroy(boolean isChangingConfig) {
        mView = null;
        mIsChangingConfig = isChangingConfig;
        if ( !isChangingConfig ) {
            mModel.onDestroy();
        }
    }

    @Override
    public void newTaskCreationClicked() {
        mView.get().showTaskCreationView();
    }

    @Override
    public void createNewTask(Task task) {
        mModel.createTask(task);
        mView.get().showTaskListView();
    }

    @Override
    public void onTaskListViewCreated() {
        ArrayList<Task> tasks = mModel.getAllTasks();
        mView.get().showAllTasks(tasks);
    }

    @Override
    public void onTaskListItemClick(Task task) {
        setEditingTask(task);
        mView.get().showTaskEditView(task);
    }

    @Override
    public void onEditTaskClicked(Task task) {
        mModel.removeTask(getEditingTask());
        mModel.createTask(task);
        mView.get().showTaskListView();

    }

    @Override
    public void onCreate() {
        mView.get().showTaskListView();
    }


}
