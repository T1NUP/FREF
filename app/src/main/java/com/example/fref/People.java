package com.example.fref;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class People extends AppCompatActivity {


    List<String> n,u;
    String a[],b[];
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);



        recyclerView= findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference df = FirebaseDatabase.getInstance().getReference("LOCATION");
        df.addValueEventListener(new ValueEventListener() {
            
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int c=0;
                a= new String[100];
                b= new String[100];

                for (DataSnapshot p : dataSnapshot.getChildren()) {
                    locat md = p.getValue(locat.class);
                    //list.add(md);

                    //Toast.makeText(People.this,p.getKey(),Toast.LENGTH_LONG).show();
                    // n.add("1");
                    //u.add("2");
                    a[c]= p.getKey();b[c]= md.getUrl();
                    c++;

                }

                Intent r= getIntent();
                String ac= r.getStringExtra("y");
                PeopleAdapter peopleAdapter= new PeopleAdapter(People.this,a,b,c,ac);
                recyclerView.setAdapter(peopleAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(People.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                // progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
