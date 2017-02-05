package ru.ulteam8.currencynow.common;

import ru.ulteam8.currencynow.command.GetDataFromRemote;
import ru.ulteam8.currencynow.interfaces.CurrencyRefs;
import ru.ulteam8.currencynow.interfaces.OnCompleteListener;
import ru.ulteam8.currencynow.models.CurrencyPair;

public class DownloadData {

    private final static String LOG_TAG = "DOWNLOAD DATA";

    public void getDataFromRemoteServer(CurrencyPair currencyPair, OnCompleteListener listener) {
        String connectionHttp = getConnectionHttp(currencyPair.getFirstCurrency(), currencyPair.getSecondCurrency());

        String result = "";
        GetDataFromRemote getDataFromRemote = new GetDataFromRemote(connectionHttp);
        getDataFromRemote.addListener(listener);
        getDataFromRemote.execute();
        System.out.println(result);
    }

    private String getConnectionHttp(CurrencyRefs firstCurrency, CurrencyRefs secondCurrency) {
        final String REMOTE_SERVER_HTTP = "http://api.fixer.io/latest?";
        return REMOTE_SERVER_HTTP + "&" +
                "base=" +
                firstCurrency.getSymbol() +
                "&symbols=" +
                secondCurrency.getSymbol();
    }
}
