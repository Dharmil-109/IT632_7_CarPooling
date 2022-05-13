package com.ds.carpooling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ds.carpooling.R;
import com.ds.carpooling.model.CarDetails;

import java.util.ArrayList;

public class ApproveAdapter extends RecyclerView.Adapter<ApproveAdapter.MyViewHolder> {

    Context context;
    ArrayList<CarDetails> list;

    public ApproveAdapter(Context context, ArrayList<CarDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.approve_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CarDetails carDetails = list.get(position);
        holder.modelNameField.setText(carDetails.getModelName());
        holder.carNumberField.setText(carDetails.getCarNumber());
        holder.liscenseNumberField.setText(carDetails.getLicenceNumber());
//        holder.liscenseImageField.setText(carDetails.getLicenceImg());

        Glide.with(context).load(list.get(position).getLicenceImg()).into(holder.licenceImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carNumberField, liscenseNumberField, modelNameField, liscenseImageField;
        ImageView licenceImg;
        //Button accept, decline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carNumberField = itemView.findViewById(R.id.tv_approve_carNumberField);
            liscenseNumberField = itemView.findViewById(R.id.tv_approve_liscenseNumberField);
            modelNameField = itemView.findViewById(R.id.tv_approve_modelNameField);
            licenceImg = itemView.findViewById(R.id.im_approve_licenceImg);
           /* accept = itemView.findViewById(R.id.btn_accept);
            decline = itemView.findViewById(R.id.btn_decline);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "It will take time", Toast.LENGTH_SHORT).show();
                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "It will take MORE time", Toast.LENGTH_SHORT).show();
                }
            });*/

        }
    }

}
