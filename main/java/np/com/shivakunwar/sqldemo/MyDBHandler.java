package np.com.shivakunwar.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sql_Demo";
    public static final String TABLE_NAME = "Donor_List";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_NAME + "(" +
                COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_NAME + "TEXT " +
                COLUMN_TYPE + "TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add a new row to the database
    public void addName(DonorReg donor){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, donor.get_name());
        values.put(COLUMN_TYPE, donor.get_type());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //delete a row in database
    public void deleteName (String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME + "WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    //print out the databse as a sting
    public String databasetoString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + "WHERE 1";

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("name"))!=null){
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

}
