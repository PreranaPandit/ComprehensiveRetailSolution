package com.example.prerana.comprehensiveretailsolution.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.activity.AddSalesActivity;
import com.example.prerana.comprehensiveretailsolution.activity.SearchSalesActivity;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private GalleryViewModel galleryViewModel;
    private Button btnAddSales, btnSearchSales;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        btnAddSales = root.findViewById(R.id.btnAddSales);
        btnSearchSales = root.findViewById(R.id.btnSearchSales);


        btnAddSales.setOnClickListener(this);
        btnSearchSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent searchIntent = new Intent(getContext(), SearchSalesActivity.class);
               startActivity(searchIntent);
            }
        });

        return root;
    }


    @Override
    public void onClick(View v) {
        Intent addSalesIntent = new Intent(getContext(), AddSalesActivity.class);
        startActivity(addSalesIntent);
    }
}