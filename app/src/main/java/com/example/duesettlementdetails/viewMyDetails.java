package com.example.duesettlementdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class viewMyDetails  extends AppCompatActivity {

    public static  final String TAG = "viewMyDetails";

    private FirebaseDatabase mFirebaseDb;
    private FirebaseAuth mFireAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference dbRef;
    private String userId;
    private ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_distinct_activity);

        //getting instances
        mFireAuth =  FirebaseAuth.getInstance();
        mFirebaseDb = FirebaseDatabase.getInstance();

        dbRef = mFirebaseDb.getReference();
        FirebaseUser user = mFireAuth.getCurrentUser();
        userId = user.getUid();

        mListView = (ListView)findViewById(R.id.listView);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = mFireAuth.getCurrentUser();
                    if(user != null){
                        toastFunc("successfully signed as"+ user.getEmail());
                    }
                    else{
                        toastFunc("successfully signed out!");
                    }
            }
        };

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){

        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
            storeStudentDetails sd = new storeStudentDetails();
            sd.setStudentName(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getStudentName());
            sd.setEmail(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getEmail());
            sd.setBook(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getBook());
            sd.setFine(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getFine());
            sd.setDept(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getDept());
            sd.setRollNo(dataSnapshot1.child(userId).getValue(storeStudentDetails.class).getRollNo());

            Log.d(TAG,"show data : name" +sd.getStudentName());
            Log.d(TAG,"show data: rollNum" + sd.getRollNo());

            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(sd.getStudentName());
            arrayList.add(sd.getRollNo());
            arrayList.add(String.valueOf(sd.getFine()));
            arrayList.add(sd.getDept());
            arrayList.add(String.valueOf(sd.getFine()));
            arrayList.add(sd.getBook());

            ArrayAdapter mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            mListView.setAdapter(mAdapter);

        }
    }
    @Override
    public void onStart(){
        super.onStart();
        mFireAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFireAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void toastFunc(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
