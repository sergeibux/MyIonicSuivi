package fr.bi.nou.myionicsuivi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import fr.bi.nou.myionicsuivi.threads.ConnectByWifi;


public class GetByWifi extends Thread {

    private Context context = null;

    public GetByWifi(Context context) {
        this.context = context;
    }

    /**
     * Run the connection
     */
    public void start() {
        String err = "starting";
//            String result = command();
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo().isConnected() && connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
            String[] commands = {"D", "Z", "E1", "H1", "SP00", "AL"};
            for (int i=0; i<commands.length; i++) {
                ConnectByWifi connectByWifi = new ConnectByWifi(context, "?AT=" + commands[i]);
                connectByWifi.execute();
            }
        } else {
            err = "NO " + this.context.getString(R.string.err_no_Wifi);
            Log.d ("Erreur #2", "");
        }
        display_ErrToast(err, this.context);
    }

    private String command() {
        String response = "";


        return response;
    }

    public void display_ErrToast(String s, Context context) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.show();
    }
}
