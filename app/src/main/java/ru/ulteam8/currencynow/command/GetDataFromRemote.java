package ru.ulteam8.currencynow.command;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ru.ulteam8.currencynow.interfaces.OnCompleteListener;
import ru.ulteam8.currencynow.interfaces.OnCompletePublisher;

public class GetDataFromRemote extends AsyncTask<Void, Void, String> implements OnCompletePublisher {

    private final String LOG_TAG = "GET DATA FROM REMOTE";
    private String connectionString;
    private List<OnCompleteListener> listeners;
    private String result = "";

    public GetDataFromRemote(String connectionString) {
        this.connectionString = connectionString;
        this.listeners = new ArrayList<>();
    }

    @Override
    protected String doInBackground(Void... params) {
        String resultJson = "";
        try {
            URL url = new URL(connectionString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String response) {
        result = response;
        notifyListener();
        super.onPostExecute(response);
    }

    @Override
    public void addListener(OnCompleteListener listener) {
        if (listeners.indexOf(listener) == -1) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(OnCompleteListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListener() {
        for (OnCompleteListener listener : listeners) {
            listener.onComplete(this.result);
        }
        listeners.removeAll(listeners);
    }
}
