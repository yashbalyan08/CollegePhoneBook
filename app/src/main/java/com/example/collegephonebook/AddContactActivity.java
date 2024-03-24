package com.example.collegephonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class AddContactActivity extends AppCompatActivity {

    private EditText etName, etPhoneNumber;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        // Correct way to initialize ViewModel
        contactViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ContactViewModel.class);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setPhoneNumber(phoneNumber);
                    contactViewModel.insert(contact);
                    finish();
                } else {
                    Toast.makeText(AddContactActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
