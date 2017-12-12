package com.ekomartantoh.recyclerview.data;

import android.provider.BaseColumns;

/**
 * Created by hp on 05/12/2017.
 */

public class oStoreContract {
    public static final class oStoreEntry implements BaseColumns{
        public static final String TABLE_NAME ="oStore";
        public static final String COLUMNS_NAMA_BARANG ="namaBarang";
        public static final String COLUMNS_HARGA_BARANG ="hargaBarang";
    }
}