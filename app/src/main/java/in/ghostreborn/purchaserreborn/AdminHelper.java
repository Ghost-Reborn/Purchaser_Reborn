package in.ghostreborn.purchaserreborn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "customers_and_sellers.db";
    private static final int DATABASE_VERSION = 1;

    public AdminHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, isSeller INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }

    // Add a Person
    public void addPerson(String name, String email, String password, int isSeller) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO person (name, email, password, isSeller) VALUES ('" + name + "', '" + email + "', '" + password + "', '" + isSeller + "')";
        db.execSQL(QUERY);
    }

    // Update a person
    public void updatePerson(int id, String name, String email, String phone_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE person SET name='" + name + "', email='" + email + "', phone_number='" + phone_number + "' WHERE id=" + id);
    }

    // Delete a person
    public void deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM person WHERE id=" + id);
    }

    // Get all people
    public Cursor getAllPerson() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM person", null);
    }

    // Get a customer by id
    public Cursor getPersonById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM person WHERE id=" + id, null);
    }

}