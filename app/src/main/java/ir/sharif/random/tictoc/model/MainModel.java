package ir.sharif.random.tictoc.model;

import java.lang.ref.WeakReference;

import ir.sharif.random.tictoc.MainMVPInterface;
import ir.sharif.random.tictoc.model.localDataBase.DataBaseService;
import ir.sharif.random.tictoc.model.localDataBase.DataSource;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public class MainModel implements MainMVPInterface.ProvidedModelOps {

    private MainMVPInterface.RequiredPresenterOps mPresenter;
    private WeakReference<DataBaseService> dataBaseService;

    public MainModel(MainMVPInterface.RequiredPresenterOps mPresenter, DataBaseService service) {
        this.mPresenter = mPresenter;
        this.dataBaseService=new WeakReference<>(service);
    }

    @Override
    public void onDestroy() {
        mPresenter=null; // let presenter be available for GC
    }

}
