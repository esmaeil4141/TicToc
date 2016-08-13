package ir.sharif.random.tictoc.presenter;

import java.lang.ref.WeakReference;
import ir.sharif.random.tictoc.MainMVPInterface;
import ir.sharif.random.tictoc.model.MainModel;

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

    //constructor method
    public MainPresenter(MainMVPInterface.RequiredViewOps mView) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new MainModel(this);
    }

    /**
     * Sent from Activity after a configuration changes
     * @param view  View reference
     */
    @Override
    public void onConfigurationChanged(MainMVPInterface.RequiredViewOps view) {
        mView = new WeakReference<>(view);
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

}
