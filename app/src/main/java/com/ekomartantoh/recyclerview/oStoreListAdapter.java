package com.ekomartantoh.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 05/12/2017.
 */

public class oStoreListAdapter extends RecyclerView.Adapter<oStoreListAdapter.oStoreViewHolder> {
    private Context mContext;
    public oStoreListAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public oStoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new oSoteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(oStoreViewHolder holder, int position){

    }
    @Override
    public getItemCount(){};

    public void swapCursor(Cursor newCursor){

    }
    class oStoreViewHolder extends RecyclerView.ViewHolder{
        TextView namaBarang;
        TextView hargaBarang;
        public oStoreViewHolder(View itemView){
            super(itemView);
            namaBarang = (TextView) itemView.findViewById(R.id.nmBrg);
            hargaBarang = (TextView) itemView.findViewById(R.id.hrg);
        }
    }
}
