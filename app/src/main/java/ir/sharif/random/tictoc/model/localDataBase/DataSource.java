package ir.sharif.random.tictoc.model.localDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;

import ir.sharif.random.tictoc.model.entity.Task;

/**
 * Created by Mojtaba on 8/13/2016.
 */
public class DataSource implements DataBaseService{
    public final String TAG = this.getClass().getSimpleName();
    DataOpenHelper helper;
    SQLiteDatabase database;

    public DataSource(Context context) {
        helper= new DataOpenHelper(context);
    }
    public void open(){
        database= helper.getWritableDatabase();
        Log.i(TAG,"database opened");
    }
    public void close(){
        database.close();
        helper.close();
        Log.i(TAG,"database closed");
    }
    public Task create(Task task){
        open();
        ContentValues values = new ContentValues();
        values.put(DataOpenHelper.COLUMN_TITLE,task.getTitle());
        values.put(DataOpenHelper.COLUMN_DATE,task.getDate());
        values.put(DataOpenHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(DataOpenHelper.COLUMN_START_TIME, task.getStartTime());
        values.put(DataOpenHelper.COLUMN_END_TIME,task.getEndTime());
        values.put(DataOpenHelper.COLUMN_IS_COMPLETED,task.isCompleted());
        values.put(DataOpenHelper.COLUMN_REPEAT_PERIOD,task.getRepeatPeriod());
        long id = database.insert(DataOpenHelper.TABLE_Task,null,values);
        task.setId(id);
        close();
        return task;
    }

    public ArrayList<Task> findAllTasks(){
        open();
        String[] colomns = {DataOpenHelper.COLUMN_TITLE, DataOpenHelper.COLUMN_DATE, DataOpenHelper.COLUMN_DESCRIPTION
                ,DataOpenHelper.COLUMN_START_TIME,DataOpenHelper.COLUMN_END_TIME,
                DataOpenHelper.COLUMN_IS_COMPLETED,DataOpenHelper.COLUMN_REPEAT_PERIOD};
        Cursor cursor = database.query(DataOpenHelper.TABLE_Task,colomns,null,null,null,null,null);
        ArrayList<Task> tasks = cursorToArrayList(cursor);
        close();
        return tasks;
    }
    public ArrayList<Task> findTask(String sql){
        open();
        Cursor cursor = database.rawQuery(sql,null);
        ArrayList<Task> tasks = cursorToArrayList(cursor);
        close();
        return tasks;
    }
    @NonNull
    private ArrayList<Task> cursorToArrayList(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while(cursor.moveToNext()){
            Task task=new Task("temp","temp");
            task.setTitle(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_TITLE)));
            task.setDate(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_DATE)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_DESCRIPTION)));
            task.setStartTime(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_START_TIME)));
            task.setEndTime(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_END_TIME)));
            task.setCompleted(cursor.getInt(cursor.getColumnIndex(DataOpenHelper.COLUMN_IS_COMPLETED))!=0); // convert integer value to boolean
            task.setRepeatPeriod(cursor.getInt(cursor.getColumnIndex(DataOpenHelper.COLUMN_REPEAT_PERIOD)));
            tasks.add(task);
        }
        return tasks;
    }
}
