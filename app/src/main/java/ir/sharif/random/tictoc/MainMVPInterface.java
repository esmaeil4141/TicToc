package ir.sharif.random.tictoc;

import android.content.Context;

/**
 * Created by Esmaeil Gholami on 2016/08/12.
 */
public interface MainMVPInterface {

    public interface ProvidedPresenterOps {
        void onConfigurationChanged(RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void createNewTask();
    }

    public interface ProvidedModelOps {
        void onDestroy();
    }

    public interface RequiredViewOps {
        void goToTaskCreationView();
    }

    public interface RequiredPresenterOps {

    }
}
