package ru.ulteam8.currencynow.common;

import android.util.Log;

import java.net.URL;

import ru.ulteam8.currencynow.interfaces.CurrencyRefs;
import ru.ulteam8.currencynow.models.CurrencyPair;

public class DownloadData {

    private final static String LOG_TAG = "DOWNLOAD DATA";

    public void getDataFromRemoteServer(CurrencyPair currencyPair) {
        String connectionHttp = getConnectionHttp(currencyPair.getFirstCurrency(), currencyPair.getSecondCurrency());

        URL url = null;
        try {
            url = new URL(connectionHttp);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Ошибка соединение с url: " + connectionHttp);
        }
    }

    private String getConnectionHttp(CurrencyRefs firstCurrency, CurrencyRefs secondCurrency) {
        final String REMOTE_SERVER_HTTP = "http://apilayer.net/api/live?access_key=194c2a70cdd58b82578f152004b94c88";
        final String REMOTE_FORMAT_JSON = "&format=1";
        StringBuilder result = new StringBuilder(REMOTE_SERVER_HTTP);
        result.append("&");
        result.append("currencies=");
        result.append(firstCurrency.getSymbol());
        result.append("&");
        result.append("source=");
        result.append(secondCurrency.getSymbol());
        result.append(REMOTE_FORMAT_JSON);
        return result.toString();
    }
}
