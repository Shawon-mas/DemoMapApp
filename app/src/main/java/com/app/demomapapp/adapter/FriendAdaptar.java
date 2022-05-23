package com.app.demomapapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.demomapapp.R;
import com.app.demomapapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class FriendAdaptar extends RecyclerView.Adapter<FriendAdaptar.MyViewHOlder> {
    private Context context;
  private List<User> users;

    public FriendAdaptar(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.friend_list_item,parent,false);
        return new MyViewHOlder(view);

        /*
        View view= LayoutInflater.from(context).inflate(R.layout.receivedlist_item,parent,false);
        return new MyViewHolder(view);
         */
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder holder, int position) {
        User user=users.get(position);
        holder.textView_name.setText(user.getUserName());
        holder.textView_address.setText(user.getUserAddress());
        holder.textView_phoneNumber.setText(String.valueOf("0"+user.getUid()));
        holder.textView_latitude.setText(String.valueOf("Lat:"+user.getUserLatitude()));
        holder.textView_longitude.setText(String.valueOf("Lat:"+user.getUserLongitude()));


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder{
        TextView textView_name,textView_address,textView_phoneNumber,textView_latitude,textView_longitude;

        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.userName);
            textView_address=itemView.findViewById(R.id.userAddress);
            textView_phoneNumber=itemView.findViewById(R.id.userNumber);
            textView_latitude=itemView.findViewById(R.id.itemLatitude);
            textView_longitude=itemView.findViewById(R.id.itemLongitude);

        }
    }
}
