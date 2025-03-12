package com.mini.fieldServices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewEntry extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_new_entry);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Logs");
                //get the values from the edit texts
                String productName = regName.getEditText().getText().toString();
                String weight = regUsername.getEditText().getText().toString();
                String harvestDate = regEmail.getEditText().getText().toString();
                String expiryDate= regPhoneNo.getEditText().getText().toString();
                String slotNo = regPassword.getEditText().getText().toString();
                //intializing the user helper class
                logHelperCLass pushData =new logHelperCLass(productName , weight,  harvestDate, expiryDate, slotNo);
                reference.child(productName).setValue(pushData);

                Toast.makeText(NewEntry.this, "New LOG updated successfully", Toast.LENGTH_SHORT).show();

                Intent login = new Intent(getApplicationContext(), technicianFeed.class);
                startActivity(login);
                finish();

            }
        });
    }
}
