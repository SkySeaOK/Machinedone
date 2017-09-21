package com.example.develop.machinedone.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.mock.Contact;
import com.example.develop.machinedone.tool.XCRoundImageView;

import java.util.List;

public class ContactInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        XCRoundImageView headImage = findViewById(R.id.head_image);
        TextView personName = findViewById(R.id.peoson_name);
        Intent intent = getIntent();
        int theKey = intent.getIntExtra("theKey", 0);
        List<Contact> mocks = Contact.mocks(this);
        Contact contact = mocks.get(theKey);

        personName.setText(contact.getLastName());
        Drawable profileImage = contact.getProfileImage();
        headImage.setImageDrawable(profileImage);
    }
}
