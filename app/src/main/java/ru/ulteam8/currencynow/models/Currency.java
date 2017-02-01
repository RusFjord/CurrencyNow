package ru.ulteam8.currencynow.models;

import android.content.Context;
import android.graphics.drawable.Drawable;

import ru.ulteam8.currencynow.interfaces.Refs;

public class Currency implements Refs {

    private long id;
    private int name;
    private int picture;

    public Currency(long id, int name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getRepresentation(Context context) {
        return context.getResources().getString(name);
    }

    public Drawable getPicture(Context context) {
        return context.getResources().getDrawable(picture, null);
    }
}
