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
import com.example.prerana.comprehensiveretailsolution.model.AddSales;

import java.util.List;

public class SaAdapter extends RecyclerView.Adapter<SaAdapter.MyViewHolder> {

    Context context;
    List<AddSales> addSalesModels;

    public SaAdapter(Context context, List<AddSales> addSalesModels) {
        this.context = context;
        this.addSalesModels = addSalesModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final AddSales addSalesModel = addSalesModels.get(position);

        holder.SaCustomerName.setText(addSalesModel.getCustomerName());
        holder.SaCustomerPhone.setText(addSalesModel.getCustomerPhone());

        holder.imgSaCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = addSalesModel.getCustomerPhone();

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
        return addSalesModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView SaCustomerName, SaCustomerPhone;

        ImageView imgSaCall;

        public MyViewHolder(View itemView)
        {
            super(itemView);

           SaCustomerName = itemView.findViewById(R.id.tvName);
           SaCustomerPhone = itemView.findViewById(R.id.tvPhone);

           imgSaCall = itemView.findViewById(R.id.imgCalling);

        }
    }
}
