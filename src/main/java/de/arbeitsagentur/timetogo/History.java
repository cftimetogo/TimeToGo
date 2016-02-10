package de.arbeitsagentur.timetogo;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CursorAdapter;

public class History extends ListActivity {

    private CursorAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerForContextMenu(getListView());
        ca = new TKMoodleyAdapter(this);
        setListAdapter(ca);
        updateList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_good:
                update(info.id, TKMoodleyOpenHandler.BUCHUNG_ARBEIT_KOMMEN);
                updateList();
                return true;
            case R.id.menu_ok:
                update(info.id, TKMoodleyOpenHandler.BUCHUNG_ARBEIT_GEHEN);
                updateList();
                return true;
            case R.id.menu_delete:
                Uri uri = Uri.withAppendedPath(TKMoodleyProvider.CONTENT_URI,
                        Long.toString(info.id));
                getContentResolver().delete(uri, null, null);
                updateList();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void update(long id, int mood) {
        Uri uri = Uri.withAppendedPath(TKMoodleyProvider.CONTENT_URI,
                Long.toString(id));
        ContentValues values = new ContentValues();
        values.put(TKMoodleyOpenHandler.BUCHUNG_ART, mood);
        getContentResolver().update(uri, values, null, null);
    }

    private void updateList() {
        Cursor cursor = getContentResolver().query(TKMoodleyProvider.CONTENT_URI,
                null, null, null, TKMoodleyOpenHandler.TIME_BUCHUNG + " DESC");
        ca.changeCursor(cursor);
    }
}
