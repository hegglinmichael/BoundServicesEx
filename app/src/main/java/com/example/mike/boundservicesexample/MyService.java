package com.example.mike.boundservicesexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//activity tries to bind
//looks for method onBind

public class MyService extends Service {

    //object responsible for binding the client to the service
    private final IBinder myBinder = new MyLocalBinder();

    //constructor for this class
    public MyService() {
    }

    //what you want to really happen in here
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    //returns the time
    public String getTime() {
        //creates an object to format and get the time
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.US);
        //returns the date(time)
        return (df.format(new Date()));
    }

    //this class is used to return a reference to MyService
    //has the ability to bind
    public class MyLocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
            //returns to do the stuff inside MyService class
        }
    }
}
