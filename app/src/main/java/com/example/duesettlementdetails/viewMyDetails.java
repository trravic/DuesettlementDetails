package com.example.duesettlementdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class viewMyDetails  extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private storeDetailsAdapter mStoreDetailsAdapter;

    private List<storeStudentDetails> studentDetailsList;

    private FirebaseFirestore dbReference;

    private FirebaseAuth mFireAuth;

    private ProgressBar mProgressBar;

    private  TextView userSignIn;
    private TextView emailTv;
    private TextView nameTV;
    private TextView rollTv;
    private TextView deptTv;
    private TextView bookTv;

    private TextView fineTv;


    private String TAG = studentDetailsRecyclerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbReference = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_details);

        mFireAuth = FirebaseAuth.getInstance();

        String emailId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore dbReference = FirebaseFirestore.getInstance();
        mProgressBar = findViewById(R.id.progressbar);

        userSignIn = (TextView)findViewById(R.id.userProfile_details);
        emailTv = (TextView)findViewById(R.id.emailId_details);
        nameTV = (TextView)findViewById(R.id.studentName_details);
        rollTv = (TextView)findViewById(R.id.rollNumber_details);
        deptTv = (TextView)findViewById(R.id.dept_Details);
        bookTv =(TextView)findViewById(R.id.bookName_details);
        fineTv = (TextView)findViewById(R.id.fineAmt_details);

        userSignIn.setText("Welcome  You are Logged in as "+emailId);

         //mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_products);
        //mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentDetailsList = new ArrayList<>();

      //   mStoreDetailsAdapter = new storeDetailsAdapter(this,studentDetailsList);

        //mRecyclerView.setAdapter(mStoreDetailsAdapter);



        //to get the "details" this is our collection from firestore so we must fetch them
        //by calling the addOnSuccessListener
        CollectionReference collectionReference = dbReference.collection("details");
        Query emailQuery = collectionReference.whereEqualTo("email",emailId);
        emailQuery.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        //we must have to hide the progress bar when the data gets loaded

                        //here queryDocumentsSnapshot will hold all the "details" which is your collection in firestore

                        if(!queryDocumentSnapshots.isEmpty()){

                            //mProgressBar.setVisibility(View.GONE);
                            //we must have to create empty list so that to store all
                            //details from DocumentsSnapshots
                            List<DocumentSnapshot>  list =  queryDocumentSnapshots.getDocuments();
                            //enhanced for loop because we have to give every index documentSnapShot
                            for(DocumentSnapshot d: list){
                                storeStudentDetails sd = d.toObject(storeStudentDetails.class);
                                nameTV.setText(sd.getStudentName());
                                rollTv.setText(sd.getRollNo());
                                deptTv.setText(sd.getDept());
                                fineTv.setText(sd.getConvert_fine());//here typecasted from double to string in storeStudentDetails getter setter method
                                bookTv.setText(sd.getBook());
                                emailTv.setText(sd.getEmail());

                                Log.d(TAG, "onSuccess: " + sd.toString());
                            }
                            //to refresh and sync we must have to use notifyDataSetChanged

                           // mStoreDetailsAdapter.notifyDataSetChanged();
                        }
                        else{
                            //mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "you have no details!!!", Toast.LENGTH_LONG).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                    }
                });
    }
   /* @Override
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
  */

}
