package com.ekomartantoh.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekomartantoh.recyclerview.data.oStoreContract;

/**
 * Created by hp on 05/12/2017.
 */

public class oStoreListAdapter extends RecyclerView.Adapter<oStoreListAdapter.oStoreViewHolder> {
    private Context mContext;
    //langkah 7
    private Cursor mCursor;
    //langkah 8
    public oStoreListAdapter(Context context, Cursor cursor){
        this.mContext = context;
        //langkah 9
        this.mCursor=cursor;
    }
    @Override
    public oStoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new oStoreViewHolder(view);
    }
    @Override
    public void onBindViewHolder(oStoreViewHolder holder, int position){
        if (!mCursor.moveToPosition(position))
            return;
        String nama =mCursor.getString(mCursor.getColumnIndex(oStoreContract.oStoreEntry.COLUMNS_NAMA_BARANG));
        double harga = mCursor.getDouble(mCursor.getColumnIndex(oStoreContract.oStoreEntry.COLUMNS_HARGA_BARANG));
        long id = mCursor.getLong(mCursor.getColumnIndex(oStoreContract.oStoreEntry._ID));
        holder.namaBarang.setText(nama);
        holder.hargaBarang.setText(String.valueOf(harga));
        holder.itemView.setTag(id);
    }
    //langkah 10
    @Override
    public int getItemCount(){return mCursor.getCount();}

    public void swapCursor(Cursor newCursor){
        if (mCursor!=null) mCursor.close();
        mCursor=newCursor;
        if(newCursor!=null){
            this.notifyDataSetChanged();
        }
    }
    class oStoreViewHolder extends RecyclerView.ViewHolder{
        TextView namaBarang;
        TextView hargaBarang;
        public oStoreViewHolder(View itemView){
            super(itemView);
            namaBarang = (TextView) itemView.findViewById(R.id.Nm_Brg);
            hargaBarang = (TextView) itemView.findViewById(R.id.Hrg);
        }
    }
}
