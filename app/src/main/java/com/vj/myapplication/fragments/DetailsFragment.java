package com.vj.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vj.myapplication.R;
import com.vj.myapplication.Util;
import com.vj.myapplication.adapters.AddressAdapter;
import com.vj.myapplication.adapters.ContactAdapter;
import com.vj.myapplication.models.Address;
import com.vj.myapplication.models.Contact;
import com.vj.myapplication.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * Created by VJ on 12/30/2017.
 */

public class DetailsFragment extends BaseFragment {
    public static String TAG = DetailsFragment.class.getSimpleName();

    @BindView(R.id.details_layout_address_recyclerview) RecyclerView addressListView;
    @BindView(R.id.details_layout_contact_recyclerview) RecyclerView contactListView;
    @BindView(R.id.details_layout_image) CircleImageView image;
    @BindView(R.id.details_layout_name) TextView name;
    @BindView(R.id.details_layout_birthday) TextView birthday;
    @BindView(R.id.details_layout_email) TextView email;
    @BindView(R.id.details_layout_mobile) TextView mobile;
    @BindView(R.id.details_layout_age) TextView age;

    AddressAdapter addressAdapter;
    List<Address> addressList = new ArrayList<>();

    ContactAdapter contactAdapter;
    List<Contact> contactList = new ArrayList<>();

    Person person;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getArguments() != null && getArguments().getSerializable("person") != null) {
            person = (Person) getArguments().getSerializable("person");
        }
    }

    public DetailsFragment addArguments(Bundle bundle) {
        setArguments(bundle);
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        addressAdapter = new AddressAdapter(addressList);
        addressListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addressListView.setAdapter(addressAdapter);

        contactAdapter = new ContactAdapter(contactList);
        contactListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactListView.setAdapter(contactAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(person != null) {
            populate(person);
        }
    }

    private void populate(Person person) {
        if(person != null) {
            Picasso.with(getContext())
                    .load(person.getImage())
                    .into(image);

            email.setText(person.getEmail());
            mobile.setText(person.getMobileNumber());
            name.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));

            age.setText(String.format(Locale.US, "Age : %d ", person.getAge()));
            birthday.setText(Util.translateBirthDate(person.getBirthday(), "MM/dd/yyyy", "MMMM dd, yyyy"));

            if(!person.getAddress().isEmpty()) {
                addressAdapter.setAddressListList(person.getAddress());
            }

            if(!person.getContacts().isEmpty()) {
                contactAdapter.setContactList(person.getContacts());
            }
        }
    }
}
