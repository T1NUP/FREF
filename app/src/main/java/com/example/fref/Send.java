package com.example.fref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Send extends AppCompatActivity {

    EditText et;
    Button snd;
    DatabaseReference m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);


        Intent e= getIntent();
        final String recipent= e.getStringExtra("ki");
        final String on= e.getStringExtra("own");
        et= findViewById(R.id.et);
        snd= findViewById(R.id.pus);
        snd.setText("Send to "+recipent);

        m= FirebaseDatabase.getInstance().getReference().child("MESSAGE");

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(et.getText()).equals("")) {
                    String sh,s = DateFormat.getDateTimeInstance().format(new Date());
                    //sh= s.substring(0,12);
                    temp ob = new temp(on,s,et.getText().toString());//recipent should contain from where come
                    String upl = m.push().getKey();
                    m.child(recipent).child(upl).setValue(ob);
                }
            }
        });

    }
}
