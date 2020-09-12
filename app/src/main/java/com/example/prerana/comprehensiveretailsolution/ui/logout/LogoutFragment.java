package com.example.prerana.comprehensiveretailsolution.ui.logout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.activity.LoginActivity;
import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutFragment extends Fragment {

    private LogoutViewModel logOutViewModel;

    private Button btnLogOut;
    AlertDialog.Builder builder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogoutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



        btnLogOut = root.findViewById(R.id.btnLogOut);
        builder = new AlertDialog.Builder(getContext());


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                builder.setMessage("Are you sure to get Logout? ")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                                //  Toast.makeText(getContext(), "You are logout successfully !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for no button clicked
                                dialog.cancel();
                                // Toast.makeText(getContext(), "You  !", Toast.LENGTH_SHORT).show();
                            }
                        });

                //creating dialog box
                AlertDialog alert = builder.create();
                alert.setTitle(" Alert! ");
                alert.show();






            }
        });


        return root;
    }



    public void logout(){
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> userCall = usersAPI.logout(Url.token);

        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                        if(!response.isSuccessful()){
//                            Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_SHORT).show();
//                            return;
//                        }


                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    //to hide title from account fragment
    @Override
    public void onResume()
    {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }


}
