package com.example.bashayer93.regblue;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class depregister extends AppCompatActivity {
    Button r_submit;
    EditText r_ID, r_FullName, r_Password, r_repassword, r_email, r_reemail , r_address, r_relation, r_rid;
    String id, name, pass, repass, email, remail, address, relation, rid;
    Context ctx=this;
    CheckBox r_policy;
    TextView popup_pol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depregister);


        r_ID = (EditText) findViewById(R.id.ID);
        r_FullName = (EditText) findViewById(R.id.FullName);
        r_Password = (EditText) findViewById(R.id.Password);
        r_repassword = (EditText) findViewById(R.id.repassword);
        r_email = (EditText) findViewById(R.id.email);
        r_reemail = (EditText) findViewById(R.id.reemail);
        r_address= (EditText) findViewById(R.id.address);
        r_policy= (CheckBox) findViewById(R.id.policy);
        r_relation= (EditText) findViewById(R.id.relation);
        r_rid= (EditText) findViewById(R.id.RID);


    }

   /* public void policy_pop(View view)
    {

        Intent i = new Intent(getApplicationContext(),policy.class);
        startActivity(i);

    }*/

    public void register_register(View v) {


        id = r_ID.getText().toString();
        name = r_FullName.getText().toString();
        pass = r_Password.getText().toString();
        repass = r_repassword.getText().toString();
        email = r_email.getText().toString();
        remail = r_reemail.getText().toString();
        address = r_address.getText().toString();
        relation= r_relation.getText().toString();
        rid= r_rid.getText().toString();

        if( r_ID.getText().toString().length() == 0 ){
            r_ID.setError( "ID is required!" );}
        if( r_FullName.getText().toString().length() == 0 ){
            r_FullName.setError( "Name is required!" );}
        if( r_Password.getText().toString().length() == 0 ){
            r_Password.setError( "Password is required!" );}
        if( r_repassword.getText().toString().length() == 0 ){
            r_repassword.setError( "Re Enter your password" );}
        if( r_email.getText().toString().length() == 0 ){
            r_email.setError( "Email is required!" );}
        if( r_reemail.getText().toString().length() == 0 ){
            r_reemail.setError( "Re Enter your email" );}
        if( r_address.getText().toString().length() == 0 ){
            r_address.setError("Address is required!");}
        if( r_relation.getText().toString().length() == 0 ){
            r_relation.setError("Relation is required!");}
        if( r_rid.getText().toString().length() == 0 ){
            r_rid.setError("Resident ID is required!");}
        if (!r_policy.isChecked()){
            r_policy.setError("you must agree to policy");}

        else {
            if( !pass.equals(repass )){
                r_repassword.setError( "Password dose not match" );}
            if( !email.equals(remail )){
                r_reemail.setError( "email dose not match" );}
            else {
                BackGround b = new BackGround();
                b.execute(id, rid, name, pass, email, address, relation);
            }
        }
    }
    class BackGround extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {

            String id = params[0];
            String rid = params[1];
            String name = params[2];
            String pass = params[3];
            String email = params[4];
            String address = params[5];
            String relation = params[6];
            String data="";
            int tmp;




            try {
                URL url = new URL("http://192.168.8.102/ES/depregister.php");

                String urlParams = "id="+id+"&name="+name+"&pass="+pass+"&email="+email+"&address="+address+"&relation="+relation+"&rid="+rid;

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
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx, MainActivity.class);

            startActivity(i);
        }


    }

}
