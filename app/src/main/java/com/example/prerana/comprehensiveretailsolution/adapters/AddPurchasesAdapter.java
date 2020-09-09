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
import com.example.prerana.comprehensiveretailsolution.activity.SearchPurchaseActivity;
import com.example.prerana.comprehensiveretailsolution.api.PurchaseAPI;
import com.example.prerana.comprehensiveretailsolution.model.Purchases;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPurchasesAdapter extends RecyclerView.Adapter<AddPurchasesAdapter.MyViewHolder>{

    Context context;
    List<Purchases> purchasesModels;

    public AddPurchasesAdapter(Context context, List<Purchases> purchasesModels) {
        this.context = context;
        this.purchasesModels = purchasesModels;
    }

    @NonNull
    @Override
    public AddPurchasesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_layout,parent,false);
        return new AddPurchasesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddPurchasesAdapter.MyViewHolder holder, int position) {

        final Purchases purchasesModel = purchasesModels.get(position);

       // holder.pSupplierId.setText(purchasesModel.get_id());
        holder.pSupplierName.setText(purchasesModel.getSupplierName());
        holder.pSupplierPhone.setText(purchasesModel.getSupplierPhone());
        holder.pBillNumber.setText(purchasesModel.getSupplierBillNumber());
        holder.pAmount.setText(purchasesModel.getSupplierAmount());
       // holder.p.setText(addSalesModel.getSalesDate());
        holder.pPurchasesStatus.setText(purchasesModel.getSupplierStatus());

        holder.imgpCall.setOnClickListener(new View.OnClickListener() {
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


        holder.imgpDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String id = purchasesModel.get_id();
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                PurchaseAPI addPurchases = Url.getInstance().create(PurchaseAPI.class);
                Call<Purchases> purchasesCallCall = addPurchases.deletePurchases(Url.token, id);

                purchasesCallCall.enqueue(new Callback<Purchases>() {
                    @Override
                    public void onResponse(Call<Purchases> call, Response<Purchases> response) {
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
                                        Toast.makeText(context, purchasesModel.getSupplierName() + ""+"has been deleted successfully..", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(v.getContext(), SearchPurchaseActivity.class);
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
                    public void onFailure(Call<Purchases> call, Throwable t) {
                        Toast.makeText(context, "Error occurred" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return purchasesModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pSupplierId, pSupplierName, pSupplierPhone, pBillNumber, pAmount, pPurchasesStatus;

        ImageView imgpCall, imgpDel;

        public MyViewHolder(View itemView)
        {
            super(itemView);

           // pSupplierId = itemView.findViewById(R.id.pSupplierId);
            pSupplierName = itemView.findViewById(R.id.pSupplierName);
            pSupplierPhone = itemView.findViewById(R.id.pSupplierPhone);
            pBillNumber = itemView.findViewById(R.id.pBillNumber);
            pAmount = itemView.findViewById(R.id.pAmount);
           // aSalesDate = itemView.findViewById(R.id.aSalesDate);
            pPurchasesStatus = itemView.findViewById(R.id.pPurchasesStatus);

            imgpCall = itemView.findViewById(R.id.pCall);
            imgpDel = itemView.findViewById(R.id.pDelete);


        }
    }
}

