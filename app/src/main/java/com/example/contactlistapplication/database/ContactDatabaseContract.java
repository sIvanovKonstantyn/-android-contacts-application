package com.example.contactlistapplication.database;

public class ContactDatabaseContract {
    static final String TABLE_NAME = "contacts";
    static final String ID_COLUMN = "id";
    static final String NAME_COLUMN = "name";
    static final String PHONE_COLUMN = "phone";

    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME_COLUMN + " TEXT," +
            PHONE_COLUMN + " TEXT)";
}
