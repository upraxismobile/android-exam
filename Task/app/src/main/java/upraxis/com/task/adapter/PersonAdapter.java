package upraxis.com.task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import upraxis.com.task.R;
import upraxis.com.task.person.model.Person;

/**
 * Created by rsbulanon on 6/19/17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> people;
    private OnSelectPersonListener onSelectPersonListener;

    public PersonAdapter(ArrayList<Person> people) {
        this.people = people;
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_person,
                parent, false);
        return new  ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_row)
        LinearLayout ll_row;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_contact)
        TextView tv_contact;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Person person = people.get(position);

        /** display first and last name */
        holder.tv_name.setText(person.getFirstName() + " " + person.getLastName());

        /** display mobile number */
        holder.tv_contact.setText(person.getMobileNumber());

        holder.ll_row.setOnClickListener(v -> {
            if (onSelectPersonListener != null) {
                onSelectPersonListener.onSelectedPerson(person);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface OnSelectPersonListener {
        void onSelectedPerson(final Person person);
    }

    public void setOnSelectPersonListener(OnSelectPersonListener onSelectPersonListener) {
        this.onSelectPersonListener = onSelectPersonListener;
    }
}
