package mana.huda.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


public class Change extends AppCompatActivity {

    EditText txt_username, txt_password;

    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL + "login.php";

    private static final String TAG = Change.class.getSimpleName();

    SharedPreferences sharedpreferences;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_KATEGORI = "kategori";
    public final static String TAG_BAHAN = "bahan";
    public final static String TAG_PEMILIK = "pemilik";
    public final static String TAG_KOTOR = "kotor";
    public final static String TAG_BERSIH = "bersih";
    public final static String TAG_QRCODE = "qrcode";

    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
    }

    public void changepasstrue(){

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_NAMA, null);
        editor.putString(TAG_KATEGORI, null);
        editor.putString(TAG_BAHAN, null);
        editor.putString(TAG_PEMILIK, null);
        editor.putString(TAG_BERSIH, null);
        editor.putString(TAG_KOTOR, null);
        editor.putString(TAG_QRCODE, null);
        editor.commit();

        Intent intent = new Intent(Change.this, Login.class);
        finish();
        startActivity(intent);
    }

    public void chagepass(View view) {
        changepasstrue();
    }
}
