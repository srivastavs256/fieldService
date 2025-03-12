package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Home extends AppCompatActivity {

    TextView temp;
    CardView cardView;
    LinearLayout linearLayout ,linearlayoutTwo ;
     TextView errortext,errorText2;
     TextView humidity;
     String email;


    int count =0;
    String sEmail,sPassword,subject,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.stock:
                        startActivity(new Intent(getApplicationContext(),stock.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),contactUs.class));
                        overridePendingTransition(0,0);
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

        temp=findViewById(R.id.temp);
        cardView=findViewById(R.id.slotOne);
        linearLayout=findViewById(R.id.layout_main);
        errortext=findViewById(R.id.errorText);
        //sender email credentials
        subject="Malfunction in cooling Device";
//        email="shikharprakash005@gmail.com";
        details="There is been some issue in the cooling device ,Technician is inform to look in the problem As Soon As Possible and update the log on the Mobile application";

        humidity=findViewById(R.id.humidity);
        linearlayoutTwo=findViewById(R.id.layout_main2);
        errorText2=findViewById(R.id.errorText2);
        cardView=findViewById(R.id.slottwo);


        DatabaseReference refTemp,refHumidity;
        refTemp= FirebaseDatabase.getInstance().getReference("temp").child("value");
        refTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                temp.setText(s);
                float temps=Float.parseFloat(s);

                if(temps>30.0)
                {

                     final DatabaseReference rEmail;
                     rEmail =FirebaseDatabase.getInstance().getReference("Technician").child("shik").child("phoneNo");

                     rEmail.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             String email =dataSnapshot.getValue().toString();
                             JavaMailAPI javaMailAPI=new JavaMailAPI(this,email,subject,details);
                             javaMailAPI.execute();
                             sendSMS(dataSnapshot.getValue().toString(), details);
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

                    linearLayout.setBackgroundColor(Color.parseColor("#c62828"));
                    errortext.setText("Malfunction in cooling device");
                   // Toast.makeText(Home.this, "Malfunction in cooling device", Toast.LENGTH_SHORT).show();
                }
                else if(temps>=25.0 && temps<= 30.0){
                    errortext.setText("Temperature under control");
                    linearLayout.setBackgroundColor(Color.parseColor("#fece2f"));
                }
                else {

                    errortext.setText("");
                    linearLayout.setBackgroundColor(Color.parseColor("#4caf50"));

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       refHumidity=FirebaseDatabase.getInstance().getReference("humidity").child("value");
       refHumidity.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String humid = dataSnapshot.getValue().toString();

               humidity.setText(humid);
               float temps=Float.parseFloat(humid);
               if (temps>=50){
                   linearlayoutTwo.setBackgroundColor(Color.parseColor("#c62828"));
                   errorText2.setText("Humidity increasing.");
               }
                 else if(temps>=25.0 && temps<= 29.0){
                   errorText2.setText("Humidity under control");
                   linearlayoutTwo.setBackgroundColor(Color.parseColor("#fece2f"));
               }
               else {

                   errorText2.setText("Humidity under control");
                   linearlayoutTwo.setBackgroundColor(Color.parseColor("#4caf50"));

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }



}
