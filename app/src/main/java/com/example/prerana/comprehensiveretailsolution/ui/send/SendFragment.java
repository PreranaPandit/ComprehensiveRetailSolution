package com.example.prerana.comprehensiveretailsolution.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.prerana.comprehensiveretailsolution.MainActivity;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    private EditText editEmpNo, editBusinessName, editFullName, editGender, editCountry, editContactNumber, editAddress,
            editEmailID;

    private TextView tvID;

    String imagename;
    private Button btnUpdateProfile;
    private ImageView imageBack;


    User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
//        final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        //dataBinding

        editBusinessName = root.findViewById(R.id.editBusinessName);
        editFullName = root.findViewById(R.id.editFullName);
        editGender = root.findViewById(R.id.editGender);
       // editBloodGroup = root.findViewById(R.id.editBloodGroup);
        editCountry = root.findViewById(R.id.editCountry);
        editContactNumber = root.findViewById(R.id.editContactNumber);
        editAddress = root.findViewById(R.id.editCity);
        editEmailID = root.findViewById(R.id.editEmail);
        tvID = root.findViewById(R.id.editID);
        btnUpdateProfile = root.findViewById(R.id.btnUpdateprofile);
        imageBack = root.findViewById(R.id.imageBack);

        loadCurrentUserProfile();

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = new User(

                        editBusinessName.getText().toString(),
                        editFullName.getText().toString(),
                        editGender.getText().toString(),
                        editCountry.getText().toString(),
                        editContactNumber.getText().toString(),
                        editAddress.getText().toString(),
                        editEmailID.getText().toString()

                );
//               String fullName = editFullName.getText().toString();
//                String gender = editGender.getText().toString();
//                String bloodGroup = editBloodGroup.getText().toString();
//                String country = editCountry.getText().toString();
//                String contactNumber = editContactNumber.getText().toString();
//                String address = editAddress.getText().toString();
//                String emailId = editEmailID.getText().toString();
//                String id = editEmpNo.getText().toString();

                //to check
//                Toast.makeText(getContext(), "id" + editEmpNo.getText().toString(), Toast.LENGTH_SHORT).show();

                String id = tvID.getText().toString();


                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<User> userCall = usersAPI.updateProfile(Url.token, user, id);


                userCall.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        if(!response.isSuccessful())
                        {
                            Toast.makeText( getContext(), "Code " + response.code(), Toast.LENGTH_SHORT ).show();
                            return;
                        }

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("Profile is Updated Successfully");
                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    }


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("User Failed to Update");
                        builder1.setCancelable(true);

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        Toast.makeText(getActivity(), "Error occurred"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });




        return root;
    }

    private void loadCurrentUserProfile() {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        Call<User> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

//                String imgPath = Url.imagePath + response.body().getImage();
                String businessName = response.body().getBusinessName();
                String fullName = response.body().getFullName();
                String gender = response.body().getGender();
              //  String bloodGroup = response.body().getBloodGroup();
                String country = response.body().getCountry();
                String contactNumber = response.body().getContactNumber();
                String address = response.body().getAddress();
                String emailId = response.body().getEmailId();
                String id = response.body().get_id();

//                Picasso.get().load(imgPath).into(imgProfileImage);
                editBusinessName.setText(businessName);
                editFullName.setText(fullName);
                tvID.setText(id);
                editGender.setText(gender);
                //editBloodGroup.setText(bloodGroup);
                editCountry.setText(country);
                editContactNumber.setText(contactNumber);
                editAddress.setText(address);
                editEmailID.setText(emailId);


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


//
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