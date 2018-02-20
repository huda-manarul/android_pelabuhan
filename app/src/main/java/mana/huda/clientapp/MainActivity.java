package mana.huda.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_logout;
    TextView txt_id, txt_username;
    TextView txt_nama,txt_kategori,txt_bahan,txt_pemilik,txt_kotor,txt_bersih,txt_qrcode;
    String id, username;
    String nama,kategori,bahan,pemilik,kotor,bersih,qrcode;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_KATEGORI = "kategori";
    public final static String TAG_BAHAN = "bahan";
    public final static String TAG_PEMILIK = "pemilik";
    public final static String TAG_KOTOR = "kotor";
    public final static String TAG_BERSIH = "bersih";
    public final static String TAG_QRCODE = "qrcode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_nama = (TextView) findViewById(R.id.txt_nama);
        txt_kategori = (TextView) findViewById(R.id.txt_kategori);
        txt_bahan = (TextView) findViewById(R.id.txt_bahan);
        txt_pemilik = (TextView) findViewById(R.id.txt_pemilik);
        txt_bersih = (TextView) findViewById(R.id.txt_bersih);
        txt_kotor = (TextView) findViewById(R.id.txt_kotor);
        txt_qrcode = (TextView) findViewById(R.id.txt_qrcode);

        btn_logout = (Button) findViewById(R.id.btn_logout);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        nama = getIntent().getStringExtra(TAG_NAMA);
        kategori = getIntent().getStringExtra(TAG_KATEGORI);
        bahan = getIntent().getStringExtra(TAG_BAHAN);
        pemilik = getIntent().getStringExtra(TAG_PEMILIK);
        kotor = getIntent().getStringExtra(TAG_KOTOR);
        bersih = getIntent().getStringExtra(TAG_BERSIH);
        qrcode = getIntent().getStringExtra(TAG_QRCODE);

        txt_id.setText("ID KAPAL : " + id);
        txt_username.setText("USERNAME : " + username);
        txt_nama.setText("NAMA KAPAL : " + nama);
        txt_kategori.setText("KATEGORI : " + kategori);
        txt_bahan.setText("BAHAN : " + bahan);
        txt_pemilik.setText("PEMILIK KAPAL : " + pemilik);
        txt_kotor.setText("KOTOR KAPAL : " + kotor);
        txt_bersih.setText("BERSIH : " + bersih);
        txt_qrcode.setText("QRCODE KAPAL : " + qrcode);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.putString(TAG_NAMA, null);
                editor.putString(TAG_KATEGORI, null);
                editor.putString(TAG_BAHAN, null);
                editor.putString(TAG_PEMILIK, null);
                editor.putString(TAG_BERSIH, null);
                editor.putString(TAG_KOTOR, null);
                editor.putString(TAG_QRCODE, null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
