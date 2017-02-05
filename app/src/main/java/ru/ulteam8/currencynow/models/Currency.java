package ru.ulteam8.currencynow.models;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import ru.ulteam8.currencynow.db.entries.CurrencyEntry;
import ru.ulteam8.currencynow.interfaces.CurrencyRefs;

public class Currency implements CurrencyRefs {

    private long id;
    private int name;
    private int picture;
    private String symbol;

    public Currency(int name, int picture, String symbol) {
        this.name = name;
        this.picture = picture;
        this.symbol = symbol;
    }

    public Currency(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex(CurrencyEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(CurrencyEntry.COLUMN_NAME);
        int pictureColumnIndex = cursor.getColumnIndex(CurrencyEntry.COLUMN_PICTURE);
        int symbolColumnIndex = cursor.getColumnIndex(CurrencyEntry.COLUMN_SYMBOL);

        this.id = cursor.getLong(idColumnIndex);
        this.name = cursor.getInt(nameColumnIndex);
        this.picture = cursor.getInt(pictureColumnIndex);
        this.symbol = cursor.getString(symbolColumnIndex);
    }

    @Override
    public String getRepresentation(Context context) {
        return context.getResources().getString(name);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public Drawable getPicture(Context context) {
        return context.getResources().getDrawable(picture, null);
    }

    public long getId() {
        return id;
    }
}
