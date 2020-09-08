package com.example.prerana.comprehensiveretailsolution.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.MainActivity;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.SalesCustomAdapter;
import com.example.prerana.comprehensiveretailsolution.api.AddSalesAPI;
import com.example.prerana.comprehensiveretailsolution.model.AddSales;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSalesActivity extends AppCompatActivity {

    private Spinner spinCategory;
    private TextView tvCategory;
    private Button btnsDate, btnAddSales;
    private TextView tvDate;

    private EditText sCustomerName, scustomerPhone, sBillNo, sAmount, sRemarks;
    private RadioGroup rdoGroup;
    private RadioButton radioPaid, radioUnpaid;

    //Alert
    AlertDialog.Builder builder;


    int year2, month2, day2;

    String[] salescategory = {"Store Sale", "Online Sale"};
    int salesimages[] = {R.drawable.ic_shopping_bag, R.drawable.ic_supply};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_sales);


        spinCategory = (Spinner) findViewById(R.id.spinCategory);
        tvCategory = findViewById(R.id.tvCategory);
        btnsDate = (Button) findViewById(R.id.sDate);
        tvDate = findViewById(R.id.tvDate);
        sCustomerName = findViewById(R.id.sCustomerName);
        scustomerPhone = findViewById(R.id.sCustomerPhone);
        sBillNo = findViewById(R.id.sBillNumber);
        rdoGroup = findViewById(R.id.rdoGroup);
        radioPaid = findViewById(R.id.radioPaid);
        radioUnpaid = findViewById(R.id.radioUnpaid);
        sRemarks = findViewById(R.id.sRemarks);
        btnAddSales = (Button) findViewById(R.id.btnAddingSale);
        sAmount = findViewById(R.id.sAmount);
        builder = new AlertDialog.Builder(this);


        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        btnsDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar();
            }
        });
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(SignUpActivity.this, "The country you selected is : "+country[position], Toast.LENGTH_SHORT).show();
                String countries = String.valueOf(salescategory[position]);
                tvCategory.setText(countries);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SalesCustomAdapter customAdapter = new SalesCustomAdapter(getApplicationContext(),salesimages,salescategory);
        spinCategory.setAdapter(customAdapter);


          btnAddSales.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(TextUtils.isEmpty(sCustomerName.getText())){
                      sCustomerName.setError("Please enter customer name");
                      sCustomerName.requestFocus();
                      return;
                  }
                  else if (TextUtils.isEmpty(scustomerPhone.getText())) {
                      scustomerPhone.setError("Please enter customer contact number ");
                      scustomerPhone.requestFocus();
                      return;
                  }
                  else if (scustomerPhone.length() != 10) {
                      scustomerPhone.setError("Contact number must be of 10 digits");
                      scustomerPhone.requestFocus();
                      return;
                  }
                  else if (TextUtils.isEmpty(sBillNo.getText())) {
                      sBillNo.setError("Please enter the bill number ");
                      sBillNo.requestFocus();
                      return;
                  }
                  else if (TextUtils.isEmpty(sAmount.getText())) {
                      sAmount.setError("Please enter the sales amount ");
                      sAmount.requestFocus();
                      return;
                  }
                  else{
                      addSales();
                     // Toast.makeText(AddSalesActivity.this, "Sales added successfully!!", Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }

    private void addSales() {

        String customerName = sCustomerName.getText().toString();
        String customerPhone = scustomerPhone.getText().toString();
        String billNumber = sBillNo.getText().toString();
        String amount = sAmount.getText().toString();
        String salesDate = tvDate.getText().toString();
        String remarks = sRemarks.getText().toString();

        SalesCustomAdapter customAdapter = new SalesCustomAdapter(getApplicationContext(),salesimages,salescategory);
        spinCategory.setAdapter(customAdapter);

        String category = tvCategory.getText().toString();

        int selectedStatus = rdoGroup.getCheckedRadioButtonId();
        RadioButton radioSalesStatus = (RadioButton) findViewById(selectedStatus);
        String salesStatus = radioSalesStatus.getText().toString();

        AddSales addSales = new AddSales(customerName,customerPhone,category,billNumber,amount,salesStatus,salesDate,remarks);

        AddSalesAPI addSalesAPI = Url.getInstance().create(AddSalesAPI.class);
        Call<AddSales> addSalesCall = addSalesAPI.addingSales(Url.token, addSales);

        addSalesCall.enqueue(new Callback<AddSales>() {
            @Override
            public void onResponse(Call<AddSales> call, Response<AddSales> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(AddSalesActivity.this, "Sales cannot be added. Error Code :" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

              // builder = new AlertDialog.Builder(getApplication());
                builder.setMessage("Sales is added successfully.");
                builder.setCancelable(true);
                AlertDialog salesAlert = builder.create();
                salesAlert.show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AddSales> call, Throwable t) {

                Toast.makeText(AddSalesActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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
                tvDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
