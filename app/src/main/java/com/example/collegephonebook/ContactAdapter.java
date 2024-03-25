package com.example.collegephonebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private static final String TAG = "ContactAdapter";
    private List<Contact> contacts;
    private Context context;
    private ContactViewModel contactViewModel;

    public ContactAdapter(Context context, List<Contact> contacts, ContactViewModel contactViewModel) {
        this.context = context;
        this.contacts = contacts;
        this.contactViewModel = contactViewModel;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhoneNumber());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private void deleteContact(Contact contact) {
        Log.d(TAG, "Deleting contact: " + contact.getName());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete " + contact.getName())
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactViewModel.delete(contact);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhoneNumber;
        Button btnDelete;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
