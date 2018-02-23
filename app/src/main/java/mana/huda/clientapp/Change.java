package mana.huda.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mana.huda.clientapp.app.AppController;


public class Change extends AppCompatActivity {

    EditText txt_username, txt_password,txt_newpassword,txt_confirm_newpassword;

    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL + "edit.php";

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

        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_newpassword = (EditText) findViewById(R.id.txt_newpassword);
        txt_confirm_newpassword = (EditText) findViewById(R.id.txt_confirm_newpassword);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected())
             {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

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

    private void checkLogin(final String username, final String password, final String newpassword){
        //Toast.makeText(getApplicationContext(),"username = "+username+" pasword lama = "+password+" password baru "+newpassword,Toast.LENGTH_LONG).show();
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    if (success == 1){
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        changepasstrue();
                    }
                    Toast.makeText(getApplicationContext(),
                            jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override//kene woi
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", username);
                params.put("pass", password);
                params.put("newpass", newpassword);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    public void chagepass(View view) {
        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();
        String newpassword = txt_newpassword.getText().toString();
        String confirmpassword = txt_confirm_newpassword.getText().toString();
        if (newpassword.equals(confirmpassword)){
            checkLogin(username, password,newpassword);
        }
        else{
            Toast.makeText(getApplicationContext(),"pasword tidak cocok",
                    Toast.LENGTH_LONG).show();
        }


        //changepasstrue();
    }
}
