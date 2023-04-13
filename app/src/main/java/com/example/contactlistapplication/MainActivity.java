package com.example.contactlistapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactlistapplication.adapters.ContactListAdapter;
import com.example.contactlistapplication.database.ContactDao;
import com.example.contactlistapplication.database.ContactDatabase;
import com.example.contactlistapplication.models.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDao contactDao;
    private ListView listView;

    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactDatabase database = ContactDatabase.getInstance(getApplicationContext());
        contactDao = database.contactDao();

        List<Contact> contacts = contactDao.getAll();
        adapter = new ContactListAdapter(this, contacts);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_contact_button);
        addButton.setOnClickListener(v -> {
            addContact();
        });

        Button removeButton = findViewById(R.id.remove_contact_button);
        removeButton.setOnClickListener(v -> {
            removeContact(contacts);
        });
    }

    private void addContact() {
        Contact testContact = new Contact(0, "Test contact", "8080801231");
        contactDao.save(testContact);
        adapter.add(testContact);
    }

    private void removeContact(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            return;
        }
        Contact contactToRemove = contacts.get(0);
        contactDao.delete(contactToRemove);
        adapter.remove(contactToRemove);
    }
}