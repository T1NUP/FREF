package com.example.fref;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    Context mContext;
    public List<temp> mupload;//Object list of Modal class
    public MessageAdapter(Context context, List<temp> uplod) {
        mContext = context;
        mupload = uplod;

    }
    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView msg,info;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.ms);
            info = itemView.findViewById(R.id.inf);
        }
    }


    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.mess, viewGroup, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder messageViewHolder, int i) {

        temp md = mupload.get(i);
        messageViewHolder.msg.setText(md.getMsg());
        messageViewHolder.info.setText("From: "+md.getName()+"\n"+md.getTime());
    }

    @Override
    public int getItemCount() {
        return mupload.size();
    }
}
