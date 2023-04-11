package com.example.contactlistapplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.contactlistapplication.models.Contact;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class ContactDao implements Closeable {

    private SQLiteDatabase db;

    public ContactDao(Context context) {
        ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        db.close();
    }

    public void save(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactDatabaseContract.NAME_COLUMN, contact.getName());
        values.put(ContactDatabaseContract.PHONE_COLUMN, contact.getPhone());
        db.insert(ContactDatabaseContract.TABLE_NAME, null, values);
    }

    public List<Contact> getAll() {
        List<Contact> contactList = new ArrayList<>();
        String[] projection = {
                ContactDatabaseContract.ID_COLUMN,
                ContactDatabaseContract.NAME_COLUMN,
                ContactDatabaseContract.PHONE_COLUMN
        };
        Cursor cursor = db.query(
                ContactDatabaseContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ContactDatabaseContract.ID_COLUMN));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactDatabaseContract.NAME_COLUMN));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactDatabaseContract.PHONE_COLUMN));
            Contact contact = new Contact(id, name, phone);
            contactList.add(contact);
        }
        cursor.close();
        return contactList;
    }

    public void delete(Contact contact) {
        String selection = ContactDatabaseContract.ID_COLUMN + " = ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };
        db.delete(ContactDatabaseContract.TABLE_NAME, selection, selectionArgs);
    }
}
