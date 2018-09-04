package com.example.turbo.attiree;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class signup extends AppCompatActivity {

    EditText fname, lname, name, password, email, phone;
    String Fname, Lname, Name, Password, Email, Phone;
    Context ctx=this;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname = (EditText) findViewById(R.id.register_fname);
        lname = (EditText) findViewById(R.id.register_lname);
        name = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        phone = (EditText) findViewById(R.id.register_phone);
        btn = (Button) findViewById(R.id.btnregister);

    }

    public void register_register(View v){
        Fname = fname.getText().toString();
        Lname = lname.getText().toString();
        Name = name.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        Phone = phone.getText().toString();
        BackGround b = new BackGround();
        b.execute(Fname, Lname, Name, Password, Email, Phone);
    }

    class BackGround extends AsyncTask<String, String ,String>{

        @Override
        protected String doInBackground(String... params) {
            String fname = params[0];
            String lname = params[1];
            String name = params[2];
            String password = params[3];
            String email = params[4];
            String phone = params[5];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.10.6:8080/adminpanel/app.php");
                String urlParams = "fname="+fname+"&lname="+lname+"&name="+name+"&password="+password+"&email="+email+"&phone="+phone;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";

                Intent in = new Intent(getBaseContext(),signin.class);
                startActivity(in);
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

}