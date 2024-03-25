package com.example.collegephonebook;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private final ContactRepository repository;
    private final LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }


    public void insert(Contact contact) {
        repository.insert(contact);
    }

    // In ContactViewModel
    public void delete(Contact contact) {
        //Log.d("ContactViewModel", "Deleting contact: " + contact.getName());
        repository.delete(contact);
    }

}
