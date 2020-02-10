package com.example.fref;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ImageViewHolder>{


    public Context mContext;
    //public List<String> mname;//Object list of Modal class
   // public List<String> mpics;
    int t;
    public String ma[]= new String[100],mb[]= new String[100],chk;
    public int c;
    public PeopleAdapter(Context context, String uplod[], String muplod2[],int c1,String chk1) {
        mContext = context;
        c= c1;
        chk= chk1;
        int i1;
        for( i1=0;i1<=c1;i1++) {
            ma[i1] = uplod[i1];
            mb[i1] = muplod2[i1];
        }
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageview;
        private TextView textView;

        public ImageViewHolder(View imageView) {
            super(imageView);
            imageview = imageView.findViewById(R.id.img);
            textView = imageView.findViewById(R.id.pple);
        }
    }

    @NonNull
    @Override
    public PeopleAdapter.ImageViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.people, viewGroup, false);
        return new ImageViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.ImageViewHolder imageViewHolder, final int i) {

        String s1,s2;
        //s1= mname.get(i);
        //s2= mpics.get(i);
        s1= ma[i]; s2= mb[i];
        Picasso.get().load(s2).into(imageViewHolder.imageview);

        if(!(chk.equals(s1)))
        {
        imageViewHolder.textView.setText(s1);
        }
        else
        {
            imageViewHolder.textView.setText("ME");
        }


        imageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use position value  to get clicked data from list
                //Toast.makeText(mContext,ma[i],Toast.LENGTH_SHORT).show();

                Intent itn= new Intent(mContext,Send.class);
                itn.putExtra("ki",ma[i]);
                itn.putExtra("own",chk);
                mContext.startActivity(itn);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return mname.size();
        return c;
    }

}
