package com.example.contactlistapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactlistapplication.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void save(Contact contact);

    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Delete
    void delete(Contact contact);
}
