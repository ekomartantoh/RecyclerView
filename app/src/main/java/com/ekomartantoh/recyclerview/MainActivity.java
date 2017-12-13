package com.ekomartantoh.recyclerview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ekomartantoh.recyclerview.data.oStoreContract;
import com.ekomartantoh.recyclerview.data.oStoreDbHelper;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =MainActivity.class.getSimpleName() ;
    protected static EditText namaBarang, harga;
    //langkah 1 membuat field database
    private SQLiteDatabase mDb;
    private oStoreListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaBarang = (EditText) findViewById(R.id.edt_Nm_Brg);
        harga = (EditText) findViewById(R.id.edt_Hrg);
        //rvItem = (RecyclerView) findViewById(R.id.rvItm);

        //langkah2
        oStoreDbHelper dbHelper = new oStoreDbHelper(this);
        //langkah3
        mDb=dbHelper.getWritableDatabase();
        //langkah6
        Cursor cursor = getAllBarang();
        //langkah 11
        RecyclerView oStoreReciclerView;
        oStoreReciclerView = (RecyclerView) findViewById(R.id.rvItm);
        oStoreReciclerView.setLayoutManager( new LinearLayoutManager(this));
        mAdapter = new oStoreListAdapter(this, cursor);
        oStoreReciclerView.setAdapter(mAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeBarang(id);
                mAdapter.swapCursor(getAllBarang());
            }
        }).attachToRecyclerView(oStoreReciclerView);

    }

    //langkah4
    private Cursor getAllBarang(){
        //langkah5
        return mDb.query(
                oStoreContract.oStoreEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                oStoreContract.oStoreEntry.COLUMNS_NAMA_BARANG
        );
    }

    public void tambah_barang(View view) {
        if (namaBarang.getText().length()==0||
            harga.getText().length()==0)
            return;
        double hargaBarang = 1;
        try {
            hargaBarang = Integer.parseInt(harga.getText().toString());
        }catch (Exception ex){
            Log.e(LOG_TAG, "Filed to parse text to number"+ex.getMessage());
        }
        addBarang(namaBarang.getText().toString(), hargaBarang);
        mAdapter.swapCursor(getAllBarang());

    }

    public long addBarang(String nama, double harga){
        ContentValues cv= new ContentValues();
        cv.put(oStoreContract.oStoreEntry.COLUMNS_NAMA_BARANG, nama);
        cv.put(oStoreContract.oStoreEntry.COLUMNS_HARGA_BARANG, harga);
        return mDb.insert(oStoreContract.oStoreEntry.TABLE_NAME, null, cv);
    }
    public boolean removeBarang(long id){
        return mDb.delete(oStoreContract.oStoreEntry.TABLE_NAME,
                oStoreContract.oStoreEntry._ID+"="+id,null )>0;
    }

}
