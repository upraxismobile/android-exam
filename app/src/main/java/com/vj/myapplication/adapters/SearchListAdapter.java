package com.vj.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vj.myapplication.R;
import com.vj.myapplication.Util;
import com.vj.myapplication.models.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * Created by VJ on 12/31/2017.
 */

public class SearchListAdapter extends BaseAdapter {
    private static String TAG = SearchListAdapter.class.getSimpleName();

    private Context context;
    private List<Person> personList;

    public SearchListAdapter(Context context, List<Person> persons) {
        this.context = context;
        personList = persons;
    }

    public void setPersonList(List<Person> persons) {
        personList = persons;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Person getItem(int i) {
        return personList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PersonViewHolder holder;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person_list_item, viewGroup, false);
            holder = new PersonViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (PersonViewHolder) view.getTag();
        }

        Person person = personList.get(i);

        Picasso.with(context)
                .load(person.getImage())
                .resize(80, 80)
                .into(holder.image);

        holder.name.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));
        holder.birthday.setText(Util.translateBirthDate(person.getBirthday(), "MM/dd/yyyy", "MMMM dd, yyyy"));

        return view;
    }

    public class PersonViewHolder {
        @BindView(R.id.person_list_item_image) CircleImageView image;
        @BindView(R.id.person_list_item_name) TextView name;
        @BindView(R.id.person_list_item_birthday) TextView birthday;

        public PersonViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
