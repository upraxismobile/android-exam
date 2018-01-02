package com.vj.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vj.myapplication.R;
import com.vj.myapplication.models.Contact;
import com.vj.myapplication.models.ViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 *
 * Created by VJ on 12/31/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private static String TAG = ContactAdapter.class.getSimpleName();

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contacts) {
        contactList = contacts;
    }

    public void setContactList(List<Contact> contacts) {
        contactList = contacts;
        notifyDataSetChanged();
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        if(holder != null) {
            Contact contact = contactList.get(position);

            holder.name.setText(contact.getName());
            holder.mobile.setText(String.valueOf(contact.getMobileNumber()));
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends ViewHolder {
        @BindView(R.id.contact_list_item_name) TextView name;
        @BindView(R.id.contact_list_item_mobile) TextView mobile;

        public ContactViewHolder(View itemView) {
            super(itemView);
        }

    }
}
