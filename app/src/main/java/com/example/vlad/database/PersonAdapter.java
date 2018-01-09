package com.example.vlad.database;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vlad on 1/6/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
//    private static String TAG = "FriendsAdapter";

private final ArrayList<Person> personArrayList;
private Person person;
private OnItemClickListener<Person> onItemClickListener;


public PersonAdapter() {
        this.personArrayList = new ArrayList<Person>();
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, null);

        return new ViewHolder(v);
        }

@Override
public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
final Person p = personArrayList.get(position);
        viewHolder.tvId.setText(p.getId() + "");
        viewHolder.tvName.setText(p.getFirstName() + " " + p.getLastName());
        viewHolder.tvAge.setText(p.getAge() + "");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (onItemClickListener != null)
        onItemClickListener.onItemClick(position, p);
        }
        });
        }

public void setOnItemClickListener(OnItemClickListener<Person> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        }

//    public Person getPerson() {
//        return person;
//    }

@Override
public int getItemCount() {
        return personArrayList.size();
        }

public void updateList(ArrayList<Person> list) {
        this.personArrayList.clear();
        this.personArrayList.addAll(list);
        this.notifyDataSetChanged();
        }

public interface OnItemClickListener<Person> {
    void onItemClick(int position, Person item);
}

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;
    private TextView tvAge;

    public ViewHolder(View v) {
        super(v);
        tvId   = (TextView) v.findViewById(R.id.tvId);
        tvName = (TextView) v.findViewById(R.id.tvName);
        tvAge  = (TextView) v.findViewById(R.id.tvAge);
    }
}
}
