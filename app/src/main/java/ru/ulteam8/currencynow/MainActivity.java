package ru.ulteam8.currencynow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ru.ulteam8.currencynow.common.DownloadData;
import ru.ulteam8.currencynow.interfaces.OnCompleteListener;
import ru.ulteam8.currencynow.models.Currency;
import ru.ulteam8.currencynow.models.CurrencyPair;

public class MainActivity extends Activity implements OnCompleteListener {

    private CurrencyPair currencyPair;
    float valueFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentData();
        setUI();

    }

    private void setUI() {
        final ImageView baseCurrencyFlag = (ImageView) findViewById(R.id.baseCurrencyFlag);
        Currency baseCurrency = (Currency) currencyPair.getFirstCurrency();
        baseCurrencyFlag.setImageDrawable(baseCurrency.getPicture(getApplicationContext()));
        final TextView baseCurrencyName = (TextView) findViewById(R.id.baseCurrencyName);
        baseCurrencyName.setText(baseCurrency.getRepresentation(getApplicationContext()));
        final EditText currencyValue = (EditText) findViewById(R.id.currencyValue);
        final Button exchange = (Button) findViewById(R.id.butEschange);
        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentValue = currencyValue.getText().toString();
                if (!currentValue.isEmpty()) {
                    valueFloat = Float.valueOf(currentValue);
                    TextView textView = (TextView) findViewById(R.id.result);
                    String text = currencyPair.getRepresentation(getApplicationContext()) + " " + currencyPair.getRatioFirstToSecond(valueFloat);
                    textView.setText(text);
                } else {
                    Toast.makeText(getApplicationContext(), "Empty value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCurrentData() {
        Currency rub = new Currency(R.string.russian_ruble, R.drawable.rub, "RUB");
        Currency usd = new Currency(R.string.us_dollar, R.drawable.usd, "USD");
        currencyPair = new CurrencyPair(rub, usd);
        DownloadData downloadData = new DownloadData();
        downloadData.getDataFromRemoteServer(currencyPair, this);
    }

    @Override
    public void onComplete(Object o) {
        String result = (String) o;
        if (!result.isEmpty()) {
            currencyPair.setCoefficient(result);
        }
    }
}
