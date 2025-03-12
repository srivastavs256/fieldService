package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class technicianFeed extends AppCompatActivity {
    Button update;
    RecyclerView recyclerView;
    DatabaseReference reference;
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
        setContentView(R.layout.activity_technician_feed);

        update=findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(technicianFeed.this,NewEntry.class);
                startActivity(intent);
                finish();
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
                return new FirebaseViewHolder(LayoutInflater.from(technicianFeed.this).inflate(R.layout.feed_fragment,parent,false));
            }
        };

        recyclerView.setAdapter(adapter);


    }
}
