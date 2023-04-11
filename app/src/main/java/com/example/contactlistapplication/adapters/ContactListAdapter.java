package com.example.contactlistapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.contactlistapplication.R;
import com.example.contactlistapplication.models.Contact;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    public ContactListAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate a new layout for the ListView item if necessary
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
        }

        // Get the Contact object for the current position
        Contact contact = getItem(position);

        // Set the name and phone number in the layout views
        TextView nameTextView = convertView.findViewById(R.id.name_text_view);
        TextView phoneTextView = convertView.findViewById(R.id.phone_text_view);
        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhone());

        return convertView;
    }
}