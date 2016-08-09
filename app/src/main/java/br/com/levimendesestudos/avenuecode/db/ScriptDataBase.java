package br.com.levimendesestudos.avenuecode.db;

/**
 * Created by 809778 on 09/08/2016.
 */
public class ScriptDataBase {

    public static final String CREATE_TB_ADDREESS = "CREATE TABLE IF NOT EXISTS " + AddressDB.TB_NAME  + " (" +
            AddressDB.ADDRESS   + " TEXT PRIMARY KEY NOT NULL, " +
            AddressDB.LATITUDE  + " REAL NOT NULL,             "  +
            AddressDB.LONGITUDE + " REAL NOT NULL);";

    public static final String DROP_TB_ADDRESS = "DROP TABLE IF EXISTS " + AddressDB.TB_NAME;
}
