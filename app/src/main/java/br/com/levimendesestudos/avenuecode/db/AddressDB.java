package br.com.levimendesestudos.avenuecode.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 09/08/2016.
 */
public class AddressDB extends DBGenericClass {

    public static final String TB_NAME = "TB_ADDRESS";

    public static final String ADDRESS   = "address";
    public static final String LATITUDE  = "latitude";
    public static final String LONGITUDE = "longitude";

    public AddressDB(Context context) {
        super(context);
    }

    public boolean save(Address address) {
        ContentValues values = new ContentValues();

        values.put(ADDRESS,   address.formattedAddress);
        values.put(LATITUDE,  address.lati);
        values.put(LONGITUDE, address.longi);

        boolean result = database.insert(TB_NAME, null, values) > 0 ? true : false;

        return result;
    }

    public boolean delete(Address address) {
        String [] param = new String[]{address.formattedAddress};
        boolean result = database.delete(TB_NAME, ADDRESS + " = ?", param) > 0 ? true : false;

        return result;
    }

    public Address find(String address) {
        Address result = null;
        String [] param = new String[]{address};
        Cursor cursor = database.query(TB_NAME, null, ADDRESS + " = ?", param, null, null, null);

        if (cursor.moveToFirst()) {
            //return any object just to say "that address exists"
            result = new Address(null, 0.0, 0.0);
        }

        fecharCursor(cursor);

        return result;
    }
}
