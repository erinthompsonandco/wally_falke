package com.example.erin.wallyfalkehvac1161505;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends Activity {

    final String myTag = "DocsUpload";
    String data1, data2;
   // Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText dataInput1 = (EditText) findViewById(R.id.editText1);
        EditText dataInput2 = (EditText) findViewById(R.id.editText2);
        data1 = String.valueOf(dataInput1);
        data2 = String.valueOf(dataInput2);
        System.out.println("Data input 1 :" + data1);
        System.out.println("Data input 2 :" + data2);
        Button btnSubmit = (Button) findViewById(R.id.Submit);


//This feels janky. The onClickListener causes execution of the thread, but I'm not really sure
        //if this is appropriate. #newb.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(myTag, "OnCreate()");
                final Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postData();

                    }
                });
                t.start();
            }
        });

       // t.start();

    }

    public void postData() {
//<input type="hidden" name="entry.769973769" jsname="L9xHkb">
        //<input type="hidden" name="entry.1791448798" jsname="L9xHkb">
//"https://docs.google.com/forms/d/1xRyQX7NWZkFSeWgxuFNI3YfwIZvVlmmXE7rbtbdvSqY/formResponse"
        String fullUrl = "https://docs.google.com/forms/d/1xRyQX7NWZkFSeWgxuFNI3YfwIZvVlmmXE7rbtbdvSqY/formResponse";
        HttpRequest mReq = new HttpRequest();
        String col1 = data1;
        String col2 = data2;
        String data = "entry.769973769=" + URLEncoder.encode(col1) + "&" +
                "entry.1791448798=" + URLEncoder.encode(col2);
        // String data = "entry.769973769=" + URLEncoder.encode(col1) + "&" +
        //         "entry.1791448798=" + URLEncoder.encode(col2);
        String response = mReq.sendPost(fullUrl, data);
        Log.i(myTag, response);
    }

}