package com.example.fref;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Inbox extends AppCompatActivity {

    List<temp> list= new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent it= getIntent();
        String s= it.getStringExtra("id");
        DatabaseReference d= FirebaseDatabase.getInstance().getReference("MESSAGE").child(s);
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot p: dataSnapshot.getChildren())
                {
                    temp ob= p.getValue(temp.class);
                    list.add(ob);
                    //Toast.makeText(Inbox.this,ob.getMsg(),Toast.LENGTH_SHORT).show();
                }

                MessageAdapter adapter = new MessageAdapter(Inbox.this, list);
                recyclerView.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
