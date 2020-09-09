package com.example.prerana.comprehensiveretailsolution.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.activity.SearchSalesActivity;
import com.example.prerana.comprehensiveretailsolution.api.AddSalesAPI;
import com.example.prerana.comprehensiveretailsolution.model.AddSales;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSalesAdapter extends RecyclerView.Adapter<AddSalesAdapter.MyViewHolder> {

    Context context;
    List<AddSales> addSalesModels;

    public AddSalesAdapter(Context context, List<AddSales> addSalesModels) {
        this.context = context;
        this.addSalesModels = addSalesModels;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final AddSales addSalesModel = addSalesModels.get(position);

        holder.aCustomerName.setText(addSalesModel.getCustomerName());
        holder.aCustomerPhone.setText(addSalesModel.getCustomerPhone());
        holder.aBillNumber.setText(addSalesModel.getBillNumber());
        holder.aAmount.setText(addSalesModel.getAmount());
        holder.aSalesDate.setText(addSalesModel.getSalesDate());
        holder.aSalesStatus.setText(addSalesModel.getSalesStatus());

        holder.imgCall.setOnClickListener(new View.OnClickListener() {
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


        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String id = addSalesModel.get_id();
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                AddSalesAPI addSales = Url.getInstance().create(AddSalesAPI.class);
                Call<AddSales> salesCall = addSales.deleteSales(Url.token, id);

                salesCall.enqueue(new Callback<AddSales>() {
                    @Override
                    public void onResponse(Call<AddSales> call, Response<AddSales> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(context, " Delete is not success !! : Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                       // AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                         builder1.setMessage("Are you sure to delete this sales record?");
                        builder1.setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context, "Customer"+""+addSalesModel.getCustomerName() + ""+" has been deleted successfully..", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(v.getContext(), SearchSalesActivity.class);
                                        v.getContext().startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.setTitle("Delete");
                        alert11.show();
                    }

                    @Override
                    public void onFailure(Call<AddSales> call, Throwable t) {
                        Toast.makeText(context, "Error occurred" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return addSalesModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView aCustomerName, aCustomerPhone, aBillNumber, aAmount, aSalesDate, aSalesStatus;

        ImageView imgCall, imgDel;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            aCustomerName = itemView.findViewById(R.id.aCustomerName);
            aCustomerPhone = itemView.findViewById(R.id.aCustomerPhone);
            aBillNumber = itemView.findViewById(R.id.aBillNumber);
            aAmount = itemView.findViewById(R.id.aAmount);
            aSalesDate = itemView.findViewById(R.id.aSalesDate);
            aSalesStatus = itemView.findViewById(R.id.aSalesStatus);

            imgCall = itemView.findViewById(R.id.dCall);
            imgDel = itemView.findViewById(R.id.dDelete);


        }
    }
}
