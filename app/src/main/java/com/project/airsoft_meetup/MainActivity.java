package com.project.airsoft_meetup;

import android.os.Environment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import data.*;


public class MainActivity extends AppCompatActivity {

    Global glob = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glob = Util.Naloži();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Login_Click(View view){
        EditText eTxT = (EditText)findViewById(R.id.editText);
        String usr = eTxT.getText().toString();

        eTxT = (EditText)findViewById(R.id.editText2);
        String pass = eTxT.getText().toString();

        if(Util.Login(glob, usr, pass))
            Toast.makeText(this, "Login complete", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
   }
}
