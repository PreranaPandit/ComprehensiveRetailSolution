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
import com.example.prerana.comprehensiveretailsolution.adapters.AddSalesAdapter;
import com.example.prerana.comprehensiveretailsolution.api.AddSalesAPI;
import com.example.prerana.comprehensiveretailsolution.model.AddSales;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSalesActivity extends AppCompatActivity {

    private AddSalesAdapter addSalesAdapter;

    List<AddSales> addSalesList;

    private RecyclerView recyclerView_sales;

    private EditText etSalesStatus;
    private ImageView imgSearchSales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search_sales);

        recyclerView_sales = findViewById(R.id.salesRecycler);

        etSalesStatus = findViewById(R.id.etSalesSerach);
        imgSearchSales = findViewById(R.id.imgSearchSales);

        imgSearchSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serachSales();

            }
        });

        salesShow();

    }

    private void salesShow() {
        addSalesList = new ArrayList<>();

        AddSalesAPI addSalesAPI = Url.getInstance().create(AddSalesAPI.class);
        Call<List<AddSales>> listCall = addSalesAPI.getAllSales();

        listCall.enqueue(new Callback<List<AddSales>>() {
            @Override
            public void onResponse(Call<List<AddSales>> call, Response<List<AddSales>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Please search by SalesStatus (PAID/UNPAID):: Error"+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                List<AddSales> addSales = response.body();
                AddSalesAdapter addSalesAdapter = new AddSalesAdapter(getApplicationContext(), addSales);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView_sales.setLayoutManager(layoutManager);
                recyclerView_sales.setHasFixedSize(true);
                recyclerView_sales.setAdapter(addSalesAdapter);
                addSalesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddSales>> call, Throwable t) {

                Log.d("Search of sales","onFailure: on failure"+t.getLocalizedMessage());

            }
        });

    }

    private void serachSales() {

        addSalesList = new ArrayList<>();

        AddSalesAPI addSalesAPI = Url.getInstance().create(AddSalesAPI.class);
        Call<List<AddSales>> listCall = addSalesAPI.searchSalesStatus(Url.token, etSalesStatus.getText().toString());

        listCall.enqueue(new Callback<List<AddSales>>() {
            @Override
            public void onResponse(Call<List<AddSales>> call, Response<List<AddSales>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Please search by SalesStatus (PAID/UNPAID):: Error"+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }

                List<AddSales> addSales = response.body();
                AddSalesAdapter addSalesAdapter = new AddSalesAdapter(getApplicationContext(), addSales);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView_sales.setLayoutManager(layoutManager);
                recyclerView_sales.setHasFixedSize(true);
                recyclerView_sales.setAdapter(addSalesAdapter);
                addSalesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddSales>> call, Throwable t) {

                Log.d("Search of sales","onFailure: on failure"+t.getLocalizedMessage());

            }
        });

    }
}
