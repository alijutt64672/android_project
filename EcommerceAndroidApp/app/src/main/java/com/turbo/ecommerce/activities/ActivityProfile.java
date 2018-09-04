package com.turbo.ecommerce.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.turbo.ecommerce.R;


public class ActivityProfile extends AppCompatActivity {
EditText height,waist,shoulders,collarsize,armlength,chestsize,ankle;
Button btndummyok;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        height = (EditText) findViewById(R.id.height);
        waist = (EditText) findViewById(R.id.waist);
        shoulders = (EditText) findViewById(R.id.shoulders);
        collarsize = (EditText) findViewById(R.id.collarsize);
        armlength = (EditText) findViewById(R.id.armlength);
        chestsize = (EditText) findViewById(R.id.chestsize);
        ankle = (EditText) findViewById(R.id.ankle);
        btndummyok = (Button) findViewById(R.id.btndummyok);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_profile);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


public void saveinfo(View view){
    SharedPreferences dummyvalues = getSharedPreferences("dummyvalues", MODE_PRIVATE);
    SharedPreferences.Editor editor = dummyvalues.edit();
    editor.putString("height",height.getText().toString() );
    if( height.getText().toString().length() == 0 )
        height.setError( "height is required!" );
    editor.putString("waist",waist.getText().toString() );
    if( waist.getText().toString().length() == 0 )
        waist.setError( "waist is required!" );
    editor.putString("shoulders",shoulders.getText().toString() );
    if( shoulders.getText().toString().length() == 0 )
        shoulders.setError( "shoulders is required!" );
    editor.putString("collarsize",collarsize.getText().toString() );
    if( collarsize.getText().toString().length() == 0 )
        collarsize.setError( "collar size is required!" );
    editor.putString("armlength",armlength.getText().toString() );
    if( armlength.getText().toString().length() == 0 )
        armlength.setError( "arm length is required!" );
    editor.putString("chestsize",chestsize.getText().toString() );
    if( chestsize.getText().toString().length() == 0 )
        chestsize.setError( "chest size is required!" );
    editor.putString("ankle",ankle.getText().toString() );
    if( ankle.getText().toString().length() == 0 )
        ankle.setError( "ankle is required!" );
    else {
        editor.apply();
        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_LONG).show();

    (new Handler())
            .postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent = getPackageManager().getLaunchIntentForPackage("com.app.model3D.view.MenuActivity");
                            startActivity(intent);

                        }
                    }, 1000);}}



//public void displaydata(View view){
//        SharedPreferences dummyvalues = getSharedPreferences("dummyvalues", MODE_PRIVATE);
//        String Height = dummyvalues.getString("height", "");
//        String Waist = dummyvalues.getString("waist", "");
//        String Shoulders = dummyvalues.getString("shoulders", "");
//        String Collarsize = dummyvalues.getString("collarsize", "");
//        String Armlength = dummyvalues.getString("armlength", "");
//        String Chestsize = dummyvalues.getString("chestsize", "");
//        String Ankle = dummyvalues.getString("ankle", "");
//        height.setText((CharSequence) height);
//}

}

