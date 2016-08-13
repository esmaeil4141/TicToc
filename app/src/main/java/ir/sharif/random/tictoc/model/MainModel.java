package ir.sharif.random.tictoc.model;

import ir.sharif.random.tictoc.MainMVPInterface;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public class MainModel implements MainMVPInterface.ProvidedModelOps {

    private MainMVPInterface.RequiredPresenterOps mPresenter;

    public MainModel(MainMVPInterface.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onDestroy() {

    }
}
