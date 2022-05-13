package com.ds.carpooling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ds.carpooling.R;
import com.ds.carpooling.model.RequestRide;
import com.ds.carpooling.model.RequestRideClass;

import java.util.ArrayList;

public class RequestRideAdapter extends RecyclerView.Adapter<RequestRideAdapter.MyViewHolder> {

    final Context context;
    ArrayList<RequestRideClass> list;

    public RequestRideAdapter(Context context, ArrayList<RequestRideClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.request_ride_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestRideClass requestRideClass = list.get(position);
        holder.name.setText(requestRideClass.getName());
        holder.contact.setText(requestRideClass.getContact());
        holder.srcAdd.setText(requestRideClass.getSourceAddress());
        holder.destAdd.setText(requestRideClass.getDestinationAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, contact, srcAdd, destAdd;
        Button accept, decline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_item_nameField);
            contact = itemView.findViewById(R.id.tv_item_contactField);
            srcAdd = itemView.findViewById(R.id.tv_request_sourceAddField);
            destAdd = itemView.findViewById(R.id.tv_request_destinationAddField);

            accept = itemView.findViewById(R.id.btn_request_accept);
            decline = itemView.findViewById(R.id.btn_request_decline);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Accept", Toast.LENGTH_SHORT).show();
                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Decline", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}