package ru.ulteam8.currencynow;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ulteam8.currencynow.models.Currency;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    private Context context;

    public CurrencyAdapter(Context context, int resource, List<Currency> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Currency currentCurrency = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.element_list, null);
        }
        ImageView currencyFlag = (ImageView) convertView.findViewById(R.id.currencyFlag);
        currencyFlag.setImageDrawable(currentCurrency.getPicture(context));
        ImageView addDel = (ImageView) convertView.findViewById(R.id.addDel);
        addDel.setImageDrawable(context.getDrawable(R.drawable.minus));
        TextView currency = (TextView) convertView.findViewById(R.id.currency);
        String symbol = currentCurrency.getSymbol();
        if (symbol != null) {
            currency.setText(symbol);
        }
        TextView changeCurrency = (TextView) convertView.findViewById(R.id.changeCurrency);
        changeCurrency.setText("V");
        EditText valueCurrency = (EditText) convertView.findViewById(R.id.valueCurrency);
        valueCurrency.setInputType(InputType.TYPE_NULL);
        return convertView;
    }
}
