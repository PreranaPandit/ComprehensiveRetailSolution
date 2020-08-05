package com.example.prerana.comprehensiveretailsolution.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.prerana.comprehensiveretailsolution.QuestionLibrary;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.CustomAdapter;
import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.serverresponse.ImageResponse;
import com.example.prerana.comprehensiveretailsolution.serverresponse.SignUpResponse;
import com.example.prerana.comprehensiveretailsolution.strictmode.StrictModeClass;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

     private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

     private TextView mScoreView;
     private Button mQuestionView;
     private Button mButtonChoice1;
     private Button mButtonChoice2;
     private Button mButtonChoice3;

     private String mAnswer;
     private int mScore = 0;
     private int mQuestionNumber = 0;


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

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (Button) findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);

        updateQuestion();


        multiChoice();

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validations for widgets
                if (TextUtils.isEmpty(etBusinessName.getText())) {
                    etBusinessName.setError("Enter your business name");
                    etBusinessName.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(etFullName.getText())) {
                    etFullName.setError("Enter your name");
                    etFullName.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(etContact.getText())) {
                    etContact.setError("Enter contact number ");
                    etContact.requestFocus();
                    return;
                }
                else if (etContact.length() != 10) {
                    etContact.setError("Contact number must be of 10 numbers");
                    etContact.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(etEmail.getText())) {
                    etEmail.setError("Enter your email address");
                    etEmail.requestFocus();
                    return;
                }
                else if(!etEmail.getText().toString().trim().matches(emailPattern))
                {
                    etEmail.setError("Email address must be in correct pattern");
                    etEmail.requestFocus();
                    return;
                }

                else if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    if(validate()) {
                        saveImageOnly();
                        //signUp();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
            }
        });

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_item,address
        );

        autoCompleteTextView.setAdapter(stringArrayAdapter);
        //will start working from first character
        autoCompleteTextView.setThreshold(1);

        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignUpActivity.this, "The country you selected is : "+country[position], Toast.LENGTH_SHORT).show();
                String countries = String.valueOf(country[position]);
                tvCountry.setText(countries);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),images,country);
        spinCity.setAdapter(customAdapter);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Did you completed your registration ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Toast.makeText(SignUpActivity.this, "You may have completed your registration!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for no button clicked
                                dialog.cancel();
                                Toast.makeText(SignUpActivity.this, "You have not completed your registration yet!", Toast.LENGTH_SHORT).show();
                            }
                        });

                //creating dialog box
                AlertDialog alert = builder.create();
                alert.setTitle(" Registration Confirmation ");
                alert.show();
            }
        });
    }

    private void updateScore(int point) {
        mScoreView.setText(" + mScore");

        if(mScore == 3){
            signUp();
        }
        else{
            Toast.makeText(this, "Please go through the security questions!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion() {
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }

    private void signUp() {

        String businessName = etBusinessName.getText().toString();
        String fullName = etFullName.getText().toString();
        String contactNumber = etContact.getText().toString();
        String password = etPassword.getText().toString();
        String emailId = etEmail.getText().toString();


        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),images,country);
        spinCity.setAdapter(customAdapter);

        String country = tvCountry.getText().toString();
        String address = autoCompleteTextView.getText().toString();

        int selectedGender = rdoGroup.getCheckedRadioButtonId();
        RadioButton radioGender = (RadioButton) findViewById(selectedGender);
        String gender = radioGender.getText().toString();

        User users = new User(businessName,fullName,gender,country,contactNumber,address,emailId,password,imageName);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> signUpResponseCall = usersAPI.registerUser(users);

        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this, "Code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void multiChoice(){
        //Start of Button Listener for button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore +1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(SignUpActivity.this, "Your answer is correct!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Your answer is wrong!!", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(SignUpActivity.this, "Your answer is correct !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Your answer is wrong!!", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        //End of Button Listener for Button2


        //Start of Button Listener for Button3

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(SignUpActivity.this, "Your answer is correct !!", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(SignUpActivity.this, "Your answer is wrong !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void BrowseImage() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);

    }

    private boolean validate() {
        boolean status=true;
        if (etPassword.getText().toString().length() < 6) {
            etPassword.setError("Minimum 6 character");
            status=false;
        }
        return status;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Please go through the security questions for complete registration!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }





}
