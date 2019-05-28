package fr.bi.nou.myionicsuivi.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import fr.bi.nou.myionicsuivi.R;

public class ConnectByWifi extends AsyncTask<Void, Void, Void> {
    private URL url;
    private URLConnection urlConnection;
    private int respCode;
    private Context context;
    private InputStream inputStream;
    private String err;

    public ConnectByWifi(Context context, String req) {
        try {
            this.url = new URL("http://192.0.10.0" + req);
        }catch (Exception e) {
            err = "\n..." + context.getString(R.string.unknown_err) + "\n >> " + e;
        }
        this.urlConnection = urlConnection;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            urlConnection = (HttpURLConnection) this.url.openConnection();
            respCode = ((HttpURLConnection) urlConnection).getResponseCode();
            if (respCode == HttpURLConnection.HTTP_OK) {
                err = this.context.getString(R.string.w_connection_ok);
                Log.d("Erreur #-1", "\nOK\n" + respCode);
            } else {
                err = " -- " + context.getString(R.string.w_connection_err);
            }
        } catch (Exception e){
            Log.d ("Erreur #1 : ", e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (this.err != null) {
            Toast.makeText(this.context, this.err, Toast.LENGTH_LONG).show();
            Log.d("ERR", this.err);
        }
    }
}
