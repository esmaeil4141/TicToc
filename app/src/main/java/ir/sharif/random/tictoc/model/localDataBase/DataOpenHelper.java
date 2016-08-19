package ir.sharif.random.tictoc.model.localDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mojtaba on 8/13/2016.
 */
public class DataOpenHelper extends SQLiteOpenHelper{
    public final String TAG = this.getClass().getSimpleName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME ="DataBase.db" ;
    public static final String TABLE_Task ="TASK" ;

    public static final String COLUMN_ID= "ID";
    public static final String COLUMN_TITLE= "TITLE";
    public static final String COLUMN_DATE= "DATE";
    public static final String COLUMN_START_TIME = "START";
    public static final String COLUMN_END_TIME= "END";
    public static final String COLUMN_IS_COMPLETED= "ISCOMPLETED";
    public static final String COLUMN_REPEAT_PERIOD= "REPEAT";

    private static final String TASK_TABLE_CREATE = "CREATE TABLE "+ TABLE_Task +
            " ( "+
            COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_TITLE +" TEXT ," +
            COLUMN_DATE +" TEXT ," +
            COLUMN_START_TIME +" TEXT ," +
            COLUMN_END_TIME +" TEXT ," +
            COLUMN_IS_COMPLETED +" INTEGER ," +
            COLUMN_REPEAT_PERIOD + " INTEGER );";

    public DataOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
        Log.i(TAG, "database created with 1 table:"+TABLE_Task );
        Log.i(TAG,TASK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_Task );
        onCreate(db);
        Log.i(TAG, "database ReCreated");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_Task );
        onCreate(db);
        Log.i(TAG, "database ReCreated in downGrade");
    }
}
