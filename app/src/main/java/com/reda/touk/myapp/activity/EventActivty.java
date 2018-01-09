package com.reda.touk.myapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.reda.touk.myapp.R;
import com.reda.touk.myapp.app.AppConfig;
import com.reda.touk.myapp.app.AppController;
import com.reda.touk.myapp.map.MapsActivityCurrentPlace;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

/**
 * Created by macos on 30/12/2017.
 */

public class EventActivty extends AppCompatActivity {

    EditText edt;
    private ProgressDialog pDialog;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.eventlayout);
            edt = (EditText)findViewById(R.id.editmsg);
        btn = (Button) findViewById(R.id.btnenvoyer);
      //  pDialog = new ProgressDialog(this);
    //    pDialog.setCancelable(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idT= Paper.book().read("userid");
                checkLogin(idT,edt.getText().toString());
            }
        });

    }

    private void checkLogin(final String id, final String text) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

      //  pDialog.setMessage("Logging in ...");
//        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SAVEEVENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

//                pDialog.hide();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session





                        // Launch main activity
                        Intent intent = new Intent(EventActivty.this,
                                MapsActivityCurrentPlace.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("text", text);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
