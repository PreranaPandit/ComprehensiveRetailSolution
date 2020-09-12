package com.example.prerana.comprehensiveretailsolution.ui.share;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.activity.CompleteSalesReportActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    PieChart pieChart;

    BarChart barChart;

    Button btnPurchaseReport;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
//        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        Log.d(TAG, "onCreate: starting to create chart");


        barChart = root.findViewById(R.id.idSalesBarchart);

       pieChart = root.findViewById(R.id.idSalesPiechart);

       btnPurchaseReport = root.findViewById(R.id.btnPurchaseReport);

       btnPurchaseReport.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), CompleteSalesReportActivity.class);
               startActivity(intent);
           }
       });

        salesPie();

        barDisplay();

        return root;
    }

    private void barDisplay() {

        ArrayList SalesAmount = new ArrayList();

        SalesAmount.add(new BarEntry(38000, 0));
        SalesAmount.add(new BarEntry(95000, 1));
        SalesAmount.add(new BarEntry(150000,2));
        SalesAmount.add(new BarEntry(350000,3));
        SalesAmount.add(new BarEntry(850000,4));
        SalesAmount.add(new BarEntry(1200000,5));
        SalesAmount.add(new BarEntry(1800000,6));

        ArrayList values = new ArrayList();

        values.add("This Week");
        values.add("This Month");
        values.add("This Year");
        values.add("Two Years");
        values.add("Five Years");
        values.add("Eight Years");
        values.add("Ten Years");

        BarDataSet barDataSet = new BarDataSet(SalesAmount, "Sales Amount");

        barChart.animateY(5000);

        BarData data = new BarData(values, barDataSet);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barChart.setData(data);
        barChart.setDescription("Complete Sales Details");

    }

    private void salesPie() {

        ArrayList salesList = new ArrayList();

        salesList.add(new Entry(25000,0));
        salesList.add(new Entry(5500,1));
        salesList.add(new Entry(18000,2));
        salesList.add(new Entry(2200,3));
        salesList.add(new Entry(6500,4));
        salesList.add(new Entry(2500,5));
        salesList.add(new Entry(6800,6));
        salesList.add(new Entry(8900,7));
        salesList.add(new Entry(19500,8));
        salesList.add(new Entry(11500,9));

        PieDataSet dataSet = new PieDataSet(salesList,"Sales Amount");

        ArrayList names = new ArrayList();

        names.add("Avinash");
        names.add("Ram");
            names.add("Shyam");
        names.add("Harry");
        names.add("Geeta");
        names.add("Albert");
        names.add("Edwin");
        names.add("Suhana");
        names.add("Shreeya");
        names.add("Loius");

        PieData data = new PieData(names,dataSet);
        pieChart.setDescription("Sales by Customer");
        pieChart.setHoleRadius(25f);
        //pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("SALES");
        pieChart.setCenterTextSize(12);
        pieChart.setHoleColor(Color.rgb(215, 203, 245));

        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000,5000);




    }
}