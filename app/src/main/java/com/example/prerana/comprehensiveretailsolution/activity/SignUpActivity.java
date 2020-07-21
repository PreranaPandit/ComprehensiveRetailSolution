package com.example.prerana.comprehensiveretailsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.R;

/**
 * Created by Prerana Pandit on 17/07/2020.
 */

public class SignUpActivity extends AppCompatActivity {

    private ImageView imgProfile;
    TextView tvCountry;
    private EditText etBusinessName, etFullName, etContact, etEmail, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private Spinner spinCity;
    private TextView tvLogin;
    private AutoCompleteTextView autoCompleteTextView;
    private String imageName = "";
    String imagePath;
    private RadioGroup rdoGroup;
    private RadioButton rdoBtnMale, rdoBtnFemale, rdoBtnOthers;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private String[] address = {"Kathmandu","Pokhara","Lumbini","Lalitpur","Bhaktapur",
            "Bharatpur","Bharatpur","Janakpur","Ghorahi","Tulsipur","Hetauda",
            "Nepalgunj","Dharan","Itahari","Jitpur Simara","Ilam","Gulmi","Kavre","Kakadvitta",
            "Baitadi","Rajbiraj","Bhojpur","Humla","Damauli","Nawalparasi",
            "Butwal","Birgunj","Lamjung","Chitwan","Jhapa","Palpa","Jumla","Dhading"};

    //Alert
    AlertDialog.Builder builder;

    String[] country = {"Nepal","India","USA","UK","China","Bhutan","Pakistan","Australia",
            "Japan","France","England","Mexico","Qatar","Vietnam"};
    int images[] = {R.drawable.nepal,R.drawable.india,R.drawable.us,R.drawable.uk,
            R.drawable.china,R.drawable.bhutan,R.drawable.pakistan,R.drawable.australia,
            R.drawable.japan,R.drawable.france,R.drawable.england,R.drawable.mexico,
            R.drawable.qatar,R.drawable.vietnam
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up);

        //spinner for gender

        spinCity = (Spinner) findViewById(R.id.spinCity);
        tvLogin = findViewById(R.id.tvLogIn);
        builder = new AlertDialog.Builder(this);
        autoCompleteTextView = findViewById(R.id.etAddress);
        etFullName = findViewById(R.id.etFullName);
        etBusinessName = findViewById(R.id.etBusinessName);
        imgProfile = findViewById(R.id.imgProfile);
        rdoGroup = findViewById(R.id.rdoGroup);
        rdoBtnMale = findViewById(R.id.rdoBtnMale);
        rdoBtnFemale = findViewById(R.id.rdoBtnFemale);
        rdoBtnOthers = findViewById(R.id.rdoBtnOthers);
        etContact = findViewById(R.id.etContact);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvCountry = findViewById(R.id.tvCountry);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
    }

    private void BrowseImage() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);
        
    }
}
