package com.example.fref;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private final int LOCATION_REQUEST = 500;
    public GoogleMap mMap;
    ArrayList<LatLng> listpoints;
    Double la, lo;
    LocationManager locationManager;
    private Polyline currentPolyline;
    List<locat> list;
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listpoints = new ArrayList<>();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                LayoutInflater inflater= getLayoutInflater();
                View alertLayout=  inflater.inflate(R.layout.clayout,null);//clayout is layout_resource_file for custo dialog box
                TextView sh= alertLayout.findViewById(R.id.text);

                AlertDialog.Builder alert= new AlertDialog.Builder(this);
                //alert.setTitle("INFO");
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(MapsActivity.this,MainActivity2.class);
                        startActivity(intent);
                    }
                });

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

                    }
                });
                AlertDialog dialog =alert.create();
                dialog.show();
            }
           return;
            }
        else
        {

            mMap.setMyLocationEnabled(true);
            locate();
        }
       /* mMap.setMyLocationEnabled(true);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(MapsActivity.this, "OPEN GPS", Toast.LENGTH_LONG).show();

        } else {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this,
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
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //Toast.makeText(MapsActivity.this, Double.toString(la)+" ** ",Toast.LENGTH_LONG).show();
            //LatLng sydney2 = new LatLng(la, lo);
            LatLng sydney2 = new LatLng(la,lo);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(sydney2).title("START"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));
            LatLng destLoc= new LatLng(la-1,lo-2);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).position(destLoc).title("DEST"));

            //TO GET DIRECTION
            //String url= getRequestUrl(sydney2,destLoc);
            //TaskRequestDirections taskRequestDirections= new TaskRequestDirections();
            //taskRequestDirections.execute(url);

            //new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            //String url= getUrl(sydney2,destLoc,"driving");
           // new FetchURL(MapsActivity.this).execute(url,"diving");
        }*/
        }

        public void locate() {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MapsActivity.this, MainActivity2.class);
                startActivity(intent);
            }

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(MapsActivity.this, "OPEN GPS", Toast.LENGTH_LONG).show();

            } else {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this,
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
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //Toast.makeText(MapsActivity.this, Double.toString(la)+" ** ",Toast.LENGTH_LONG).show();
                //LatLng sydney2 = new LatLng(la, lo);
                LatLng sydney2 = new LatLng(la, lo);
                //mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(sydney2).title("START"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));
                LatLng destLoc = new LatLng(la - 1, lo - 2);
                //mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).position(destLoc).title("DEST"));

                //TO GET DIRECTION
                //String url= getRequestUrl(sydney2,destLoc);
                //TaskRequestDirections taskRequestDirections= new TaskRequestDirections();
                //taskRequestDirections.execute(url);

                //new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                //String url= getUrl(sydney2,destLoc,"driving");
                // new FetchURL(MapsActivity.this).execute(url,"diving");

                DatabaseReference df = FirebaseDatabase.getInstance().getReference("LOCATION");
                df.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        c = 0;
                        for (DataSnapshot p : dataSnapshot.getChildren()) {
                            locat md = p.getValue(locat.class);
                            //list.add(md);
                            //c++;

                            LatLng dest = new LatLng(md.getLat(),md.getLon());

                                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(dest).title(p.getKey()));



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(MapsActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                        // progressBar.setVisibility(View.INVISIBLE);
                    }
                });

                int i;
               // for( i=0;i<c;i++)
                //{
                  //  LatLng dest = new LatLng(list.get(i).getLat(), list.get(i).getLon());
                   // mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).position(dest).title("DEST"));
                //}

            }
        }


    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key="+getString(R.string.google_api_key);
        return url;
    }
/*    private String getRequestUrl(LatLng origin, LatLng destination) {

        String str_org= "origin="+origin.latitude+","+origin.longitude;
        String str_dest= "destination="+destination.latitude+","+destination.longitude;
        String sensor= "sensor=true";
        String mode= "mode=driving";
        String key= "AIzaSyClKXbphWYRtqZW90VrVrYV2ncU4CigLE8";
        String param= str_org+"&"+str_dest+"&"+sensor+"&"+mode;
        //String par= str_org+"&"+str_dest;
        String output= "json";
        String ur="https://maps.googleapis.com/maps/api/directions/"+output+"?"+param+"&key=AIzaSyClKXbphWYRtqZW90VrVrYV2ncU4CigLE8";
        return (ur);
        //https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=YOUR_API_KEY
    }

    public String requestDirection(String reqUrl)
    {
        String response= "";
        InputStream inputStream= null;
        HttpsURLConnection httpsURLConnection= null;
        try{
            URL url= new URL(reqUrl);
            httpsURLConnection= (HttpsURLConnection)url.openConnection();
            httpsURLConnection.connect();

            inputStream= httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer= new StringBuffer();
            String line= "";
            while ((line= bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            response= stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        }
        catch (Exception e)
        {
            Toast.makeText(MapsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }finally {
            if(inputStream!=null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpsURLConnection.disconnect();
        }
        return (response);
    }
*/
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case LOCATION_REQUEST:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //mMap.setMyLocationEnabled(true);
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);



        }
    }
    }

   @Override
    public void onTaskDone(Object... values) {

        if (currentPolyline != null){
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
    }
/*
    public class TaskRequestDirections extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String resp= "";
            resp = requestDirection(strings[0]);
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            TaskParse taskParse= new TaskParse();
            taskParse.execute(s);
        }
    }

    public class TaskParse extends AsyncTask<String, Void, List<List<HashMap<String, String>>>>
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject;
            List<List<HashMap<String, String>>> routes= null;
            try {
                jsonObject= new JSONObject(strings[0]);
                DataParser dataParser= new DataParser();
                routes= dataParser.parse(jsonObject);
                //Toast.makeText(getApplicationContext(),routes.toString(),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {

            ArrayList points;
            PolylineOptions polylineOptions= null;

            for(List<HashMap<String, String>> path: lists)
            {
               points= new ArrayList();
               polylineOptions= new PolylineOptions();

               for (HashMap<String, String> point: path)
               {
                   double lat= Double.parseDouble(point.get("lat"));
                   double lon= Double.parseDouble(point.get("lng"));
                   points.add(new LatLng(lat,lon));
               }
               polylineOptions.addAll(points);
               polylineOptions.width(5);
               polylineOptions.color(Color.BLUE);
               polylineOptions.geodesic(true);
            }

            if(polylineOptions!= null)
            {
                mMap.addPolyline(polylineOptions);
            }
            else
            {
                //Toast.makeText(getApplicationContext(),"NOT FOUND",Toast.LENGTH_LONG).show();
            }
        }
    }
*/
}

