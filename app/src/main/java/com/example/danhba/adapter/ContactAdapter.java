package com.example.danhba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danhba.OnItemClickListener;
import com.example.danhba.R;
import com.example.danhba.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Contact> arrContact;
    private OnItemClickListener listener ;

    public ContactAdapter(Context context, ArrayList<Contact> arrContact, OnItemClickListener listener) {
        this.context = context;
        this.arrContact = arrContact;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_contact_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = arrContact.get(position);
        holder.tv_name.setText(contact.getmName());
        holder.tv_number.setText(contact.getmNumber());
        if (contact.isMale()) {
            holder.img_avatar.setImageResource(R.drawable.man);
        }
        else{
            holder.img_avatar.setImageResource(R.drawable.woman);
        }
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_avatar;
        private TextView tv_name;
        private TextView tv_number;
        private Contact contact ;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(contact);
                }
            });
            img_avatar = itemView.findViewById(R.id.img_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_number = itemView.findViewById(R.id.tv_number);
        }
    }
}
