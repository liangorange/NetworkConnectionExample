package com.example.li4ngorange.networkconnectionexample;

import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

// Libraries used to access to access network resources
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;
    Button b1;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // downloadImage("http://www.hdwallpapersbot.com/wp-content/uploads/2015/09/Mobile-Background-Grass-Lion-Wallpaper-nice-android-wallpaper1.jpg");

        b1 = (Button) findViewById(R.id.button);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetConenction();
                // downloadImage("http://www.tutorialspoint.com/green/images/logo.png");

                downloadImage("http://cdn.appstorm.net/android.appstorm.net/files/2012/03/Fire-Moth.jpg");
            }
        });
    }

    private void downloadImage(String urlStr) {

        // progressDialog = ProgressDialog.show(this, "", "Downloading Image from " + urlStr);
        progressDialog = ProgressDialog.show(this, "", "Downloading Images from web");

        final String url = urlStr;

        final String urlNew = "http://www.hdwallpapersbot.com/wp-content/uploads/2015/09/Mobile-Background-Grass-Lion-Wallpaper-nice-android-wallpaper1.jpg";

        loadImageOne(url);
        loadImageTwo(urlNew);



        /*
        try {
            System.out.println("Old Thread Joined");
            oldThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        /*
        new Thread() {
            public void run() {
                InputStream in = null;

                // Message defines a message containing a description and arbitrary data object can be sent to a Handler
                Message msg = Message.obtain();

                // User-defined message code so that the recipient can identify what this message is about.
                // Each Handler has its own namespace for message codes, so you do not need to worry about yours conflicting with other handlers
                msg.what = 1;

                try {
                    in = openHttpConnection(url);

                    // Decode an input stream into a bitmap.
                    bitmap = BitmapFactory.decodeStream(in);

                    // A mapping from String values to various Parcelable types
                    Bundle b = new Bundle();

                    // Inserts a Parcelable value into the mapping of this Bundle, replacing any existing value for the given key.
                    // Either key or value may be null
                    b.putParcelable("bitmap", bitmap);

                    // Sets a Bundle of arbitrary data values. Use arg1 and arg2 memebers as a lower cost way to send a few simple integer values, if you can
                    msg.setData(b);

                    in.close();
                }

                catch (IOException e1) {
                    e1.printStackTrace();
                }
                messageHandler.sendMessage(msg);
            }
        }.start();
        */

            /*
            Thread newThread = new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    InputStream in = null;

                    // Message defines a message containing a description and arbitrary data object can be sent to a Handler
                    Message msg = Message.obtain();

                    // User-defined message code so that the recipient can identify what this message is about.
                    // Each Handler has its own namespace for message codes, so you do not need to worry about yours conflicting with other handlers
                    msg.what = 1;

                    try {
                        in = openHttpConnection("http://www.hdwallpapersbot.com/wp-content/uploads/2015/09/Mobile-Background-Grass-Lion-Wallpaper-nice-android-wallpaper1.jpg");

                        // Decode an input stream into a bitmap.
                        bitmap = BitmapFactory.decodeStream(in);

                        // A mapping from String values to various Parcelable types
                        Bundle b = new Bundle();

                        // Inserts a Parcelable value into the mapping of this Bundle, replacing any existing value for the given key.
                        // Either key or value may be null
                        b.putParcelable("bitmapOne", bitmap);

                        // Sets a Bundle of arbitrary data values. Use arg1 and arg2 memebers as a lower cost way to send a few simple integer values, if you can
                        msg.setData(b);

                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    messageHandlerOne.sendMessage(msg);
                }
            });

            newThread.start();


            try {
                System.out.println("Old Thread Joined");
                oldThread.join();
                Log.i(TAG, "New Thread Joined");
                // System.out.println("New Thread Joined");
                newThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

    }


    public void retrieveImage() {

        System.out.println("This is the second thread");

    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {

            // URL class can be used to define a pointer to a web resource
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            // HttpURLConnection class can be used to access a web resource
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
            progressDialog.dismiss();
        }
    };


    private Handler messageHandlerOne = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView img = (ImageView) findViewById(R.id.imageView2);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmapOne")));
            progressDialog.dismiss();
        }
    };


    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, "Internet Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, "Internet Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


    public void loadImageOne(String url) {

        final String urlNew = url;

        Thread oldThread = new Thread(new Runnable() {

            @Override
            public void run() {
                InputStream in = null;

                // Message defines a message containing a description and arbitrary data object can be sent to a Handler
                Message msg = Message.obtain();

                // User-defined message code so that the recipient can identify what this message is about.
                // Each Handler has its own namespace for message codes, so you do not need to worry about yours conflicting with other handlers
                msg.what = 1;

                try {
                    in = openHttpConnection(urlNew);

                    // Decode an input stream into a bitmap.
                    bitmap = BitmapFactory.decodeStream(in);

                    // A mapping from String values to various Parcelable types
                    Bundle b = new Bundle();

                    // Inserts a Parcelable value into the mapping of this Bundle, replacing any existing value for the given key.
                    // Either key or value may be null
                    b.putParcelable("bitmap", bitmap);

                    // Sets a Bundle of arbitrary data values. Use arg1 and arg2 memebers as a lower cost way to send a few simple integer values, if you can
                    msg.setData(b);

                    in.close();
                }

                catch (IOException e1) {
                    e1.printStackTrace();
                }
                messageHandler.sendMessage(msg);

                // newThread.start();
            }
        });

        oldThread.start();
    }


    public void loadImageTwo(String url) {

        final String urlNew = url;

        Thread newThread = new Thread(new Runnable() {

            @Override
            public void run() {

                InputStream in = null;

                // Message defines a message containing a description and arbitrary data object can be sent to a Handler
                Message msg = Message.obtain();

                // User-defined message code so that the recipient can identify what this message is about.
                // Each Handler has its own namespace for message codes, so you do not need to worry about yours conflicting with other handlers
                msg.what = 1;

                try {
                    in = openHttpConnection("http://www.hdwallpapersbot.com/wp-content/uploads/2015/09/Mobile-Background-Grass-Lion-Wallpaper-nice-android-wallpaper1.jpg");

                    // in = openHttpConnection("http://www.tutorialspoint.com/green/images/logo.png");

                    // Decode an input stream into a bitmap.
                    bitmap = BitmapFactory.decodeStream(in);

                    // A mapping from String values to various Parcelable types
                    Bundle b = new Bundle();

                    // Inserts a Parcelable value into the mapping of this Bundle, replacing any existing value for the given key.
                    // Either key or value may be null
                    b.putParcelable("bitmapOne", bitmap);

                    // Sets a Bundle of arbitrary data values. Use arg1 and arg2 memebers as a lower cost way to send a few simple integer values, if you can
                    msg.setData(b);

                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                messageHandlerOne.sendMessage(msg);
            }
        });

        newThread.start();
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
}
