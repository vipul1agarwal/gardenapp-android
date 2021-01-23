package com.example.plantnanny;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    private Context context;

    public RetrieveFeedTask(Context context){
        this.context = context.getApplicationContext();
    }

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

            return null;
        } finally {
        }
    }

    @Override
    protected void onPostExecute(String feed) {
        TextView isSuccessResult = (TextView) ((Activity)context).findViewById(R.id.isSuccessResult);
        isSuccessResult.setText(feed);

    }
}