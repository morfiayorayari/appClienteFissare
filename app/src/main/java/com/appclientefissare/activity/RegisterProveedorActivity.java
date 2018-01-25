package com.appclientefissare.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.appclientefissare.R;

public class RegisterProveedorActivity extends AppCompatActivity {

    private static final String TAG = "PreregisterActivity";
    private static final String URL_FOR_REGISTRATION = "http://172.16.220.241:8080/appServiHogar/srv/web/registro";
    ProgressDialog progressDialog;

    private EditText _cedulaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_proveedor);

        getSupportActionBar().setTitle("Registro - Nuevo Proveedor");
    }
}
