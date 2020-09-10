package com.example.prerana.comprehensiveretailsolution.ui.tools;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.PuAdapter;
import com.example.prerana.comprehensiveretailsolution.adapters.SaAdapter;
import com.example.prerana.comprehensiveretailsolution.api.AddSalesAPI;
import com.example.prerana.comprehensiveretailsolution.api.PurchaseAPI;
import com.example.prerana.comprehensiveretailsolution.model.AddSales;
import com.example.prerana.comprehensiveretailsolution.model.Purchases;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private SaAdapter saAdapter;
    private PuAdapter puAdapter;

    List<AddSales> addSalesList;
    List<Purchases> addPurchasesList;

    private RecyclerView recyclerView_contacts, recyclerView_purchase;
    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        toolsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        
        recyclerView_contacts = root.findViewById(R.id.contactsRecycler);
        recyclerView_purchase = root.findViewById(R.id.contactsPRecycler);
        
        contactsShow();
        contactsshowshow();
        return root;
    }

    private void contactsshowshow() {
        addPurchasesList = new ArrayList<>();

        PurchaseAPI addPurchasesAPI = Url.getInstance().create(PurchaseAPI.class);
        Call<List<Purchases>> listCallPurchase = addPurchasesAPI.getAllPurchases();

        listCallPurchase.enqueue(new Callback<List<Purchases>>() {
            @Override
            public void onResponse(Call<List<Purchases>> call, Response<List<Purchases>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Unsuccess, Error: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Purchases> addPurchases = response.body();
                PuAdapter addPurchaseAdpater = new PuAdapter(getContext(), addPurchases);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                  recyclerView_purchase.setLayoutManager(layoutManager);
                recyclerView_purchase.setHasFixedSize(true);
                recyclerView_purchase.setAdapter(addPurchaseAdpater);
                  addPurchaseAdpater.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Purchases>> call, Throwable t) {

                Log.d("Search on contacts","onFailure: on failure"+ t.getLocalizedMessage());

            }
        });

        }

    private void contactsShow() {

        addSalesList = new ArrayList<>();
        addPurchasesList = new ArrayList<>();

        AddSalesAPI addSalesAPI = Url.getInstance().create(AddSalesAPI.class);
        Call<List<AddSales>> listCall = addSalesAPI.getAllSales();

        PurchaseAPI addPurchasesAPI = Url.getInstance().create(PurchaseAPI.class);
        Call<List<Purchases>> listCallPurchase = addPurchasesAPI.getAllPurchases();

        listCall.enqueue(new Callback<List<AddSales>>() {
            @Override
            public void onResponse(Call<List<AddSales>> call, Response<List<AddSales>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Unsucess, Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<AddSales> addSales = response.body();
                SaAdapter addSalesAdapter = new SaAdapter(getContext(), addSales);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView_contacts.setLayoutManager(layoutManager);
                recyclerView_contacts.setHasFixedSize(true);
                recyclerView_contacts.setAdapter(addSalesAdapter);
                addSalesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddSales>> call, Throwable t) {

                Log.d("Search on contacts", "onFailure: on failure" + t.getLocalizedMessage());
            }
        });

//        listCallPurchase.enqueue(new Callback<List<Purchases>>() {
//            @Override
//            public void onResponse(Call<List<Purchases>> call, Response<List<Purchases>> response) {
//
//                if(!response.isSuccessful()){
//                    Toast.makeText(getContext(), "Unsuccess, Error: "+response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Purchases> addPurchases = response.body();
//                PuAdapter addPurchaseAdpater = new PuAdapter(getContext(), addPurchases);
//
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                  recyclerView_contacts.setLayoutManager(layoutManager);
//                  recyclerView_contacts.setHasFixedSize(true);
//                  recyclerView_contacts.setAdapter(addPurchaseAdpater);
//                  addPurchaseAdpater.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Purchases>> call, Throwable t) {
//
//                Log.d("Search on contacts","onFailure: on failure"+ t.getLocalizedMessage());
//
//            }
//        });

    }
}