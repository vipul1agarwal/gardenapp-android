package com.example.plantnanny;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    TextView isSuccessResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonUp = (Button) findViewById(R.id.buttonUp);
        Button buttonRight = (Button) findViewById(R.id.buttonRight);
        Button buttonLeft = (Button) findViewById(R.id.buttonLeft);
        Button buttonDown = (Button) findViewById(R.id.buttonDown);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonHome = (Button) findViewById(R.id.buttonHome);
        Button buttonScan = (Button) findViewById(R.id.buttonScan);




        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("0","+200");
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("1","-200");
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("0","-200");
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("1","+200");
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("2","");
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("3","");
            }
        });

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isSuccessResult = (TextView) findViewById(R.id.isSuccessResult);

                new RetrieveFeedTask().execute("4","");
            }
        });
    }


    private class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {

            InputSource is = null;
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader xmlreader = parser.getXMLReader();
                UrlRequestHandler theRSSHandler = new UrlRequestHandler();
                return theRSSHandler.getResponse(urls[0],urls[1]);
            } catch (Exception e) {
                this.exception = e;
                return "NOT OKAY";
            }
        }


        @Override
        protected void onPostExecute(String s) {
            isSuccessResult.setText(s);
        }
    }
}
