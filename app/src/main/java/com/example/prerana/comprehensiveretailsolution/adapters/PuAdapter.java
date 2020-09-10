package com.example.prerana.comprehensiveretailsolution.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.model.Purchases;

import java.util.List;

public class PuAdapter extends RecyclerView.Adapter<PuAdapter.MyViewHolder> {

    Context context;
    List<Purchases> purchasesModels;

    public PuAdapter(Context context, List<Purchases> purchasesModels) {
        this.context = context;
        this.purchasesModels = purchasesModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Purchases purchasesModel = purchasesModels.get(position);

        holder.paName.setText(purchasesModel.getSupplierName());
        holder.paContact.setText(purchasesModel.getSupplierPhone());

        holder.imgpaCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = purchasesModel.getSupplierPhone();

                Uri num = Uri.parse("tel:"+number);

                Intent intent = new Intent(Intent.ACTION_DIAL);
                // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // intent.setData(Uri.parse(addSalesModel.getCustomerPhone()));
                intent.setData(num);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchasesModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView paName, paContact;

        ImageView imgpaCall;

        public MyViewHolder(View itemView){
            super(itemView);

            paName = itemView.findViewById(R.id.tvName);
            paContact = itemView.findViewById(R.id.tvPhone);

            imgpaCall = itemView.findViewById(R.id.imgCalling);
        }
    }

}
