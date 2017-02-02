package ru.ulteam8.currencynow.db.entries;

import android.provider.BaseColumns;

public final class CurrencyEntry implements BaseColumns {

    public final static String TABLE_NAME = "currencies";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_PICTURE = "picture";
    public final static String COLUMN_SYMBOL = "symbol";
}