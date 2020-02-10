package com.example.fref;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class Speech_intermdiate extends AppCompatActivity {

    EditText editText;
    Button button;
    private static final int REQ= 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech);

        button= findViewById(R.id.voice);
        editText= findViewById(R.id.text);
        editText.setVisibility(View.INVISIBLE);
        //ImageView view= findViewById(R.id.gif);
        //String us = p.getValue().toString();
        //Picasso.get().load("@res:raw/tenor.gif").into(view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    public void speak()
    {
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"SPEAK!!");

        try
        {
            startActivityForResult(intent,REQ);
        }
        catch(Exception e)
        {
            Toast.makeText(Speech_intermdiate.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //editText = findViewById(R.id.text);
                    //editText.setVisibility(View.VISIBLE);
                   // editText.setText(result.get(0));

                    Intent intent= new Intent(Speech_intermdiate.this,Google.class);
                    intent.putExtra("key",result.get(0));
                    startActivity(intent);

                }
                break;
            }
        }
    }
}
