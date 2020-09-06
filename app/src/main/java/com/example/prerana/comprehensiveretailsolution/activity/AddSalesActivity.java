package com.example.prerana.comprehensiveretailsolution.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.SalesCustomAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSalesActivity extends AppCompatActivity {

    private Spinner spinCategory;
    TextView tvCategory;
    Button btnsDate;
    TextView tvDate;


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
        btnsDate = findViewById(R.id.sDate);
        tvDate = findViewById(R.id.tvDate);


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
