package com.example.duesettlementdetails;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class studentDetailsRecyclerActivity extends AppCompatActivity {

    //recyclerview  to set the details for UI in the student profile activity

    private RecyclerView mRecyclerView;
    private storeDetailsAdapter mStoreDetailsAdapter;

    private List<storeStudentDetails> studentDetailsList;

    private FirebaseFirestore dbReference;

    private ProgressBar mProgressBar;

    private String TAG = studentDetailsRecyclerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbReference = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_details);

        mProgressBar = findViewById(R.id.progressbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_products);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentDetailsList = new ArrayList<>();

        mStoreDetailsAdapter = new storeDetailsAdapter(this,studentDetailsList);

        mRecyclerView.setAdapter(mStoreDetailsAdapter);



        //to get the "details" this is our collection from firestore so we must fetch them
        //by calling the addOnSuccessListener

        dbReference.collection("details").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {



                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        //we must have to hide the progress bar when the data gets loaded

                        //here queryDocumentsSnapshot will hold all the "details" which is your collection in firestore

                        if(!queryDocumentSnapshots.isEmpty()){

                            //we must have to create empty list so that to store all
                            //details from DocumentsSnapshots
                            List<DocumentSnapshot>  list =  queryDocumentSnapshots.getDocuments();



                            //enhanced for loop because we have to give every index documentSnapShot

                            for(DocumentSnapshot d: list){

                                storeStudentDetails sd = d.toObject(storeStudentDetails.class);
                                studentDetailsList.add(sd);


                                Log.d(TAG, "onSuccess: " + sd.toString());




                            }

                            //to refresh and sync we must have to use notifyDataSetChanged

                            mStoreDetailsAdapter.notifyDataSetChanged();
                        }
                    }
                })  .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
