package com.appclientefissare.activity.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.appclientefissare.R;
import com.appclientefissare.activity.ProveedorActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginProveedorActivity extends AppCompatActivity {

    EditText emailBox, passwordBox;
    Button loginButton;
    TextView registerLink;
    String URL = "http://192.168.1.108:8080/appServiHogar/srv/web/login";
    //String URL = "http://fissare.ayniwork.com/appServiHogar/srv/web/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_proveedor);

        emailBox = (EditText)findViewById(R.id.emailBox);
        passwordBox = (EditText)findViewById(R.id.passwordBox);
        loginButton = (Button)findViewById(R.id.loginButton);
        registerLink = (TextView)findViewById(R.id.registerLink);

        getSupportActionBar().setTitle("Iniciar como Proveedor");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("true")){
                            Toast.makeText(LoginProveedorActivity.this, "Login Exitoso", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginProveedorActivity.this, ProveedorActivity.class));
                        }
                        else{
                            Toast.makeText(LoginProveedorActivity.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginProveedorActivity.this, "Ocurrió un error -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", emailBox.getText().toString());
                        parameters.put("password", passwordBox.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(LoginProveedorActivity.this);
                rQueue.add(request);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginProveedorActivity.this, RegisterProveedorActivity.class));
            }
        });
    }
}
