package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class stock extends AppCompatActivity {

    Button slotOne,slotTwo;

    TextView expiry,weight,expiryTwo,weightTwo;
    DatabaseReference ref_expi,ref_weight,refExpiryTwo,refWeight;

    LinearLayout slot_oneLayout,slot_twoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.stock);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.stock:

                        return true;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),contactUs.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
        slotOne=findViewById(R.id.bookSlotOne);
        slotTwo=findViewById(R.id.bookSlotTwo);
        slotOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(stock.this,stockUpdate.class);
                startActivity(i);
            }
        });
        slotTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(stock.this,stockUpdate.class);
                startActivity(j);
            }
        });
        //textview ids
        expiry=findViewById(R.id.expiry);
        weight=findViewById(R.id.weight);
        expiryTwo=findViewById(R.id.expiryTwo);
        weightTwo=findViewById(R.id.weightTwo);
        //linear layout ids
           slot_oneLayout=findViewById(R.id.slot_oneLayout);
           slot_twoLayout=findViewById(R.id.slot_twoLayout);
        //firebase retrive code for expiry date one
        ref_expi= FirebaseDatabase.getInstance().getReference("Stock").child("1").child("expiryDate");
        ref_expi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                expiry.setText(s);


                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                DateFormat df1 = new SimpleDateFormat("h:mm a");
                String date = df1.format(Calendar.getInstance().getTime());

                if (formattedDate.compareTo(s) >=0)
                {
                    expiry.setText("Product Expired");
                    slot_oneLayout.setBackgroundColor(Color.parseColor("#c62828"));
                        //Toast.makeText(stock.this, "Product expired", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    slot_twoLayout.setBackgroundColor(Color.parseColor("#fece2f"));
                  // Toast.makeText(stock.this, "Product in one s", Toast.LENGTH_SHORT).show();



                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //firebase retrive code fot weight one

        ref_weight=FirebaseDatabase.getInstance().getReference("Stock").child("1").child("weight");
        ref_weight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                weight.setText(s);
//                int stausOne=Integer.parseInt(s);
//                if(stausOne==0)
//                {
//                    expiry.setText("Not Available");
//                    bookingSlotOne.setVisibility(View.INVISIBLE);
//                }
//                else{
//                    status.setText("Available");
//                    bookingSlotOne.setVisibility(View.VISIBLE);
//                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //firebase retrive code for expiry two

        refExpiryTwo=FirebaseDatabase.getInstance().getReference("Stock").child("2").child("expiryDate");
        refExpiryTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                expiryTwo.setText(s);
//                int stausOne=Integer.parseInt(s);
//                if(stausOne==0)
//                {
//                    expiry.setText("Not Available");
//                    bookingSlotOne.setVisibility(View.INVISIBLE);
//                }
//                else{
//                    status.setText("Available");
//                    bookingSlotOne.setVisibility(View.VISIBLE);
//                }

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                DateFormat df1 = new SimpleDateFormat("h:mm a");
                String date = df1.format(Calendar.getInstance().getTime());

                if (formattedDate.compareTo(s) >0)
                {
                    expiryTwo.setText("Product Expired");
                    slot_twoLayout.setBackgroundColor(Color.parseColor("#c62828"));
                    //Toast.makeText(stock.this, "Working", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   // Toast.makeText(stock.this, "Not Working", Toast.LENGTH_SHORT).show();
                    slot_twoLayout.setBackgroundColor(Color.parseColor("#fece2f"));



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refWeight=FirebaseDatabase.getInstance().getReference("Stock").child("2").child("weight");
        refWeight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                weightTwo.setText(s);
//                int stausOne=Integer.parseInt(s);
//                if(stausOne==0)
//                {
//                    expiry.setText("Not Available");
//                    bookingSlotOne.setVisibility(View.INVISIBLE);
//                }
//                else{
//                    status.setText("Available");
//                    bookingSlotOne.setVisibility(View.VISIBLE);
//                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
