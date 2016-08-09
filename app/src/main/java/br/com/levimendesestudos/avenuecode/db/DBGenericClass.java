package br.com.levimendesestudos.avenuecode.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 429317980 on 27/11/2015.
 */
public abstract class DBGenericClass {

    public SQLiteDatabase database;

    /**
     *
     * singleton
     *
     * @param context
     *
     */
    public DBGenericClass(Context context) {
        database = AvenuCodeDB.getInstance(context).getDatabase();
    }

    /**
     * init transaction
     */
    public void startTransaction(){
        database.beginTransaction();
    }

    /**
     * end transaction
     */
    public void commitChanges(){
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    /**
     * close cursor
     * @param cursor
     */
    public void fecharCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
