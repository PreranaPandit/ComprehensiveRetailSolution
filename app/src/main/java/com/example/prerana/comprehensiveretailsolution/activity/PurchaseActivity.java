package com.example.prerana.comprehensiveretailsolution.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.MainActivity;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.api.PurchaseAPI;
import com.example.prerana.comprehensiveretailsolution.model.Purchases;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseActivity extends AppCompatActivity {

    private Button btnsupplyDate, btnAddPurchase;
    private TextView supplyDate;

    private EditText supplierName, supplierPhone, supplierBillNo, supplierAmount, supplierRemarks;
    private RadioGroup rdoSupplierGroup;
    private RadioButton rdoPaid, rdoUnpaid;

    //Alert
    AlertDialog.Builder builder;


    int year2, month2, day2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_purchase);

        btnsupplyDate = (Button) findViewById(R.id.supplyDate);
        supplyDate = findViewById(R.id.supplierDate);
        supplierName = findViewById(R.id.supplierName);
        supplierPhone = findViewById(R.id.supplierPhone);
        supplierBillNo = findViewById(R.id.supplierBillNumber);
        rdoSupplierGroup = findViewById(R.id.rdoSupplierGroup);
        rdoPaid = findViewById(R.id.rdoPaid);
        rdoUnpaid = findViewById(R.id.rdoUnpaid);
        supplierRemarks = findViewById(R.id.supplierRemarks);
        btnAddPurchase = (Button) findViewById(R.id.btnAddingPurchase);
        supplierAmount = findViewById(R.id.supplierAmount);
        builder = new AlertDialog.Builder(this);


        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        btnsupplyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar();
            }
        });


        btnAddPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(supplierName.getText())){
                    supplierName.setError("Please enter supplier name");
                    supplierName.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(supplierPhone.getText())) {
                    supplierPhone.setError("Please enter supplier contact number ");
                    supplierPhone.requestFocus();
                    return;
                }
                else if (supplierPhone.length() != 10) {
                    supplierPhone.setError("Contact number must be of 10 digits");
                    supplierPhone.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(supplierBillNo.getText())) {
                    supplierBillNo.setError("Please enter the bill number ");
                    supplierBillNo.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(supplierAmount.getText())) {
                    supplierAmount.setError("Please enter the purchase amount ");
                    supplierAmount.requestFocus();
                    return;
                }
                else{
                    addPurchases();
                    //Toast.makeText(PurchaseActivity.this, "Sales added successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addPurchases() {

        String sName  = supplierName.getText().toString();
        String sPhone = supplierPhone.getText().toString();
        String sBillNumber = supplierBillNo.getText().toString();
        String sAmount = supplierAmount.getText().toString();
        String sDate = supplyDate.getText().toString();
        String remarks = supplierRemarks.getText().toString();

        int selectedStatus = rdoSupplierGroup.getCheckedRadioButtonId();
        RadioButton radioSalesStatus = (RadioButton) findViewById(selectedStatus);
        String supplierStatus = radioSalesStatus.getText().toString();

       // Toast.makeText(this, "date:"+sDate, Toast.LENGTH_SHORT).show();
        Purchases purchases = new Purchases(sName,sPhone,sBillNumber,sAmount,supplierStatus,sDate,remarks);

        PurchaseAPI purchaseAPI = Url.getInstance().create(PurchaseAPI.class);
        Call<Purchases> purchasesCall = purchaseAPI.addingPurchases(Url.token, purchases);


        purchasesCall.enqueue(new Callback<Purchases>() {
            @Override
            public void onResponse(Call<Purchases> call, Response<Purchases> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                //  Toast.makeText(getContext(), "request sedn gdsfg", Toast.LENGTH_SHORT).show();
               // androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Purchase has been added successfully!!");
                builder.setCancelable(true);
                AlertDialog salesAlert = builder.create();
                salesAlert.show();

                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Purchases> call, Throwable t) {
                Toast.makeText(PurchaseActivity.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void loadCalendar() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final  int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = month+"/"+dayOfMonth+"/"+year;
                month2 = month;
                day2 = dayOfMonth;
                year2 = year;
                supplyDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
