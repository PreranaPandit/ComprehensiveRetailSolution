package com.example.prerana.comprehensiveretailsolution.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.activity.AddSalesActivity;
import com.example.prerana.comprehensiveretailsolution.activity.PurchaseActivity;
import com.example.prerana.comprehensiveretailsolution.adapters.SliderImageAdapter;
import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.model.ImageModel;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.url.Url;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    CircleImageView imgBusinessLogo;
    TextView tvBusinessName, tvEmailAddress;
    LinearLayout lvAddSales, lvPurchaseSales;

    RadarChart pieRadarChart;

    PieChart pieChart;


    private static ViewPager mPager;
    private  static int currentPages = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{  R.drawable.oneone, R.drawable.five,   R.drawable.four
                                           , R.drawable.six, R.drawable.seven, R.drawable.nine , R.drawable.one, R.drawable.three};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        mPager.setAdapter(new SliderImageAdapter(getActivity(),imageModelArrayList));

        //data binding
        imgBusinessLogo = root.findViewById(R.id.imgProfileImage);
        tvBusinessName = root.findViewById(R.id.tvFullName);
        tvEmailAddress = root.findViewById(R.id.tvEmailAdrress);
        lvAddSales = root.findViewById(R.id.addSales);
        lvPurchaseSales = root.findViewById(R.id.purchaseSales);

        lvAddSales.setOnClickListener(this);
        lvPurchaseSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent purchaseIntent = new Intent(getContext(), PurchaseActivity.class);
                startActivity(purchaseIntent);
            }
        });

        //function to load users details as image, name, emailid
        loadCurrentUser();


        CirclePageIndicator indicator = (CirclePageIndicator) root.findViewById(R.id.circle_indicator);
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //set circle indicator radius

        indicator.setRadius(5*density);

        NUM_PAGES = imageModelArrayList.size();

        //auto start viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {

                if(currentPages == NUM_PAGES)
                {
                    currentPages = 0;
                }

                mPager.setCurrentItem(currentPages++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },2000,2000);

        //page Lietener over inidcator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPages = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       pieRadarChart = root.findViewById(R.id.idPieRadarChart);
       pieChart = root.findViewById(R.id.idPieChart);

       pieradarDisplay();

       pieDisplay();

        return root;
    }

    private void pieDisplay() {
        ArrayList salesList = new ArrayList();

        salesList.add(new Entry(3500000,0));
        salesList.add(new Entry(5500000,1));


        PieDataSet dataSet = new PieDataSet(salesList,"Complete Retails");

        ArrayList names = new ArrayList();

        names.add("Purchase");
        names.add("Sales");



        PieData data = new PieData(names,dataSet);
        pieChart.setDescription("Purchase per Supplier");
        pieChart.setHoleRadius(15f);
        //pieChart.setTransparentCircleAlpha(0);
        // pieChart.setCenterText("Purchase");
        // pieChart.setCenterTextSize(12);
        pieChart.setHoleColor(Color.rgb(105, 221, 250));

        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.animateXY(5000,5000);


    }

    private void pieradarDisplay() {

        ArrayList salesList = new ArrayList();

        salesList.add(new Entry(25000,0));
        salesList.add(new Entry(55000,1));
        salesList.add(new Entry(85000,2));
        salesList.add(new Entry(10000,3));
        salesList.add(new Entry(25000,4));
        salesList.add(new Entry(55000,5));
        salesList.add(new Entry(25000,6));
        salesList.add(new Entry(95000,7));
        salesList.add(new Entry(3900,8));
        salesList.add(new Entry(30000,9));

        RadarDataSet dataSet = new RadarDataSet(salesList,"Purchase Amount");

        ArrayList names = new ArrayList();

        names.add("Ram");
        names.add("Shyam");
        names.add("Hari");
        names.add("Geeta");
        names.add("Rita");
        names.add("Neeta");
        names.add("Sita");
        names.add("John");
        names.add("Albert");
        names.add("Raju");
        names.add("Shivam");



        RadarData data = new RadarData(names,dataSet);
        pieRadarChart.setDescription("Purchase per Supplier");
       // pieRadarChart.setWebLineWidth(25f);
        //pieRadarChart.setWebColorInner();
       //  pieRadarChart.setHoleRadius(25f);
//        pieRadarChart.setTransparentCircleAlpha(0);
//       pieRadarChart.setCenterText("Purchase");
//       pieRadarChart.setCenterTextSize(12);
//        pieRadarChart.setHoleColor(Color.rgb(105, 221, 250));

        pieRadarChart.setData(data);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        pieRadarChart.animateXY(5000,5000);


    }

    private void loadCurrentUser() {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<User> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String imgPath = Url.imagePath + response.body().getImage();
                String businessName = response.body().getBusinessName();
                String emailId = response.body().getEmailId();

                Picasso.get().load(imgPath).into(imgBusinessLogo);
                tvBusinessName.setText(businessName);
                tvEmailAddress.setText("Email Id: "+emailId);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private ArrayList<ImageModel> populateList() {
        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), AddSalesActivity.class);
        startActivity(intent);
    }
}