package de.arbeitsagentur.timetogo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TKMoodleyOpenHandler extends SQLiteOpenHelper {

	private static final String TAG = TKMoodleyOpenHandler.class
			.getSimpleName();

	private static final String DATABASE_NAME = "timetogo.db";
	private static final int DATABASE_VERSION = 4;

	public static final String TABLE_NAME = "timetogo";
	public static final String _ID = "_id";
	public static final String TIME_BUCHUNG = "timeMillis";
	public static final String BUCHUNG_ART = "buchung";

	public static final int BUCHUNG_ARBEIT_KOMMEN = 1;
	public static final int BUCHUNG_ARBEIT_GEHEN = 2;
	public static final int BUCHUNG_KRANK = 3;
	public static final int BUCHUNG_URLAUB = 4;
	public static final int BUCHUNG_DIENSTREISE_GANZTAEGIG = 5;

	private static final String TABLE_MOOD_CREATE = "CREATE TABLE "
			+ TABLE_NAME + " (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME_BUCHUNG + " INTEGER, "
			+ BUCHUNG_ART + " INTEGER);";

	private static final String TABLE_MOOD_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	TKMoodleyOpenHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_MOOD_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrade der Datenbank von Version " + oldVersion + " zu "
				+ newVersion + "; alle Daten werden gelöscht");
		db.execSQL(TABLE_MOOD_DROP);
		onCreate(db);
	}

	public void insert(int mood, long timeMillis) {
		long rowId = -1;
		try {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(BUCHUNG_ART, mood);
			values.put(TIME_BUCHUNG, timeMillis);
			rowId = db.insert(TABLE_NAME, null, values);
		} catch (SQLiteException e) {
			Log.e(TAG, "insert()", e);
		} finally {
			Log.d(TAG, "insert(): rowId=" + rowId);
		}
	}

	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		return db.query(TABLE_NAME, null, null, null, null, null,
				TIME_BUCHUNG + " DESC");
	}

	public void update(long id, int smiley) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BUCHUNG_ART, smiley);
		int numUpdated = db.update(TABLE_NAME, values, _ID + " = ?",
				new String[] { Long.toString(id) });
		Log.d(TAG, "update(): id=" + id + " -> " + numUpdated);
	}

	public void delete(long id) {
		SQLiteDatabase db = getWritableDatabase();
		int numDeleted = db.delete(TABLE_NAME, _ID + " = ?",
				new String[] { Long.toString(id) });
		Log.d(TAG, "delete(): id=" + id + " -> " + numDeleted);
	}
}
