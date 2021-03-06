package com.example.mlh_user.snapriffs;

import com.getpebble.android.kit.Constants;
import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    // Initialize the chords
    private Chord Am;
    private Chord A;
    private Chord C;
    private Chord D;
    private Chord Em;
    private Chord E;
    private Chord F;
    private Chord G;

    private final int numChords = 8;

    // An array of all the chords
    private Chord[] chords = new Chord[numChords];

    // Declare the selected chords for each fret
    private Chord selectedChord1, selectedChord2,selectedChord3, selectedChord4;

    // isClicked booleans for buttons defined
    private boolean isClicked1 = false;
    private boolean isClicked2 = false;
    private boolean isClicked3 = false;
    private boolean isClicked4 = false;

    /* Declaring Spinners
    private Spinner s1;
    private Spinner s2;
    private Spinner s3;
    private Spinner s4; */

    private Handler mHandler = new Handler();

    // Declare Pebble Data Receiver to receive tap event info from Pebble
    private PebbleKit.PebbleDataReceiver mDataReceiver;

    // Init UUID with the app's UUID
    private static final UUID APP_UUID = UUID.fromString("252d564b-7fa1-4558-b319-56713468ad86");

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    // Takes a note as a string and returns the chord, returns null if there's no match
    private Chord getChordFromString(String chord)
    {
        for (Chord c : chords)
        {
            if (c.name.equals(chord))
                return c;
        }

        return null;
    }

    // Play the current selected chord
    private void playChord()
    {
        // Store the context variable in context
        Context context = this.getApplicationContext();

        if (isClicked1)
        {
            if (selectedChord1 == null)
                return;
            selectedChord1.playChord(context);
        } if (isClicked2) {
            if (selectedChord2 == null)
                return;
            selectedChord2.playChord(context);
        } if (isClicked3) {
            if (selectedChord3 == null)
                return;
            selectedChord3.playChord(context);
        } if (isClicked4) {
            if (selectedChord4 == null)
                return;
            selectedChord4.playChord(context);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the spinners
        final Spinner s1 = (Spinner) findViewById(R.id.spinner);
        final Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        final Spinner s4 = (Spinner) findViewById(R.id.spinner4);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final Context context = getApplicationContext();

        // Initialize the chords
        Am = new Chord("Am", R.raw.am_chord, context);
        A = new Chord("A", R.raw.a_chord, context);
        C = new Chord("C", R.raw.c_chord, context);
        D = new Chord("D", R.raw.d_chord, context);
        Em = new Chord("Em", R.raw.em_chord, context);
        E = new Chord("E", R.raw.em_chord, context);
        F = new Chord("F", R.raw.f_chord, context);
        G = new Chord("G", R.raw.g_chord, context);

        // Initialize the array of all the chords
        chords[0] = Am;
        chords[1] = A;
        chords[2] = C;
        chords[3] = D;
        chords[4] = Em;
        chords[5] = E;
        chords[6] = F;
        chords[7] = G;

        // Create ArrayAdapter with all the chords
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.chords_array, R.layout.support_simple_spinner_dropdown_item);

        // Set the view for each entry
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter for each spinner to the adapter we just made
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        s3.setAdapter(adapter);
        s4.setAdapter(adapter);

        // Set the selected item listeners for the spinners
        // Each one updates the appropriate selectedChord, 1 through 4
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Log.d("SnapRiffs", "Item selected: " + s1.getSelectedItem().toString());
                selectedChord1 = getChordFromString(s1.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Log.d("SnapRiffs", "Item selected: " + s2.getSelectedItem().toString());
                selectedChord2 = getChordFromString(s2.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Log.d("SnapRiffs", "Item selected: " + s3.getSelectedItem().toString());
                selectedChord3 = getChordFromString(s3.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Log.d("SnapRiffs", "Item selected: " + s4.getSelectedItem().toString());
                selectedChord4 = getChordFromString(s4.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // Create the buttons
        final Button b1 = (Button) findViewById(R.id.button);
        final Button b2 = (Button) findViewById(R.id.button2);
        final Button b3 = (Button) findViewById(R.id.button3);
        final Button b4 = (Button) findViewById(R.id.button4);

        // Set button transparent
        b1.setBackgroundColor(Color.TRANSPARENT);
        b1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SnapRiffs", "1 is clicked");
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Tell program, button one is clicked
                        isClicked1 = true;
                        Log.d("SnapRiffs", "down");
                        // And no other buttons are clicked
                        isClicked2 = false;
                        isClicked3 = false;
                        isClicked4 = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("SnapRiffs", "up");
                        isClicked1 = false;
                        break;
                }
                //playChord();
                return false;
            }
        });

        // Set button transparent
        b2.setBackgroundColor(Color.TRANSPARENT);
        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SnapRiffs", "2 is clicked");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Tell program, button two is clicked
                        isClicked2 = true;
                        Log.d("SnapRiffs", "down");
                        // And no other buttons are clicked
                        isClicked1 = false;
                        isClicked3 = false;
                        isClicked4 = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("SnapRiffs", "up");
                        isClicked2 = false;
                        break;
                }
                //playChord();
                return false;
            }
        });

        // Set button transparent
        b3.setBackgroundColor(Color.TRANSPARENT);
        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SnapRiffs", "3 is clicked");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Tell program, button three is clicked
                        isClicked3 = true;
                        Log.d("SnapRiffs", "down");
                        // And no other buttons are clicked
                        isClicked1 = false;
                        isClicked2 = false;
                        isClicked4 = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("SnapRiffs", "up");
                        isClicked3 = false;
                        break;
                }
                //playChord();
                return false;
            }

        });

        // Set button transparent
        b4.setBackgroundColor(Color.TRANSPARENT);
        b4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SnapRiffs", "4 is clicked");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Tell program, button four is clicked
                        isClicked4 = true;
                        Log.d("SnapRiffs", "down");
                        // And no other buttons are clicked
                        isClicked1 = false;
                        isClicked2 = false;
                        isClicked3 = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("SnapRiffs", "up");
                        isClicked4 = false;
                        break;
                }
                //playChord();
                return false;
            }
        });

        // Pebble stuff

        // If we don't have a data receiver for the pebble, create one and register it
        if(mDataReceiver == null) {
            mDataReceiver = new PebbleKit.PebbleDataReceiver(APP_UUID) {

                // If we get a message, ACK the message and play the current chord
                // the pebble app only sends a message if there's a tap
                @Override
                public void receiveData(Context context, int transactionId, PebbleDictionary dict) {
                    // Always ACK the message. Communicate!
                    PebbleKit.sendAckToPebble(context, transactionId);
                    Log.d("Pebble", "Got message from Pebble!");

                    playChord();
                }

            };
        }

        // Register the data handler
        PebbleKit.registerReceivedDataHandler(getApplicationContext(), mDataReceiver);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mlh_user.snapriffs/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mlh_user.snapriffs/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // If we still have a data receiver, unregister it and set it to null
        if(mDataReceiver != null) {
            unregisterReceiver(mDataReceiver);
            mDataReceiver = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // If we don't have a data receiver for the pebble, create one and register it
        if(mDataReceiver == null) {
            mDataReceiver = new PebbleKit.PebbleDataReceiver(APP_UUID) {

                // If we get a message, ACK the message and play the current chord
                // the pebble app only sends a message if there's a tap
                @Override
                public void receiveData(Context context, int transactionId, PebbleDictionary dict) {
                    // Always ACK the message. Communicate!
                    PebbleKit.sendAckToPebble(context, transactionId);
                    Log.i("receiveData", "Got message from Pebble!");

                    playChord();
                }

            };

            // Register the data handler
            PebbleKit.registerReceivedDataHandler(getApplicationContext(), mDataReceiver);
        }
    }
}