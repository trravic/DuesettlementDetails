package com.example.duesettlementdetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declare all the buttons and views
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button userSignInTV;
    private Button signUpButton;
    private ProgressDialog progressDialog;
    private Button adminTextView;
    //google firebasse auth
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() != null){
            finish();

            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }
        usernameEditText = (EditText) findViewById(R.id.username);

        passwordEditText = (EditText) findViewById(R.id.password);

        userSignInTV = (Button) findViewById(R.id.textViewSignIn);

        signUpButton = (Button) findViewById(R.id.signUpbutton);

        adminTextView = (Button) findViewById(R.id.adminTv);

        progressDialog = new ProgressDialog(this);


        signUpButton.setOnClickListener(this);
        userSignInTV.setOnClickListener(this);
        adminTextView.setOnClickListener(this);
    }




    private void registerUser() {

        String storeUsername = usernameEditText.getText().toString().trim();

        String storepwd = passwordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(storeUsername)) {
            Toast.makeText(this, "please enter the username", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(storepwd)) {
            Toast.makeText(this, "please enter the password", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Registering user ...!");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(storeUsername,storepwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"you have succesfully registered!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), profileActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"our servers are busy try again!",Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View view) {

        if(view ==signUpButton){
            registerUser();
            //stop the execution
        }

        if(view == userSignInTV){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if(view == adminTextView){
            startActivity(new Intent(this,adminActivity.class));
        }


    }
}
