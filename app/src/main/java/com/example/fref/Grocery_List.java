package com.example.fref;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Grocery_List extends AppCompatActivity {

    Button add, del, show, showlst;
    EditText iitem, pprice, qquantity;
     String sitem, sprice, squantity;

     databaseHandler db= new databaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        //final databaseHandler db= new databaseHandler(this);

        add= findViewById(R.id.ad);
        iitem= findViewById(R.id.gi);
        show= findViewById(R.id.sh);
        pprice= findViewById(R.id.p);
        del= findViewById(R.id.dlt);
        qquantity= findViewById(R.id.qt);
        showlst= findViewById(R.id.sal);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    sitem= iitem.getText().toString().trim();
                    sprice= pprice.getText().toString();
                    squantity= qquantity.getText().toString();
                if((!sitem.equals(""))&&(!sprice.equals(""))&&(!squantity.equals("")))
                {
                    list k= new list(sitem,squantity,sprice);
                    //k.setItem(sitem);
                    //k.setQuantity(squantity);
                    //k.setPrice(sprice);
                    boolean f= db.addList(k);
                   // Toast.makeText(MainActivity.this,sitem,Toast.LENGTH_SHORT).show();
                    if (f==false)
                        Toast.makeText(Grocery_List.this,"NOT",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Grocery_List.this,"DONE",Toast.LENGTH_SHORT).show();
                }
                else
                   Toast.makeText(Grocery_List.this,"Please enter all fields",Toast.LENGTH_LONG).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sitem= iitem.getText().toString();
                if(!sitem.equals(""))
                {
                    // Reading all contacts
                    //Log.d("Reading: ", "Reading all contacts..");
                    List<list> contacts = db.getAllList();

                    String a,b;
                    b= sitem.trim();
                    for (list cn : contacts) {
                        //String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                                //cn.getPhoneNumber();
                        // Writing Contacts to log
                        //Log.d("Name: ", log);


                        a= cn.getItem();
                       // b= cn.getItem();
                        if(a.equals(b)) {
                           // Toast.makeText(Grocery_List.this,"Found",Toast.LENGTH_LONG).show();
                            show(cn.getItem(),cn.getQuantity(),cn.getPrice());
                        }

                    }
                    //Toast.makeText(MainActivity.this,"Find over!!",Toast.LENGTH_LONG).show();
                }

                else
                    Toast.makeText(Grocery_List.this,"Please enter Item name",Toast.LENGTH_LONG).show();

                /*Cursor res= db.getAll();
                if(res.getCount()!=0)
                {
                    //String d= res.getColumnName(1);
                    //Toast.makeText(MainActivity.this,d,Toast.LENGTH_LONG).show();
                    while ((res.moveToNext()))
                    {
                        String d= res.getString(1).toUpperCase();
                        Toast.makeText(MainActivity.this,d,Toast.LENGTH_LONG).show();

                    }
                }*/
            }
        });

        showlst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Grocery_List.this,Show_list.class);
                startActivity(intent);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sitem= iitem.getText().toString().trim();
                if(!((sitem).equals("")))
                {
                    db.deleteContact(sitem);
                    Toast.makeText(Grocery_List.this,"DELETED!",Toast.LENGTH_SHORT).show();
                }

            }
        });





        }
        public  void show(String it, String qt , String pr)
        {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(it);
            builder.setMessage("Quantity: "+qt+"\n"+"Price: "+pr);
            builder.show();
        }
    }

