package com.ekomartantoh.recyclerview.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 05/12/2017.
 */

public class oStoreDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ostore.db";
    public static final int DATABASE_VERSION = 1;
    public oStoreDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_OSTORE_TABLE= "CREATE TABLE "+
                oStoreContract.oStoreEntry.TABLE_NAME+" ("+
                oStoreContract.oStoreEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                oStoreContract.oStoreEntry.COLUMNS_NAMA_BARANG+" TEXT NOT NULL, "+
                oStoreContract.oStoreEntry.COLUMNS_HARGA_BARANG+" DOUBLE NOT NULL"+
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_OSTORE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ oStoreContract.oStoreEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
