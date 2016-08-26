package ir.sharif.random.tictoc.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import ir.sharif.random.tictoc.MainMVPInterface;
import ir.sharif.random.tictoc.model.entity.Task;
import ir.sharif.random.tictoc.model.localDataBase.DataBaseService;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public class MainModel implements MainMVPInterface.ProvidedModelOps {

    private MainMVPInterface.RequiredPresenterOps mPresenter;
    private DataBaseService dataBaseService;

    public MainModel(MainMVPInterface.RequiredPresenterOps mPresenter, DataBaseService service) {
        this.mPresenter = mPresenter;
        this.dataBaseService = service;
    }

    @Override
    public void onDestroy() {
        mPresenter=null; // let presenter be available for GC
    }

    @Override
    public void createTask(Task task) {
        dataBaseService.create(task);
    }

    @Override
    public ArrayList<Task> getAllTasks(){
        return dataBaseService.findAllTasks();
    }

    @Override
    public void removeTask(Task task) {
        long id = task.getId();
        if (id != 0)
            dataBaseService.removeTaskById(id);
    }
}
