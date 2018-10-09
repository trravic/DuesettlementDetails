package com.example.duesettlementdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logOutBtn;
    private TextView userTv;
    private FirebaseAuth mFireAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        logOutBtn=(Button)findViewById(R.id.logoutbtn);
        userTv=(TextView)findViewById(R.id.userProfile);

        mFireAuth=FirebaseAuth.getInstance();
        if(mFireAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user=mFireAuth.getCurrentUser();
        userTv.setText("Welcome "+user.getEmail());

        logOutBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==logOutBtn)
        {
            mFireAuth.signOut();

            finish();

            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
