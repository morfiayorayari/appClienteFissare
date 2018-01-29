package com.appclientefissare.activity.init;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appclientefissare.R;
import com.appclientefissare.activity.ClientActivity;
import com.appclientefissare.activity.ProveedorActivity;

public class IngresarcomoActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    private Button _clienteButton;
    private Button _proveedorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarcomo);

        getSupportActionBar().setTitle("Ingresar como:");

        //Progres Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        _clienteButton = (Button) findViewById(R.id.ingresarcomo_cliente);
        _proveedorButton = (Button) findViewById(R.id.ingresarcomo_proveedor);

        _clienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IngresarcomoActivity.this, LoginClienteActivity.class));
            }
        });

        _proveedorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IngresarcomoActivity.this, LoginProveedorActivity.class));
            }
        });
    }
}
