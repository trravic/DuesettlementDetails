package com.example.duesettlementdetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    //declare all the buttons and views
    private EditText login_uname_EditText;
    private EditText login_passwordEditText;
    private TextView login_userSignUp_TV;
    private Button LoginButton;
    private ProgressDialog progressDialog;

    //google firebasse auth
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login_uname_EditText = (EditText)findViewById(R.id.username_login);

        login_passwordEditText = (EditText)findViewById(R.id.password_login);

        LoginButton = (Button) findViewById(R.id.loginbutton);

        login_userSignUp_TV = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), profileActivity.class));
        }

        LoginButton.setOnClickListener(this);
        login_userSignUp_TV.setOnClickListener(this);
    }

    private void userLogin(){

       String storeUserName =  login_uname_EditText.getText().toString().trim();
        String storepwd =  login_passwordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(storeUserName)){
            Toast.makeText(this,"please enter the user name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(storepwd)){
            Toast.makeText(this,"please enter the password",Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("logging in ! thanks for your patience!");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(storeUserName,storepwd ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                   finish();
                   startActivity(new Intent(getApplicationContext(),profileActivity.class));
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
            if(view == LoginButton){
                userLogin();
            }

        if(view == login_userSignUp_TV){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
