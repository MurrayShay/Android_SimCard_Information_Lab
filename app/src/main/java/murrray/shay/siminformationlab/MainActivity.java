package murrray.shay.siminformationlab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private static final String LT = MainActivity.class.getSimpleName();
    private TextView NetMncText;
    private TextView NetMccText;
    private TextView SimMncText;
    private TextView SimMccText;
    private TextView nciText;
    private TextView sciText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findView();

        NetMncText.setText(getMnc(this));
        NetMccText.setText(getMcc(this));
        SimMncText.setText(getSimMnc(this));
        SimMccText.setText(getSimMcc(this));
        nciText.setText(getNetworkCountryISO(this));
        sciText.setText(getSimCountryISO(this));
    }


    private void findView() {
        NetMncText = (TextView) findViewById(R.id.NetMncText);
        NetMccText = (TextView)findViewById(R.id.NetMccText);
        SimMncText = (TextView)findViewById(R.id.SimMncText);
        SimMccText = (TextView)findViewById(R.id.SimMccText);
        sciText = (TextView)findViewById(R.id.sciText);
        nciText = (TextView)findViewById(R.id.nciText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getNetworkCountryISO(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.
                    getSystemService(Context.TELEPHONY_SERVICE);
            String NetworkCountry = tm.getNetworkCountryIso();
            return NetworkCountry.toLowerCase(Locale.US);
        }
        catch (Exception e) {
            Log.w(LT, "getNetworkCountryISO failed",e);
            return null;
        }
    }

    public static String getSimCountryISO(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.
                    getSystemService(Context.TELEPHONY_SERVICE);
            String simCountry = tm.getSimCountryIso();
            return simCountry.toLowerCase(Locale.US);
        }
        catch (Exception e) {
            Log.w(LT, "getSimCountryISO failed",e);
            return null;
        }
    }

    public static String getMcc(Context context) {
        try {
            TelephonyManager telephoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String networkOperator = telephoneManager.getNetworkOperator();

            String mcc = networkOperator.substring(0, 3);
            return mcc;
        }
        catch (Exception e) {
            Log.w(LT, "getMcc failed",e);
            return null;
        }
    }

    public static String getSimMcc(Context context) {
        try {
            TelephonyManager telephoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String simOperator = telephoneManager.getSimOperator();

            String mcc = simOperator.substring(0, 3);
            return mcc;
        }
        catch (Exception e) {
            Log.w(LT, "getSimMcc failed",e);
            return null;
        }
    }

    public static String getMnc(Context context) {
        try {

            TelephonyManager telephoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String networkOperator = telephoneManager.getNetworkOperator();

            String mnc = networkOperator.substring(3);
            return mnc;
        }
        catch (Exception e) {
            Log.w(LT, "getMnc failed",e);
            return null;
        }

    }

    public static String getSimMnc(Context context) {
        try {

            TelephonyManager telephoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String simOperator = telephoneManager.getSimOperator();
            String mnc = simOperator.substring(3);
            return mnc;
        }
        catch (Exception e) {
            Log.w(LT, "getSimMnc failed",e);
            return null;
        }

    }

    public static String getCellId(Context context) {

        try {
            TelephonyManager telephoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephoneManager.getCellLocation();
            String cellId = String.valueOf(gsmCellLocation.getCid());
            return cellId;

        } catch (Exception e) {
            Log.w(LT, "getCellId failed",e);
            return null;
        }
    }
}
