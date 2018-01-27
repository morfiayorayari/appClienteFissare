package com.appclientefissare.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appclientefissare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterClientActivity extends AppCompatActivity {

    private static final String TAG = "RegisterClient";
    private static final String URL_FOR_REGISTRATION = "http://172.16.220.241:8080/appServiHogar/srv/web/registro";
    ProgressDialog progressDialog;

    private EditText _cedulaText;
    private EditText _nombresText;
    private EditText _direccionText;
    private EditText _telfijoText;
    private EditText _movilText;
    private EditText _emailText;

    private Button _acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        getSupportActionBar().setTitle("Registro - Nuevo Cliente");

        //Progres Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        _cedulaText = (EditText) findViewById(R.id.register_client_cedula);
        _nombresText = (EditText) findViewById(R.id.register_client_name);
        _direccionText = (EditText) findViewById(R.id.register_client_direccion);
        _telfijoText = (EditText) findViewById(R.id.register_client_telfijo);
        _movilText = (EditText) findViewById(R.id.register_client_telmovil);
        _emailText = (EditText) findViewById(R.id.register_client_email);

        _acceptButton = (Button) findViewById(R.id.accept_client);


        _acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                registro();
                startActivity(new Intent(RegisterClientActivity.this, ClientActivity.class));
                //Toast.makeText(RegisterClientActivity.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
            }
        });
    }




    private void registro() {
        registroClient(
                _cedulaText.getText().toString(), _nombresText.getText().toString(),
                _direccionText.getText().toString(), _telfijoText.getText().toString(),
                _movilText.getText().toString(), _emailText.getText().toString()
        );
    }




    private void registroClient(final String cedula, final String nombres, final String direccion,
                                   final String telfijo, final String telmovil, final String email){

        String cancel_req_tag = "register";

        progressDialog.setMessage("Agregando ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hola " + user + ", Has sido agregado exitosamente!", Toast.LENGTH_SHORT).show();

                        // Launch register activity
                        Intent intent = new Intent(
                                RegisterClientActivity.this, ClientActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("cedula", cedula);
                params.put("nombres", nombres);
                params.put("direccion", direccion);
                params.put("telfijo", telfijo);
                params.put("telmovil", telmovil);
                params.put("email", email);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);


    }//end registroProveedor() method



    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }


    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }



}
