package de.arbeitsagentur.timetogo;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TKMoodleyAdapter extends CursorAdapter {

    private final Date date;

    private static final DateFormat DF_DATE = SimpleDateFormat
            .getDateInstance(DateFormat.MEDIUM);
    private static final DateFormat DF_TIME = SimpleDateFormat
            .getTimeInstance(DateFormat.MEDIUM);

    private LayoutInflater inflator;

    public TKMoodleyAdapter(Context context) {
        super(context, null, 0);
        date = new Date();
        inflator = LayoutInflater.from(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int ciMood = cursor.getColumnIndex(TKMoodleyOpenHandler.BUCHUNG_ART);
        int ciTimeMillisStart = cursor.getColumnIndex(TKMoodleyOpenHandler.TIME_BUCHUNG);
        int mood = cursor.getInt(ciMood);

        TextView textview1 = (TextView) view.findViewById(R.id.text1);
        TextView textview2 = (TextView) view.findViewById(R.id.text2);
        TextView textview3 = (TextView) view.findViewById(R.id.text3);

        if (mood == TKMoodleyOpenHandler.BUCHUNG_URLAUB) {
            textview3.setText("Urlaub");
        } else if (mood == TKMoodleyOpenHandler.BUCHUNG_ARBEIT_GEHEN) {
            textview3.setText("Gehen");
        } else if (mood == TKMoodleyOpenHandler.BUCHUNG_ARBEIT_KOMMEN) {
            textview3.setText("Kommen");
        } else if (mood == TKMoodleyOpenHandler.BUCHUNG_KRANK) {
            textview3.setText("Krank");
        } else if (mood == TKMoodleyOpenHandler.BUCHUNG_DIENSTREISE_GANZTAEGIG) {
            textview3.setText("Ganztägige Dienstreise");
        } else {
            textview3.setText("Unbekannt");
        }

        long timeMillisStart = cursor.getLong(ciTimeMillisStart);
        date.setTime(timeMillisStart);
        textview1.setText(DF_DATE.format(date));
        textview2.setText(DF_TIME.format(date));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflator.inflate(R.layout.icon_text_text, null);
    }
}
