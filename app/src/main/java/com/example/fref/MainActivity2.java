package com.example.fref;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    Button signin, speech ;
    private static final int RC_SIGN_IN = 123;
    private final int LOCATION_REQUEST = 500;
    int flag=0;int vi=0;
    TextView tv, quote, inf, onBut;
    ImageView c, al;
    EditText ed, ed2;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth firebaseAuth;
    Double la = 0.00, lo = 0.00;
    Animation ani;
    String nme;
    String own;
    private static final int REQ = 1000;
    ImageView img,img2,img3,img4,img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signin = findViewById(R.id.login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        signIn();
                        break;
                    // ...
                }
            }
        });


    }

    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }

        if (requestCode == REQ) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //editText = findViewById(R.id.text);
                // editText.setVisibility(View.VISIBLE);
                // editText.setText(result.get(0));


                String s = result.get(0), su;
                int l = s.length(), i, f = 0;
                for (i = 0; i <= (l - 3); i++) {
                    su = s.substring(i, i + 3);
                    if (su.equals("sad")) {
                        Toast.makeText(MainActivity2.this, "sad", Toast.LENGTH_LONG).show();
                        f = 1;
                    }
                }
                for (i = 0; i <= (l - 5); i++) {
                    su = s.substring(i, i + 5);
                    if (su.equals("happy")) {
                        Toast.makeText(MainActivity2.this, "happy", Toast.LENGTH_LONG).show();
                        f = 2;
                    }
                }
                for (i = 0; i <= (l - 4); i++) {
                    su = s.substring(i, i + 4);
                    if (su.equals("fine")) {
                        Toast.makeText(MainActivity2.this, "fine", Toast.LENGTH_LONG).show();
                        f = 3;
                    }
                }
                for (i = 0; i <= (l - 3); i++) {
                    su = s.substring(i, i + 3);
                    if (su.equals("cry")) {
                        Toast.makeText(MainActivity2.this, "cry", Toast.LENGTH_LONG).show();
                        f = 4;
                    }
                }
                for (i = 0; i <= (l - 6); i++) {
                    su = s.substring(i, i + 6);
                    if (su.equals("lonely")) {
                        Toast.makeText(MainActivity2.this, "lonely", Toast.LENGTH_LONG).show();
                        f = 5;
                    }
                }
                for (i = 0; i <= (l - 6); i++) {
                    su = s.substring(i, i + 6);
                    if (su.equals("guilty")) {
                        Toast.makeText(MainActivity2.this, "guilty", Toast.LENGTH_LONG).show();
                        f = 6;
                    }
                }
                for (i = 0; i <= (l - 6); i++) {
                    su = s.substring(i, i + 6);
                    if (su.equals("relief")) {
                        Toast.makeText(MainActivity2.this, "relief", Toast.LENGTH_LONG).show();
                        f = 7;
                    }
                }
                for (i = 0; i <= (l - 5); i++) {
                    su = s.substring(i, i + 5);
                    if (su.equals("relax")) {
                        Toast.makeText(MainActivity2.this, "relax", Toast.LENGTH_LONG).show();
                        f = 8;
                    }
                }
                for (i = 0; i <= (l - 7); i++) {
                    su = s.substring(i, i + 7);
                    if (su.equals("excited")) {
                        Toast.makeText(MainActivity2.this, "excited", Toast.LENGTH_LONG).show();
                        f = 9;
                    }
                }
                for (i = 0; i <= (l - 9); i++) {
                    su = s.substring(i, i + 9);
                    if (su.equals("embarrass")) {
                        Toast.makeText(MainActivity2.this, "embarrass", Toast.LENGTH_LONG).show();
                        f = 10;
                    }
                }
                for (i = 0; i <= (l - 5); i++) {
                    su = s.substring(i, i + 5);
                    if (su.equals("proud")) {
                        Toast.makeText(MainActivity2.this, "proud", Toast.LENGTH_LONG).show();
                        f = 11;
                    }
                }
                for (i = 0; i <= (l - 4); i++) {
                    su = s.substring(i, i + 4);
                    if (su.equals("fear")) {
                        Toast.makeText(MainActivity2.this, "fear", Toast.LENGTH_LONG).show();
                        f = 12;
                    }
                }
                for (i = 0; i <= (l - 7); i++) {
                    su = s.substring(i, i + 7);
                    if (su.equals("disgust")) {
                        Toast.makeText(MainActivity2.this, "disgust", Toast.LENGTH_LONG).show();
                        f = 13;
                    }
                }
                for (i = 0; i <= (l - 5); i++) {
                    su = s.substring(i, i + 5);
                    if (su.equals("angry")) {
                        Toast.makeText(MainActivity2.this, "angry", Toast.LENGTH_LONG).show();
                        f = 14;
                    }
                }
                for (i = 0; i <= (l - 9); i++) {
                    su = s.substring(i, i + 9);
                    if (su.equals("satisfied")) {
                        Toast.makeText(MainActivity2.this, "satisfied", Toast.LENGTH_LONG).show();
                        f = 15;
                    }
                }
                String fs = Integer.toString(f);
                list = new ArrayList<>();
                DatabaseReference databaseReference, dbloc;
                databaseReference = FirebaseDatabase.getInstance().getReference(fs);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        quote = findViewById(R.id.quotes);
                        for (DataSnapshot p : dataSnapshot.getChildren()) {
                            //Memes md= p.getValue(Memes.class);
                            //list.add(md);

                            String us = p.getValue().toString();
                            Animation animate2 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.lefttoright);
                            //quote.setText(us);
                            //quote.startAnimation(animate2);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(MainActivity2.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });


            }
        }
    }
    int f=0;

    CardView cv;
    void tk(){

         ani=  AnimationUtils.loadAnimation(MainActivity2.this,R.anim.rotate);


    String fs = Integer.toString(f++);
    list=new ArrayList<>();
    DatabaseReference databaseReference, dbloc;
    databaseReference=FirebaseDatabase.getInstance().

    getReference(fs);
                databaseReference.addValueEventListener(new

    ValueEventListener() {
        @Override
        public void onDataChange (@NonNull DataSnapshot dataSnapshot){

            quote = findViewById(R.id.quotes);

            //cv.startAnimation(ani);
            for (DataSnapshot p : dataSnapshot.getChildren()) {
                //Memes md= p.getValue(Memes.class);
                //list.add(md);
                //for(int u=0;u<20;u++)
                //quote.setText(" ");
                String us = p.getValue().toString();
                Animation animate2 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.lefttoright);
                quote.setText(us);
                quote.startAnimation(animate2);

            }
        }
        @Override
        public void onCancelled (@NonNull DatabaseError databaseError){

            Toast.makeText(MainActivity2.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            //progressBar.setVisibility(View.INVISIBLE);
        }
    });

}




    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
           GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);

            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            //updateUI(null);
        }
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());


        /*final GoogleSignInAccount account=acct;
        firebaseAuth=  FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity2.this,"Successful",Toast.LENGTH_SHORT).show();
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
          */                  updateUI(acct);
            /*            } else {
                            Toast.makeText(MainActivity2.this,"Unsuccessful",Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });*/
    }


    @Override
    public void onStart() {
        super.onStart();
        //say();
        // Check if user is signed in (non-null) and update UI accordingly.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
            updateUI(account);
        else {
            final TextView textView1 = findViewById(R.id.help),textView,textView2,textView3;
            textView2= findViewById(R.id.help2);
            textView3= findViewById(R.id.help3);
            textView= findViewById(R.id.mov);
            textView.setVisibility(View.INVISIBLE);
            textView1.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            Handler handler = new Handler();//shows progress bar to 100% after success
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView1.setVisibility(View.VISIBLE);Animation animato;
                    animato= AnimationUtils.loadAnimation(MainActivity2.this,R.anim.lefttoright);
                    textView1.startAnimation(animato);
                }
            }, 500);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView2.setVisibility(View.VISIBLE);

                }
            }, 2000);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView3.setVisibility(View.VISIBLE);
                    Animation nimato;
                    nimato= AnimationUtils.loadAnimation(MainActivity2.this,R.anim.lefttoright);
                    textView3.startAnimation(nimato);
                }
            }, 3500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animatio;
                    textView.setVisibility(View.VISIBLE);
                    animatio= AnimationUtils.loadAnimation(MainActivity2.this,R.anim.blink_anim);
                    textView.startAnimation(animatio);
                }
            }, 5000);

        }
    }


    List<Memes> list;
    private void updateUI(final GoogleSignInAccount account) {

        setContentView(R.layout.activity_main2);

        NavigationView navigationView;

        //voice();
        if (ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            LayoutInflater inflater= getLayoutInflater();
            View alertLayout=  inflater.inflate(R.layout.clayout,null);//clayout is layout_resource_file for custo dialog box
            TextView sh= alertLayout.findViewById(R.id.text);

            AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity2.this);
            //alert.setTitle("INFO");
            alert.setView(alertLayout);
            alert.setCancelable(false);
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity2.this, "APP is INACTIVE", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity2.this, "APP is INACTIVE", Toast.LENGTH_SHORT).show();
                }
            });

            alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

                }
            });
            AlertDialog dialog =alert.create();
            dialog.show();
        }

        if (!(ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            LocationManager locationManager;

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(MainActivity2.this, "OPEN GPS", Toast.LENGTH_LONG).show();

            } else {
                if (ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity2.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Location ln = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Location lp = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                    if (l != null) {
                        la = l.getLatitude();
                        lo = l.getLongitude();
                        //String s= Double.toString(lat);
                        //String t= Double.toString(lon);
                        double ac = l.getAccuracy();
                        double al = l.getAltitude();
                        String g = l.getProvider();
                        String sc = Double.toString(ac);
                        String sl = Double.toString(al);
                        //gpss.setText("La: "+s+"\n"+"Lo: "+t+"\nAlt: "+sl+"\nAccu.: "+sc+"\nProvider: "+g);
                        //textView.setText("La: "+s+"\n"+"Lo: "+t);
                    } else if (ln != null) {
                        la = ln.getLatitude();
                        lo = ln.getLongitude();
                        double ac = ln.getAccuracy();
                        double al = ln.getAltitude();
                        String g = ln.getProvider();
                        String sc = Double.toString(ac);
                        String sl = Double.toString(al);
                        //String s= Double.toString(lat);
                        //String t= Double.toString(lon);
                        //textView.setText("La: "+s+"\n"+"Lo: "+t+"\nAlt: "+sl+"\nAccu.: "+sc+"\nProvider: "+g);
                    } else if (lp != null) {

                        la = lp.getLatitude();
                        lo = lp.getLongitude();
                        double ac = lp.getAccuracy();
                        double al = lp.getAltitude();
                        String g = lp.getProvider();
                        String sc = Double.toString(ac);
                        String sl = Double.toString(al);
                        //String s= Double.toString(lat);
                        //String t= Double.toString(lon);
                        //textView.setText("La: "+s+"\n"+"Lo: "+t+"\nAlt: "+sl+"\nAccu.: "+sc+"\nProvider: "+g);
                        //textView.setText("La: "+s+"\n"+"Lo: "+t);
                    }
                }


                nme=   account.getDisplayName();
                //onBut= findViewById(R.id.online);
                //onBut.setVisibility(View.INVISIBLE);
                final DatabaseReference dbloc = FirebaseDatabase.getInstance().getReference("LOCATION");
                //locat ob = new locat(la, lo, account.getPhotoUrl().toString());
                //String upl = dbloc.push().getKey();
                dbloc.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        flag=0;
                        for (DataSnapshot p : dataSnapshot.getChildren()) {
                            if (p.getKey().equals(account.getDisplayName())) ;
                            {
                                flag = 0;
                                break;
                            }
                        }

                        if(flag==1) {
                            dbloc.child(nme).setValue(null);
                            //dbloc.setValue(null);
                            //flag = 1;
                          //  onBut.setText("ONLINE");
                        } else {
                            locat ob= new locat(la,lo,nme);
                            //dbloc.child(nme).setValue(ob);
                            dbloc.child(account.getDisplayName()).setValue(ob);
                            //flag = 0;
                           // onBut.setText("OFFLINE");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

                //dbloc.child(upl).setValue(m);

            }

        }

        /*onBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dblo = FirebaseDatabase.getInstance().getReference("LOCATION");
                dblo.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        flag=0;
                        for (DataSnapshot p: dataSnapshot.getChildren())
                        {
                            if(p.getKey().equals(account.getDisplayName()));
                            {flag=1;
                            break;}
                        }

                        if(flag==1) {
                            dblo.child(nme).setValue(null);
                            //dbloc.setValue(null);
                            //flag = 1;
                            onBut.setText("ONLINE");
                        }
                        else
                        {
                            locat ob= new locat(la,lo,nme);
                            dblo.child(nme).setValue(ob);
                            //flag=0;
                            onBut.setText("OFFLINE");
                        }
                        flag=0;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
*/
        //String str= account.getDisplayName();
        list= new ArrayList<>();
        DatabaseReference databaseReference, dbloc,dot;
        databaseReference= FirebaseDatabase.getInstance().getReference("MEMES");
        dot= FirebaseDatabase.getInstance().getReference("title");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                img= findViewById(R.id.meme);
                img2= findViewById(R.id.meme2);
                img3= findViewById(R.id.meme3);
                img4= findViewById(R.id.meme4);
                img5= findViewById(R.id.meme5);
                int c=0;
                for (DataSnapshot p: dataSnapshot.getChildren())
                {
                    //Memes md= p.getValue(Memes.class);
                    //list.add(md);
                    switch(c) {
                        case 0:
                        if (p.getValue() != null) {
                            String us = p.getValue().toString();
                            Picasso.get().load(us).into(img);c++;
                        }
                        break;
                        case 1:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                Picasso.get().load(us).into(img2);c++;
                            }
                            break;
                        case 2:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                Picasso.get().load(us).into(img3);c++;
                            }
                            break;
                        case 3:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                Picasso.get().load(us).into(img4);c++;
                            }
                            break;
                        case 4:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                Picasso.get().load(us).into(img5);c++;
                            }
                            break;

                    }
                }
                //imageAdapter= new ImageAdapter(MediaStore.Images.this,list);
                //recyclerView.setAdapter(imageAdapter);
                //progressBar.setVisibility(View.INVISIBLE);
                //ImageView img= findViewById(R.id.meme);
                //Memes upld= list.get(0);
                //Picasso.get().load(upld.getUrl()).into(img);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity2.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                //progressBar.setVisibility(View.INVISIBLE);
            }
        });

        dot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TextView im, im2,im3,im4,im5;
                im= findViewById(R.id.line1);
                im2= findViewById(R.id.line2);
                im3= findViewById(R.id.line3);
                im4= findViewById(R.id.line4);
                im5= findViewById(R.id.line5);
                int c=0;
                for (DataSnapshot p: dataSnapshot.getChildren())
                {
                    //Memes md= p.getValue(Memes.class);
                    //list.add(md);
                    switch(c) {
                        case 0:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                im.setText(us);
                                c++;
                            }
                            break;
                        case 1:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                im2.setText(us);
                                c++;
                            }
                            break;
                        case 2:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                im3.setText(us);
                                c++;
                            }
                            break;
                        case 3:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                im4.setText(us);
                                c++;
                            }
                            break;
                        case 4:
                            if (p.getValue() != null) {
                                String us = p.getValue().toString();
                                im5.setText(us);
                                c++;
                            }
                            break;

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        CardView c1,c2,c3,c4,c5;
        c1= findViewById(R.id.c5);
        c2= findViewById(R.id.c4);
        c3= findViewById(R.id.c3);
        c4= findViewById(R.id.c2);
        c5= findViewById(R.id.c1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(MainActivity2.this,Map_intmed.class);
                i1.putExtra("topic","1");
                startActivity(i1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(MainActivity2.this,Map_intmed.class);
                i2.putExtra("topic","2");
                startActivity(i2);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3= new Intent(MainActivity2.this,Map_intmed.class);
                i3.putExtra("topic","3");
                startActivity(i3);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4= new Intent(MainActivity2.this,Map_intmed.class);
                i4.putExtra("topic","4");
                startActivity(i4);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5= new Intent(MainActivity2.this,Map_intmed.class);
                i5.putExtra("topic","5");
                startActivity(i5);
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle;
        setupToolbar();
        navigationView= findViewById(R.id.nav);

        //ImageView iv= findViewById(R.id.nav);
        View navView =  navigationView.getHeaderView(0);
//reference to views
        ImageView iv = navView.findViewById(R.id.test);
        tv= navView.findViewById(R.id.hello);

        tv.setText("Welcome\n"+account.getDisplayName());
        Uri u= account.getPhotoUrl();
        String sh,s = DateFormat.getDateTimeInstance().format(new Date());
        sh= s.substring(0,12);
        TextView dt= navView.findViewById(R.id.dat);
        dt.setText(sh);

        //ImageView im= findViewById(R.id.imageView);
        //im.setImageURI(Uri.parse(us));

        if(u!=null)
        {
            String us= u.toString();
            Picasso.get().load(us).fit().into(iv);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.search:
                        Intent it= new Intent(MainActivity2.this,Speech_intermdiate.class);
                        startActivity(it);
                        break;
                    case R.id.findfrnd:
                        //ToDo here map today
                        Intent itnq= new Intent(MainActivity2.this,MapsActivity.class);
                        startActivity(itnq);
                        break;
                    case R.id.grocery:
                        Intent itn= new Intent(MainActivity2.this,Grocery_List.class);
                        startActivity(itn);
                        break;
                    case R.id.notes:
                        Intent it1= new Intent(MainActivity2.this,NotesFb.class);
                        startActivity(it1);
                        break;
                    case R.id.aboutUs:
                        Intent itt= new Intent(Intent.ACTION_DIAL);
                        itt.setData(Uri.parse("tel:9504959745"));
                        startActivity(itt);
                        break;
                    case R.id.policy:
                        Uri uri = Uri.parse("https://github.com/T1NUP/");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                        likeIng.setPackage("com.github.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/T1NUP/")));
                        }
                        break;
                    case R.id.frndon:
                        Intent tent= new Intent(MainActivity2.this,People.class);
                        tent.putExtra("y",account.getDisplayName());
                        startActivity(tent);
                        break;
                    case R.id.help:
                    //TODO
                }


                return false;
            }
        });

        Animation animate=  AnimationUtils.loadAnimation(MainActivity2.this,R.anim.lefttoright);
        speech= findViewById(R.id.speak);
        speech.startAnimation(animate);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inb= new Intent(MainActivity2.this,Inbox.class);
                inb.putExtra("id",account.getDisplayName());
                startActivity(inb);
            }
        });

        cv= findViewById(R.id.qt);

        tk();
    }

    private void voice() {

        /*
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
            Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        */




    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //editText = findViewById(R.id.text);
                   // editText.setVisibility(View.VISIBLE);
                   // editText.setText(result.get(0));

                }
                break;
            }
        }
    }*/

    private void logout()
    {

        FirebaseAuth.getInstance().signOut();
        finish();
    }

    private void setupToolbar()
    {
        DrawerLayout drawerLayout;
        Toolbar t;
        ActionBarDrawerToggle actionBarDrawerToggle;
        drawerLayout= findViewById(R.id.drw);
        t= findViewById(R.id.toolbar);
        setSupportActionBar(t);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout, t,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    public void tic()
    {
        new CountDownTimer(90000, 1000) {
            public void onTick(long millisUntilFinished) {
                tk();
            }
            @Override
            public void onFinish() {
       tic();
                //start();
            }
        }.start();
           }

}
