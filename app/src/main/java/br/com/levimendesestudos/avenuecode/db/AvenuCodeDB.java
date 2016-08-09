package br.com.levimendesestudos.avenuecode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 809778 on 09/08/2016.
 */
public class AvenuCodeDB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private SQLiteDatabase database;
    private static AvenuCodeDB avenuCodeDB;


    private AvenuCodeDB(Context context) {
        super(context, "avenue", null, DB_VERSION);

        database = getWritableDatabase();
    }

    /**
     *
     * Singleton da classe
     *
     * @return
     *
     */
    public static AvenuCodeDB getInstance(Context context) {
        if (avenuCodeDB == null || !avenuCodeDB.database.isOpen()) {
            avenuCodeDB = new AvenuCodeDB(context);
        }

        return avenuCodeDB;
    }

    /**
     *
     * Metodo chamado na criacao do banco de dados
     *
     * @param db
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDataBase.DROP_TB_ADDRESS);

        db.execSQL(ScriptDataBase.CREATE_TB_ADDREESS);
    }

    /**
     * metodo chamado toda vez que a versao do banco banco de dados e alterada
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

}
