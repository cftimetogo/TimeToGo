package de.arbeitsagentur.timetogo;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public ConfigDaten p = new ConfigDaten();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        load();

        final Button buttonKommen = (Button) findViewById(R.id.arbeitKommen);
        buttonKommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonClicked(TKMoodleyOpenHandler.BUCHUNG_ARBEIT_KOMMEN);
            }
        });

        final Button buttonGehen = (Button) findViewById(R.id.arbeitGehen);
        buttonGehen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonClicked(TKMoodleyOpenHandler.BUCHUNG_ARBEIT_GEHEN);
            }
        });

        final Button buttonBuchen = (Button) findViewById(R.id.buchen);
        buttonBuchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonClicked(TKMoodleyOpenHandler.BUCHUNG_ARBEIT_KOMMEN);
            }
        });
        final Button buttonAuswertung = (Button) findViewById(R.id.auswertung);
        buttonAuswertung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

        /**
         * Datei Speichern
         */

        // das Eingabefeld
        final EditText edit = (EditText) findViewById(R.id.edit);
        // Leeren
        final Button bClear = (Button) findViewById(R.id.clear);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });
        // Laden
        final Button bLoad = (Button) findViewById(R.id.load);
        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(p.getName());
            }
        });
        // Speichern
        final Button bSave = (Button) findViewById(R.id.save);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setName(edit.getText().toString());
                save();
            }
        });

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
            setContentView(R.layout.activity_settings);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            return true;
        }

        if (id == R.id.action_main) {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            return true;
        }

        if (id == R.id.action_uebersicht) {
            setContentView(R.layout.activity_uebersicht);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                Uri.parse("android-app://de.arbeitsagentur.timetogo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


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
                Uri.parse("android-app://de.arbeitsagentur.timetogo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void imageButtonClicked(int mood) {
        if(p.getButtonStatus() == true) {
            findViewById(R.id.arbeitGehen).setVisibility(View.INVISIBLE);
            findViewById(R.id.arbeitKommen).setVisibility(View.VISIBLE);
            p.setButtonStatus(false);
        } else {
            findViewById(R.id.arbeitGehen).setVisibility(View.VISIBLE);
            findViewById(R.id.arbeitKommen).setVisibility(View.INVISIBLE);
            p.setButtonStatus(true);
        }
        save();

        ContentValues values = new ContentValues();
        values.put(TKMoodleyOpenHandler.BUCHUNG_ART, mood);
        values.put(TKMoodleyOpenHandler.TIME_BUCHUNG, System.currentTimeMillis());
        getContentResolver().insert(TKMoodleyProvider.CONTENT_URI, values);
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }

    public void load() {
        // Von der Festplatte laden
        ConfigDaten p2 = null;
        ObjectInputStream ois = null;
        try {
            new FileInputStream(getFilesDir()+"/datasave" );
        } catch (FileNotFoundException e) {
            save();
        }

        try {
            System.out.println(new FileInputStream(getFilesDir()+"/datasave" ));
            ois = new ObjectInputStream(new FileInputStream(getFilesDir()+"/datasave"));
            p2 = (ConfigDaten) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        p = p2;
    }

    private void save() {
        // Auf die Festplatte speichern
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(), "/datasave")));
            oos.writeObject(p);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

