package com.example.duesettlementdetails;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_uname_EditText;
    private EditText login_passwordEditText;
    private Button LoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        login_uname_EditText = (EditText) findViewById(R.id.admin_username_login);
        login_passwordEditText=(EditText)findViewById(R.id.admin_password_login);
        LoginButton =(Button)findViewById(R.id.admin_loginbutton);



        LoginButton.setOnClickListener(this);




    }
    private void adminLogin(){


        String storeUserName =  login_uname_EditText.getText().toString().trim();
        String storepwd =  login_passwordEditText.getText().toString().trim();

        String checkUser = "admin";
        String checkpwd = "admin";

        if(TextUtils.isEmpty(storeUserName)){
            Toast.makeText(this,"please enter the user name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(storepwd)){
            Toast.makeText(this,"please enter the password",Toast.LENGTH_SHORT).show();
        }


            if(storeUserName.equals(checkUser)&& storepwd.equals(checkpwd)) {
                Toast.makeText(this,"success!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), adminProfile.class));
                finish();
            }
            else {
                Toast.makeText(this,"Invalid details",Toast.LENGTH_SHORT).show();
            }

    }


    @Override
    public void onClick(View view) {

        if(view == LoginButton){
            adminLogin();
        }
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure want to exit while saving?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adminActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
