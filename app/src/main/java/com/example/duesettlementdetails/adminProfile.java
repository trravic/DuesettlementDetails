package com.example.duesettlementdetails;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class adminProfile extends AppCompatActivity implements View.OnClickListener {

    private EditText studentEt;
    private EditText rollEt;
    private Spinner mdeptSpinner;
    private EditText bookEt;
    private EditText fineAmtEt;
    private Button saveBtn;

    //google realtime database
   private FirebaseFirestore holddatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        //reference will be acting as the root node


        holddatabaseReference = FirebaseFirestore.getInstance();

        studentEt = (EditText) findViewById(R.id.studentName);
        rollEt = (EditText) findViewById(R.id.rollNumber);
        mdeptSpinner = (Spinner) findViewById(R.id.deptSpinner);
        bookEt = (EditText) findViewById(R.id.bookName);
        fineAmtEt = (EditText) findViewById(R.id.fineAmt);
        saveBtn = (Button) findViewById(R.id.save_details);

        saveBtn.setOnClickListener(this);
    }

    private boolean hasValidationErrors(String name, String rollNo, String book, String fine ,String dept) {

        if (name.isEmpty()) {
            studentEt.setError("Name required");
            studentEt.requestFocus();
            return true;
        }

        if (rollNo.isEmpty()) {
            rollEt.setError("Name required");
            rollEt.requestFocus();
            return true;
        }
        if (book.isEmpty()) {
            bookEt.setError("Name required");
            bookEt.requestFocus();
            return true;
        }
        if (fine.isEmpty()) {
            fineAmtEt.setError("Name required");
            fineAmtEt.requestFocus();
            return true;
        }


        return false;
    }

    private void saveDetails() {

        String name = studentEt.getText().toString().trim();
        String rollNo = rollEt.getText().toString().trim();
        String book = bookEt.getText().toString().trim();
        String fine = fineAmtEt.getText().toString().trim();

        String dept = mdeptSpinner.getSelectedItem().toString();

        if (!hasValidationErrors(name, rollNo, book, fine ,dept)) {

            CollectionReference dbProducts = holddatabaseReference.collection("details");

            storeStudentDetails product = new storeStudentDetails(
                    name,
                    rollNo,
                    book,
                    Double.parseDouble(fine),
                    dept

            );

            dbProducts.add(product)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(adminProfile.this, "Successfully Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(adminProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
        if(view == saveBtn){
            saveDetails();
        }
    }
}