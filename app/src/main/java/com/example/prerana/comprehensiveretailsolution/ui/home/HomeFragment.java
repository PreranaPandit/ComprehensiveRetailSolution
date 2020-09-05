package com.example.prerana.comprehensiveretailsolution.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.SliderImageAdapter;
import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.model.ImageModel;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.url.Url;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    CircleImageView imgBusinessLogo;
    TextView tvBusinessName, tvEmailAddress;


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


        return root;
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
}