package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class feed extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<FeedDataHelperClass> arrayList;
    private FirebaseRecyclerOptions<FeedDataHelperClass> options;
    private FirebaseRecyclerAdapter<FeedDataHelperClass,FirebaseViewHolder> adapter;
    private DatabaseReference database;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.feed);
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
                        startActivity(new Intent(getApplicationContext(),contactUs.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feed:

                        return true;


                }
                return false;
            }
        });
        //recycler view
        recyclerView=findViewById(R.id.customerListRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<FeedDataHelperClass>();
         database= FirebaseDatabase.getInstance().getReference().child("Logs");
         database.keepSynced(true);
         options=new FirebaseRecyclerOptions.Builder<FeedDataHelperClass>().setQuery(database,FeedDataHelperClass.class).build();

         adapter=new FirebaseRecyclerAdapter<FeedDataHelperClass, FirebaseViewHolder>(options) {
             @Override
             protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull FeedDataHelperClass feedDataHelperClass) {
                 firebaseViewHolder.name.setText(feedDataHelperClass.getName());
                 firebaseViewHolder.date.setText(feedDataHelperClass.getDate());
                 firebaseViewHolder.details.setText(feedDataHelperClass.getDetails());

                 firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                     }
                 });
             }

             @NonNull
             @Override
             public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 return new FirebaseViewHolder(LayoutInflater.from(feed.this).inflate(R.layout.feed_fragment,parent,false));
             }
         };

         recyclerView.setAdapter(adapter);

    }
}
