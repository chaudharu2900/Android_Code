package home.example.com.wifi_hotspot;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.widget.Toast;

public class MyWifiService extends Service {

boolean flagss=true;
    WifiManager myWifiManager;
    int come=10;


    /**
     * indicates how to behave if the service is killed
     */
    int mStartMode;

    /**
     * interface for clients that bind
     */
    IBinder mBinder;

    /**
     * indicates whether onRebind should be used
     */
    boolean mAllowRebind;

    /**
     * Called when the service is being created.
     */
    @Override
    public void onCreate() {

        myWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        RestartWifi();

    }

    /**
     * The service is starting, due to a call to startService()
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      // RestartWifi();

        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
//        if (myWifiManager.isWifiEnabled()) {
//
//            Toast.makeText(getApplicationContext(), "wifi is off... turn it On",
//                    Toast.LENGTH_LONG).show();
//
//            myWifiManager.setWifiEnabled(true);
//        }
        return MyWifiService.START_STICKY;
    }

    /**
     * A client is binding to the service with bindService()
     */
    @Override
    public IBinder onBind(Intent intent) {
        RestartWifi();
        return null;
    }

    /**
     * Called when all clients have unbound with unbindService()
     */
    @Override
    public boolean onUnbind(Intent intent) {
        RestartWifi();
        return mAllowRebind;
    }

    /**
     * Called when a client is binding to the service with bindService()
     */
    @Override
    public void onRebind(Intent intent) {
        RestartWifi();

    }

    /**
     * Called when The service is no longer used and is being destroyed
     */
    @Override
    public void onDestroy() {


    }


    public void RestartWifi(){


        while (come==10) {
            if (myWifiManager.isWifiEnabled()) {

                Toast.makeText(getApplicationContext(), "wifi is off... please wait turning it on automatically ",
                        Toast.LENGTH_LONG).show();

                myWifiManager.setWifiEnabled(true);
            }

        }






    }



}