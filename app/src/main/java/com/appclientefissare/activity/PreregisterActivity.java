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
import org.json.JSONException;
import org.json.JSONObject;

import com.appclientefissare.R;
import com.appclientefissare.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class PreregisterActivity extends AppCompatActivity {

    private static final String TAG = "PreregisterActivity";
    private static final String URL_FOR_REGISTRATION = "http://192.168.1.103:8080/appServiHogar/srv/web/registro";
    ProgressDialog progressDialog;

    private EditText _emailText;
    private EditText _passwordText;
    private Button _aceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preregister);

        //Progres Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        _emailText = (EditText) findViewById(R.id.preregister_email);
        _passwordText = (EditText) findViewById(R.id.preregister_password);
        _aceptButton = (Button) findViewById(R.id.preregister_button);

        _aceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
                startActivity(new Intent(PreregisterActivity.this, MainActivity.class));
            }
        });

        getSupportActionBar().setTitle("Nuevo Registro");
    }


    private void registro() {
        registerUser(_emailText.getText().toString(),
                _passwordText.getText().toString());
    }


    private void registerUser(final String email, final String password){

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
                        Toast.makeText(getApplicationContext(), "Hola " + user +", Has sido agregado exitosamente!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                PreregisterActivity.this, LoginActivity.class);
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }



}
