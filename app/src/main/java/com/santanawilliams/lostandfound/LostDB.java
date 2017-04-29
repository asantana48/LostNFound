package com.santanawilliams.lostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by andre on 4/29/2017.
 */

public class LostDB extends SQLiteOpenHelper {
    // Database constants
    private static final int VERSION = 1;
    private static final String DBNAME = "lost.sqlite";
    private static final String TBLNAME = "items";

    // Table column names
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String TYPE = "item_type";
    private static final String CONTACT = "contact_info";
    private static final String DESCRIPTION = "description";

    // Table primary key
    private int itemCount;
    public LostDB(Context c) {
        super(c, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE" + TBLNAME + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                TYPE + " TEXT, " +
                CONTACT + " TEXT, " +
                DESCRIPTION + " TEXT);";
        db.execSQL(table);
        itemCount = 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + DBNAME;
        db.execSQL(drop);
        onCreate(db);
    }

    public void insertItem(Item newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        itemCount++;

        ContentValues itemRow = new ContentValues();
        itemRow.put(ID, itemCount);
        itemRow.put(NAME, newItem.getName());
        itemRow.put(TYPE, newItem.getType().toString());
        itemRow.put(CONTACT, newItem.getContactInfo());
        itemRow.put(DESCRIPTION, newItem.getDescription());

        db.insert(TBLNAME, null, itemRow);
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        String query = "SELECT * FROM " + TBLNAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curse = db.rawQuery(query, null);
        while(curse.moveToNext()) {
            Item.ItemType type = Item.ItemType.valueOf(curse.getString(2));
            Item i = new Item( curse.getInt(0),
                    curse.getString(1),
                    type,
                    curse.getString(3),
                    curse.getString(4));
            items.add(i);
        }
        return items;
    }
}
