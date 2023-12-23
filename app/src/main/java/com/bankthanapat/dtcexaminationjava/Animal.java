package com.bankthanapat.dtcexaminationjava;

import android.provider.BaseColumns;

public class Animal {

    int ID;
    private String name;
    public static final String TABLE = "Animal";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String NAME = "name";
    }
}
