package com.example.prerana.comprehensiveretailsolution.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.MainActivity;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.bll.LoginBLL;
import com.example.prerana.comprehensiveretailsolution.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {


    //Alert
    AlertDialog.Builder builder;

    private Button btnSignUp;
    private Button btnLogInUser;

    private EditText etEmailAddress;
    private EditText etPasswordCheck;

    private TextView tvNoAttempts;
    private TextView tvTimer;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);


        builder = new AlertDialog.Builder(this);

        tvTimer = findViewById(R.id.tvTimer);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogInUser = (Button) findViewById(R.id.btnLogInUser);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPasswordCheck = findViewById(R.id.etPasswordCheck);
        tvNoAttempts = findViewById(R.id.tvNoAttempts);
        tvNoAttempts.setVisibility(View.GONE);


        tvTimer.setVisibility(View.GONE);

        btnLogInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmailAddress.getText())) {
                    etEmailAddress.setError("Enter your email address");
                    etEmailAddress.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(etPasswordCheck.getText())) {
                    etPasswordCheck.setError("Enter your password");
//                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    vibrator.vibrate(800);
                    etPasswordCheck.requestFocus();
                    return;
                } else {
                    login();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });
    }

    private void login() {

        String emailId = etEmailAddress.getText().toString().trim();
        String password = etPasswordCheck.getText().toString().trim();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(emailId, password)) {
            Toast.makeText(this, "Login Success !! ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("User", databaseList());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etEmailAddress.requestFocus();
            tvNoAttempts.setVisibility(View.VISIBLE);
            counter--;
            tvNoAttempts.setText(Integer.toString(counter));

            if (counter == 0){
                btnLogInUser.setEnabled(false);
               // btnLogInUser.setBackgroundColor(Color.rgb(199,99,230));
                btnLogInUser.setTextColor(Color.TRANSPARENT);
            //   Toast.makeText(this, "Your attempt for login is finished, please try again after 60 second!!..", Toast.LENGTH_SHORT).show();


                builder.setMessage("Your attempt for login is finished, please try again after 60 second!!..")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                btnLogInUser.setEnabled(false);
                                Toast.makeText(LoginActivity.this, "Login again with correct credentials after 60 seconds !!", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                startActivity(intent);
                               // finish();
                                tvTimer.setVisibility(View.VISIBLE);
                                new CountDownTimer(18000,1000){

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        tvTimer.setText("Time remaining : "+millisUntilFinished/1000 + " " +"s");
                                    }

                                    @Override
                                    public void onFinish() {
                                        tvTimer.setText("Timer stopped!!");
                                    }
                                }.start();
                                dialog.cancel();

                                }
                        })
                     ;

                //creating dialog box
                AlertDialog alert = builder.create();
                alert.setTitle(" Invalid Login ");
                alert.show();


               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try{
                           Thread.sleep(1*20*1000);
                       }catch(InterruptedException e){
                           e.printStackTrace();
                       }

                       LoginActivity.this.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               btnLogInUser.setEnabled(true);
                               btnLogInUser.setTextColor(Color.WHITE);
                               tvNoAttempts.setTextColor(Color.TRANSPARENT);

                           }
                       });
                   }
               }).start();
//
            }
        }
    }


}
