package ir.sharif.random.tictoc.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ir.sharif.random.tictoc.MainMVPInterface;
import ir.sharif.random.tictoc.R;
import ir.sharif.random.tictoc.StateMaintainer;
import ir.sharif.random.tictoc.model.entity.Task;
import ir.sharif.random.tictoc.model.localDataBase.DataBaseService;
import ir.sharif.random.tictoc.model.localDataBase.DataSource;
import ir.sharif.random.tictoc.presenter.MainPresenter;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainMVPInterface.RequiredViewOps
        , FragmentCreateTask.CallBack , FragmentTaskList.CallBack {

    protected final String TAG = getClass().getSimpleName();
    private final String CREATE_TASK_FRAGMENT_TAG = "CreateTaskFragment";
    private final String TASK_LIST_FRAGMENT_TAG = "TaskListFragment";

    private FragmentTaskList fragmentTaskList;
    private FragmentCreateTask fragmentCreateTask;
    // Responsible to maintain the Objects state
    // during changing configuration
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(this.getFragmentManager(), TAG);
    DataBaseService dataBaseService;
    // Presenter operations
    private MainMVPInterface.ProvidedPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//todo alaki
        setContentView(R.layout.activity_main);
        dataBaseService = new DataSource(this);
        startMVPOps();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.newTaskCreationClicked();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentTaskList =
                (FragmentTaskList) getFragmentManager().findFragmentByTag(TASK_LIST_FRAGMENT_TAG);
        if (fragmentTaskList == null) {
            // Create the fragment
            fragmentTaskList = new FragmentTaskList();
        }
        fragmentCreateTask =
                (FragmentCreateTask) getFragmentManager().findFragmentByTag(CREATE_TASK_FRAGMENT_TAG);
        if (fragmentCreateTask == null) {
            // Create the fragment
            fragmentCreateTask = new FragmentCreateTask();
        }
        mPresenter.onCreate();
    }

    public void startMVPOps() {
        try {
            if (mStateMaintainer.firstTimeIn()) {
                Log.d(TAG, "onCreate() called for the first time");
                initialize(this, dataBaseService);
            } else {
                Log.d(TAG, "onCreate() called more than once");
                reinitialize(this, dataBaseService);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            Log.d(TAG, "onCreate() " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize relevant MVP Objects.
     * Creates a Presenter instance, saves the presenter in {@link StateMaintainer}
     */
    private void initialize(MainMVPInterface.RequiredViewOps view, DataBaseService service)
            throws InstantiationException, IllegalAccessException {
        mPresenter = new MainPresenter(view, service);
        mStateMaintainer.put(MainMVPInterface.ProvidedPresenterOps.class.getSimpleName(), mPresenter);

    }

    /**
     * Recovers Presenter and informs Presenter that occurred a config change.
     * If Presenter has been lost, recreates a instance
     */
    private void reinitialize(MainMVPInterface.RequiredViewOps view, DataBaseService service)
            throws InstantiationException, IllegalAccessException {
        mPresenter = mStateMaintainer.get(MainMVPInterface.ProvidedPresenterOps.class.getSimpleName());

        if (mPresenter == null) {
            Log.w(TAG, "recreating Presenter");
            initialize(view, service);
        } else {
            mPresenter.onConfigurationChanged(view);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showTaskCreationView() {
        getFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragmentCreateTask, CREATE_TASK_FRAGMENT_TAG)
                .commit();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    @Override
    public void showTaskListView() {
        getFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragmentTaskList, TASK_LIST_FRAGMENT_TAG)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
    }

    @Override
    public void showAllTasks(ArrayList<Task> tasks) {
        fragmentTaskList.updateTaskList(tasks);
    }

    @Override
    public void onCreateTaskClicked(Task task) {
        mPresenter.createNewTask(task);
    }

    @Override
    public void onTaskListReady() {
        mPresenter.onTaskListViewCreated();
    }
}
