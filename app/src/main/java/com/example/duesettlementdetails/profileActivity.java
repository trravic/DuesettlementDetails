package com.example.duesettlementdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class profileActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView logOutBtn;
    private TextView userTv;
    private FirebaseAuth mFireAuth;
    private CardView ViewBtn;
    private CardView payActivity;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);



        logOutBtn=(CardView) findViewById(R.id.logoutbtn);
        userTv=(TextView)findViewById(R.id.userProfile);
        ViewBtn = (CardView) findViewById(R.id.view_details);
        payActivity = (CardView) findViewById(R.id.pay_Activity);
        mFireAuth=FirebaseAuth.getInstance();

        if(mFireAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }


        payActivity.setOnClickListener(this);


        FirebaseUser user=mFireAuth.getCurrentUser();
        String user_detail = user.getEmail();
        userTv.setText("Welcome Buddy ," + user_detail);

        logOutBtn.setOnClickListener(this);
        ViewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==logOutBtn)
        {
            mFireAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        if(view == payActivity){
            startActivity(new Intent(this,paymentAmtActivity.class));
        }


        if(view==ViewBtn){
            startActivity(new Intent(this,studentDetailsRecyclerActivity.class));
        }




    }
}
