package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.activity.ContactInfoActivity;
import com.example.develop.machinedone.mock.Contact;
import com.example.develop.machinedone.mock.Section;


import java.util.List;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.startActivity;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private static final String NAME_FORMAT = "%s %s";

    private List<Contact> mContacts;
    private LayoutInflater mInflater;
    private ContactScrollerAdapter mContactScrollerAdapter;

    public ContactAdapter(Context c, List<Contact> contacts, ContactScrollerAdapter contactScrollerAdapter) {
        mContacts = contacts;
        mInflater = LayoutInflater.from(c);
        mContactScrollerAdapter = contactScrollerAdapter;
    }


    /*public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contact = mInflater.inflate(R.layout.view_contact, parent, false);
        return new ContactViewHolder(contact);
    }*/


    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        Drawable profileImage = contact.getProfileImage();
        holder.pic.setImageDrawable(contact.getProfileImage());
        holder.mName.setText(String.format(Locale.US, NAME_FORMAT, contact.getFirstName(), contact.getLastName()));
        Section s = mContactScrollerAdapter.fromItemIndex(position);
        if (s.getIndex() == position) {
            holder.title.setText(s.getTitle());
        } else {
            holder.title.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        private TextView title;
        private ImageView pic;
        private TextView mName;

        public ContactViewHolder(View itemView)
        {
            super(itemView);
            this.itemView= itemView;
            title =  itemView.findViewById(R.id.title_index);
            pic = itemView.findViewById(R.id.contact_img);
            mName = itemView.findViewById(R.id.contact_name);
        }
    }
    public ContactViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact,parent,false);
      final   ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        contactViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = contactViewHolder.getAdapterPosition();
                Contact contact = mContacts.get(adapterPosition);
                Toast.makeText(view.getContext(),contact.getFirstName()+" "+contact.getLastName(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), ContactInfoActivity.class);
                intent.putExtra("theKey",adapterPosition);
                view.getContext().startActivity(intent);

      }
        });
     return  contactViewHolder;
    }
}
