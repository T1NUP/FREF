package com.example.fref;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Map_intmed extends AppCompatActivity {

    String top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_intmed);

        Intent tp= getIntent();
         top= tp.getStringExtra("topic");
        Toast.makeText(Map_intmed.this,top,Toast.LENGTH_SHORT).show();

        DatabaseReference db= FirebaseDatabase.getInstance().getReference("NEWS");
        DatabaseReference cb= FirebaseDatabase.getInstance().getReference("MEMES");
        db.addValueEventListener(new ValueEventListener() {

            int i=0;
            TextView textView= findViewById(R.id.news);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    i++;
                    textView.setText("            "+ds.getValue().toString());

                    if(i==Integer.parseInt(top))
                    {i=0; break; }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                textView.setText("Unable to fetch!!");
            }
        });

        final ImageView imageView= findViewById(R.id.nwsimg);
        cb.addValueEventListener(new ValueEventListener() {

            int i=0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    i++;
                    String us = ds.getValue().toString();
                    Picasso.get().load(us).into(imageView);

                    if(i==Integer.parseInt(top))
                    {i=0; break; }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
