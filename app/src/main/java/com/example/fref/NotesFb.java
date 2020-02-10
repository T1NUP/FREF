package com.example.fref;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesFb extends AppCompatActivity {

    Button start, next, pause;
    TextView tv;
    ImageView img;
    DatabaseReference databaseReference;
    MediaPlayer mediaPlayer = new MediaPlayer();
    Animation animation;
    String s,t;
    List<song> list;
    int c,i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_fb);

        //seek= findViewById(R.id.sek);
        start = findViewById(R.id.play);
        next = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        img= findViewById(R.id.ani);
        animation= AnimationUtils.loadAnimation(NotesFb.this,R.anim.blink_anim);
        //s="https://www.redringtones.com/wp-content/uploads/2016/10/harry-potter-ringtone.mp3";

        c=0;
        list= new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("MUSIC");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot p: dataSnapshot.getChildren())
                {
                    song md= p.getValue(song.class);
                    list.add(md);
                    c++;
                }
                //imageAdapter= new ImageAdapter(Images.this,list);

                //recyclerView.setAdapter(imageAdapter);
                //progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(NotesFb.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                //progressBar.setVisibility(View.INVISIBLE);
            }
        });



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //s="https://www.redringtones.com/wp-content/uploads/2016/10/harry-potter-ringtone.mp3";
                song a= list.get(0);
                s= a.getUrl();
                t= a.getName();
                tracks(s,t);

                //img= findViewById(R.id.ani);

                img.startAnimation(animation);
                //i.startAnimation(animation);

                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

            }
        });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //databaseReference= FirebaseDatabase.getInstance().getReference("MUSIC").child(Integer.toString(i));

                    pause.setText("PAUSE");
                    if(i<c) {
                        mediaPlayer.reset();
                        song a = list.get(i);
                        s = a.getUrl();
                        t = a.getName();
                        tracks(s, t);i++;
                        //img.clearAnimation();
                    }
                    else
                    {
                        tv= findViewById(R.id.name);
                        tv.setText("GOODBYE");
                        mediaPlayer.stop();
                        start.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.INVISIBLE);
                    }
                }


            });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        pause.setText("RESUME");
                        img.clearAnimation();
                    }
                    else {
                        mediaPlayer.start();
                        pause.setText("PAUSE");
                        img.startAnimation(animation);
                    }
                }
            });

            //Toast.makeText(NotesFb.this,Integer.toString(mediaPlayer.getDuration()),Toast.LENGTH_LONG).show();

                //Toast.makeText(NotesFb.this,Integer.toString(mediaPlayer.getDuration()),Toast.LENGTH_LONG).show();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(NotesFb.this,Integer.toString(mediaPlayer.getDuration()),Toast.LENGTH_LONG).show();
                    //s="https://ia800908.us.archive.org/12/items/tvtunes_20712/Game%20of%20Thrones.mp3";
                    if(i<c) {
                        song a = list.get(i);
                        s = a.getUrl();
                        t = a.getName();
                        i++;
                        mediaPlayer.reset();
                    /*try {
                        mediaPlayer.setDataSource(s);
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    */
                        tracks(s, t);
                    }
                    else
                    {
                        tv= findViewById(R.id.name);
                        tv.setText("GOODBYE");
                        mediaPlayer.stop();
                        start.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.INVISIBLE);

                    }

                }
            });

    }

    public void tracks(String m, String n)
    {
        try {
            tv= findViewById(R.id.name);
            tv.setText(n);
            mediaPlayer.setDataSource(m);
            //mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
