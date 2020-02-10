package com.example.fref;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_list extends AppCompatActivity {

    databaseHandler db = new databaseHandler(this);
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //databaseHandler db= new databaseHandler(this);
        setContentView(R.layout.item_list);
        listView = findViewById(R.id.lv);
        ArrayList<String> ar = new ArrayList<>();
        Button button = findViewById(R.id.dlt);
        Cursor res = db.getAll();
        if (res.getCount() != 0) {
            //String d= res.getColumnName(1);
            //Toast.makeText(MainActivity.this,d,Toast.LENGTH_LONG).show();
            while ((res.moveToNext())) {
                String d3, d2, d1 = res.getString(1).toUpperCase();
                d2 = res.getString(2);
                d3 = res.getString(3);
                ar.add("ITEM=  " + d1 + "\n\nQUANTITY= " + d2 + "\nPRICE= Rs" + d3 + "/-\n");


                //Toast.makeText(MainActivity.this,d,Toast.LENGTH_LONG).show();

                //show(res.getString(1),res.getString(2),res.getString(3));
            }
            ArrayAdapter ad = new ArrayAdapter(Show_list.this, android.R.layout.simple_list_item_1, ar);

            listView.setAdapter(ad);

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //db.onDlt();
                //listView.setAdapter(null);
                show();
            }
        });
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("WARNING");
        if (!(listView.equals(null))) {
            builder.setMessage("All items will be deleted!");
            builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.onDlt();
                    //listView= null;
                    listView.setAdapter(null);
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Show_list.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
        else {
            builder.setMessage("NO ITEMS TO DELETE!!");
            builder.show();
        }
    }
}

