package com.example.mike.boundservicesexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mike.boundservicesexample.MyService.MyLocalBinder;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //creates a myService object
    MyService myService = new MyService();
    //testing if it is bound to a service or not
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates an intent to launch the service
        Intent intent = new Intent(this, MyService.class);
        //binds the service to the class
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    //method to show time when button is pressed
    public void showTime(View v){
        //this creates a link between the textView and the xml
        TextView display = (TextView)findViewById(R.id.showTime_textView);
        //sets the time when the button is pressed
        display.setText(myService.getTime());
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

    //this is the class responsible for binding to some kind of service
    private ServiceConnection myConnection = new ServiceConnection() {
        //what do you wnat to happen when you connect
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //this is a reference/object to the class
            MyLocalBinder binder = (MyLocalBinder) service;
            //calling method
            myService = binder.getService();
            //saying yes this
            isBound=true;
        }
        //what do you wnat to happen when you disconnect
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //saying no this
            isBound = false;
        }
    };
}
