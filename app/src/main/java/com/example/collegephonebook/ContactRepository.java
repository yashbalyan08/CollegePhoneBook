package com.example.collegephonebook;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {

    private final ContactDao contactDao;
    private final LiveData<List<Contact>> allContacts;
    private final ExecutorService executorService;

    public ContactRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        contactDao = database.contactDao();
        allContacts = contactDao.getAllContacts();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void insert(Contact contact) {
        executorService.execute(() -> {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                contactDao.insert(contact);
            });
        });
    }

    public void delete(Contact contact) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
        //executorService.execute(() -> {
            //Log.d("ContactRepository", "Deleting contact: " + contact.getName());
            contactDao.delete(contact);
        });
    }

}
