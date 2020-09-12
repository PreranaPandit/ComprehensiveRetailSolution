package com.example.prerana.comprehensiveretailsolution.activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.R;
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

public class CompleteSalesReportActivity extends AppCompatActivity {

    private PieChart purchasePieChart;
    private BarChart purchaseBarChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_sales_report);

        purchaseBarChart = findViewById(R.id.purchaseBarChart);
        purchasePieChart = findViewById(R.id.purchasePieChart);

        barpChart();
        piepChart();
    }

    private void piepChart() {

        ArrayList salesList = new ArrayList();

        salesList.add(new Entry(25000,0));
        salesList.add(new Entry(55000,1));
        salesList.add(new Entry(18000,2));
        salesList.add(new Entry(22000,3));
        salesList.add(new Entry(6500,4));
        salesList.add(new Entry(25000,5));
        salesList.add(new Entry(38000,6));
        salesList.add(new Entry(8900,7));
        salesList.add(new Entry(19500,8));


        PieDataSet dataSet = new PieDataSet(salesList,"Purchase Amount");

        ArrayList names = new ArrayList();

        names.add("Supplier:01");
        names.add("Supplier:02");
        names.add("Supplier:03");
        names.add("Supplier:04");
        names.add("Supplier:05");
        names.add("Supplier:06");
        names.add("Supplier:07");
        names.add("Supplier:08");
        names.add("Supplier:09");


        PieData data = new PieData(names,dataSet);
        purchasePieChart.setDescription("Purchase per Supplier");
        purchasePieChart.setHoleRadius(25f);
        //pieChart.setTransparentCircleAlpha(0);
        purchasePieChart.setCenterText("Purchase");
        purchasePieChart.setCenterTextSize(12);
        purchasePieChart.setHoleColor(Color.rgb(105, 221, 250));

        purchasePieChart.setData(data);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        purchasePieChart.animateXY(5000,5000);


    }

    private void barpChart() {

        ArrayList SalesAmount = new ArrayList();

        SalesAmount.add(new BarEntry(52000, 0));
        SalesAmount.add(new BarEntry(150000, 1));
        SalesAmount.add(new BarEntry(295000,2));
        SalesAmount.add(new BarEntry(380000,3));
        SalesAmount.add(new BarEntry(890000,4));
        SalesAmount.add(new BarEntry(1400000,5));
        SalesAmount.add(new BarEntry(1805500,6));

        ArrayList values = new ArrayList();

        values.add("This Week");
        values.add("This Month");
        values.add("This Year");
        values.add("Two Years");
        values.add("Five Years");
        values.add("Eight Years");
        values.add("Ten Years");

        BarDataSet barDataSet = new BarDataSet(SalesAmount, "Purchase Amount");

        purchaseBarChart.animateY(5000);

        BarData data = new BarData(values, barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        purchaseBarChart.setData(data);
        purchaseBarChart.setDescription("Purchase");


    }
}
