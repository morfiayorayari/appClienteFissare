package com.appclientefissare.activity.init;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.appclientefissare.activity.ClientActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginClienteActivity extends AppCompatActivity {

    EditText emailBoxCliente, passwordBoxCliente;
    Button loginButtonCliente;
    TextView registerLinkCliente;
    String URL = "http://192.168.1.108:8080/appServiHogar/srv/web/login";
    //String URL = "http://fissare.ayniwork.com/appServiHogar/srv/web/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

        emailBoxCliente = (EditText)findViewById(R.id.emailBoxCliente);
        passwordBoxCliente = (EditText)findViewById(R.id.passwordBoxCliente);
        loginButtonCliente = (Button)findViewById(R.id.loginButtonCliente);
        registerLinkCliente = (TextView)findViewById(R.id.registerLinkCliente);

        getSupportActionBar().setTitle("Iniciar como Cliente");

        loginButtonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("true")){
                            Toast.makeText(LoginClienteActivity.this, "Login Exitoso", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginClienteActivity.this, ClientActivity.class));
                        }
                        else{
                            Toast.makeText(LoginClienteActivity.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginClienteActivity.this, "Ocurrió un error -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", emailBoxCliente.getText().toString());
                        parameters.put("password", passwordBoxCliente.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(LoginClienteActivity.this);
                rQueue.add(request);
            }
        });

        registerLinkCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginClienteActivity.this, RegisterClientActivity.class));
            }
        });
    }
}
