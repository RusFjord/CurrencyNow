package ru.ulteam8.currencynow;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.ulteam8.currencynow.common.DownloadData;
import ru.ulteam8.currencynow.interfaces.OnCompleteListener;
import ru.ulteam8.currencynow.models.Currency;
import ru.ulteam8.currencynow.models.CurrencyPair;

public class MainActivity extends ListActivity implements OnCompleteListener {

    private CurrencyPair currencyPair;
    private List<Currency> list;
    //float valueFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentData();
        setUI();
    }

    private void setUI() {
        CurrencyAdapter adapter = new CurrencyAdapter(this, 0, list);

        setListAdapter(adapter);
//        final ImageView baseCurrencyFlag = (ImageView) findViewById(R.id.baseCurrencyFlag);
//        Currency baseCurrency = (Currency) currencyPair.getFirstCurrency();
//        baseCurrencyFlag.setImageDrawable(baseCurrency.getPicture(getApplicationContext()));
//        final TextView baseCurrencyName = (TextView) findViewById(R.id.baseCurrencyName);
//        baseCurrencyName.setText(baseCurrency.getRepresentation(getApplicationContext()));
//
//        final ImageView secondCurrencyFlag = (ImageView) findViewById(R.id.secondCurrencyFlag);
//        Currency secondCurrency = (Currency) currencyPair.getSecondCurrency();
//        secondCurrencyFlag.setImageDrawable(secondCurrency.getPicture(getApplicationContext()));
//        final TextView secondCurrencyName = (TextView) findViewById(R.id.secondCurrencyName);
//        secondCurrencyName.setText(secondCurrency.getRepresentation(getApplicationContext()));
//
//        final EditText currencyValue = (EditText) findViewById(R.id.currencyValue);
//        final Button exchange = (Button) findViewById(R.id.butEschange);
//        exchange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentValue = currencyValue.getText().toString();
//                if (!currentValue.isEmpty()) {
//                    valueFloat = Float.valueOf(currentValue);
//                    TextView textView = (TextView) findViewById(R.id.result);
//                    String text = currencyPair.getRepresentation(getApplicationContext()) + " " + currencyPair.getRatioFirstToSecond(valueFloat);
//                    textView.setText(text);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Empty value", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void getCurrentData() {
        list = new ArrayList<>();
        Currency rub = new Currency(R.string.russian_ruble, R.drawable.rub, "RUB");
        Currency usd = new Currency(R.string.us_dollar, R.drawable.usd, "USD");
        list.add(rub);
        list.add(usd);

//        currencyPair = new CurrencyPair(rub, usd);
//        DownloadData downloadData = new DownloadData();
//        downloadData.getDataFromRemoteServer(currencyPair, this);
    }

    @Override
    public void onComplete(Object o) {
        String result = (String) o;
        if (!result.isEmpty()) {
            currencyPair.setCoefficient(result);
        }
    }
}
