package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class contactUs extends AppCompatActivity {
    TextInputEditText tiev_email, tiev_name,et_sub, et_msg;
    Button cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        tiev_name = (TextInputEditText) findViewById(R.id.name);
        tiev_email = (TextInputEditText) findViewById(R.id.mail);
        et_sub = (TextInputEditText) findViewById(R.id.subject);
        et_msg = (TextInputEditText) findViewById(R.id.msg);
        cv =(Button )findViewById(R.id.login_btn);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, email, sub, msg;

                name = tiev_name.getText().toString().trim();
                email = tiev_email.getText().toString().trim();
                sub = et_sub.getText().toString().trim();
                msg = et_msg.getText().toString().trim();

                String myMailId = "shikharprakash008@gmail.com";
                String[] emails = myMailId.split(","); //split not required since only one email id

                msg = msg + "\n\"" + "Email: " + email + "\"" + "\n\"" + "Name: " + name + "\"";

                Intent sendMail = new Intent(Intent.ACTION_SEND);


                sendMail.putExtra(Intent.EXTRA_EMAIL, emails);
                sendMail.putExtra(Intent.EXTRA_SUBJECT, sub);
                sendMail.putExtra(Intent.EXTRA_TEXT, msg);
                sendMail.setType("message/rfc822");
                sendMail.setPackage("com.google.android.gm");

                if(name.isEmpty()) {
                    Toast.makeText(contactUs.this, "Enter a valid name.", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()) {
                    Toast.makeText(contactUs.this, "Enter a valid email.", Toast.LENGTH_SHORT).show();
                }
                else if(sub.isEmpty()) {
                    Toast.makeText(contactUs.this, "Enter a valid subject.", Toast.LENGTH_SHORT).show();
                }
                else if(msg.isEmpty()) {
                    Toast.makeText(contactUs.this, "Enter message.", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(sendMail);
                }

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.contact);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.stock:
                        startActivity(new Intent(getApplicationContext(),stock.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contact:

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext(),feed.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
}
