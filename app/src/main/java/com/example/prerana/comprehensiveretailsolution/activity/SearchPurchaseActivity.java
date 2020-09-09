package com.example.prerana.comprehensiveretailsolution.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.AddPurchasesAdapter;
import com.example.prerana.comprehensiveretailsolution.api.PurchaseAPI;
import com.example.prerana.comprehensiveretailsolution.model.Purchases;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPurchaseActivity extends AppCompatActivity {

    private AddPurchasesAdapter addPurchasesAdapter;

    List<Purchases> addPurchasesList;

    private RecyclerView recyclerView_purchases;

    private EditText etPurchasesStatus;
    private ImageView imgSearchPurchases;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search_purchase);

        recyclerView_purchases = findViewById(R.id.purchasesRecycler);

        etPurchasesStatus = findViewById(R.id.etPurchasesStatus);
        imgSearchPurchases = findViewById(R.id.imgSearchPurchases);

        imgSearchPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPuchases();

            }
        });

        purchasesShow();
    }

    private void purchasesShow() {
        addPurchasesList = new ArrayList<>();

        PurchaseAPI addPurchasesAPI = Url.getInstance().create(PurchaseAPI.class);
        Call<List<Purchases>> listCall = addPurchasesAPI.getAllPurchases();

        listCall.enqueue(new Callback<List<Purchases>>() {
            @Override
            public void onResponse(Call<List<Purchases>> call, Response<List<Purchases>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Please search by Status (PAID/UNPAID):: Error"+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                List<Purchases> addPurchases = response.body();
                AddPurchasesAdapter addSalesAdapter = new AddPurchasesAdapter(getApplicationContext(), addPurchases);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView_purchases.setLayoutManager(layoutManager);
                recyclerView_purchases.setHasFixedSize(true);
                recyclerView_purchases.setAdapter(addSalesAdapter);
                addSalesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Purchases>> call, Throwable t) {

                Log.d("Search of Purchases","onFailure: on failure"+t.getLocalizedMessage());

            }
        });


    }

    private void searchPuchases() {

        addPurchasesList = new ArrayList<>();

        PurchaseAPI addPurchasesAPI = Url.getInstance().create(PurchaseAPI.class);
        Call<List<Purchases>> listCall = addPurchasesAPI.searchPurchaseStatus(Url.token, etPurchasesStatus.getText().toString());

        listCall.enqueue(new Callback<List<Purchases>>() {
            @Override
            public void onResponse(Call<List<Purchases>> call, Response<List<Purchases>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Please search by Status (PAID/UNPAID):: Error"+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                List<Purchases> addPurchases = response.body();
                AddPurchasesAdapter addSalesAdapter = new AddPurchasesAdapter(getApplicationContext(), addPurchases);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView_purchases.setLayoutManager(layoutManager);
                recyclerView_purchases.setHasFixedSize(true);
                recyclerView_purchases.setAdapter(addSalesAdapter);
                addSalesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Purchases>> call, Throwable t) {

                Log.d("Search of Purchases","onFailure: on failure"+t.getLocalizedMessage());

            }
        });

    }
    }

