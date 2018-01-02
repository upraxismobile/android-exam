package com.vj.myapplication.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vj.myapplication.R;
import com.vj.myapplication.models.Address;
import com.vj.myapplication.models.ViewHolder;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 *
 * Created by VJ on 12/31/2017.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private static String TAG = AddressAdapter.class.getSimpleName();

    private List<Address> addressList;

    public AddressAdapter(List<Address> addresses) {
        addressList = addresses;
    }

    public void setAddressListList(List<Address> addresses) {
        addressList = addresses;
        notifyDataSetChanged();
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_item, parent, false);
        return new AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {

        if(holder != null) {
            final Address address = addressList.get(position);

            holder.name.setText(address.getName());
//            holder.latitude.setText(String.valueOf(address.getLat()));
//            holder.longitude.setText(String.valueOf(address.getLng()));

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = String.format(Locale.ENGLISH, "google.navigation:q=%f,%f", address.getLat(), address.getLng());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    view.getContext().startActivity(Intent.createChooser(intent, " "));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends ViewHolder {
        @BindView(R.id.address_list_item_root) LinearLayout root;
        @BindView(R.id.address_list_item_name) TextView name;
        @BindView(R.id.address_list_item_lat) TextView latitude;
        @BindView(R.id.address_list_item_long) TextView longitude;

        public AddressViewHolder(View itemView) {
            super(itemView);
        }

    }
}
