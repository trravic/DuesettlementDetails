package com.example.duesettlementdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class update_details_activity extends AppCompatActivity implements View.OnClickListener {

    private EditText studentEt;
    private EditText rollEt;
    private Spinner mdeptSpinner;
    private EditText bookEt;
    private EditText fineAmtEt;
    private EditText emailId;
    private Button updateBtn;
    private int selectedSpinner;


    private FirebaseFirestore holddatabaseReference;

    //again we have to update and we must have to store
    private storeStudentDetails mStoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_details_admin);
        holddatabaseReference = FirebaseFirestore.getInstance();
        mStoreDetails = (storeStudentDetails) getIntent().getSerializableExtra("details");

        studentEt = (EditText) findViewById(R.id.studentName);
        rollEt = (EditText) findViewById(R.id.rollNumber);
        mdeptSpinner = (Spinner) findViewById(R.id.deptSpinner);
        bookEt = (EditText) findViewById(R.id.bookName);
        fineAmtEt = (EditText) findViewById(R.id.fineAmt);
        emailId = (EditText) findViewById(R.id.emailId);

        updateBtn = (Button) findViewById(R.id.update_details);

        studentEt.setText(mStoreDetails.getStudentName());
        rollEt.setText(mStoreDetails.getRollNo());
        // mdeptSpinner.setSelected(mStoreDetails.getDept());
       selectedSpinner = mdeptSpinner.getSelectedItemPosition();
       mdeptSpinner.setSelection(selectedSpinner);
        bookEt.setText(String.valueOf(mStoreDetails.getBook()));
        fineAmtEt.setText(String.valueOf(mStoreDetails.getFine()));
        emailId.setText(mStoreDetails.getEmail());

        updateBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_details:
                updateDetails();
        }
    }

    private boolean hasValidationErrors(String name, String rollNo, String book, String fine ,String dept,String email) {

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
        if (email.isEmpty()) {
            emailId.setError("Name required");
            emailId.requestFocus();
            return true;
        }


        return false;
    }

    private void updateDetails() {


        String name = studentEt.getText().toString().trim();
        String rollNo = rollEt.getText().toString().trim();
        String book = bookEt.getText().toString().trim();
        String fine = fineAmtEt.getText().toString().trim();
        String email = emailId.getText().toString().trim();
        String dept = mdeptSpinner.getSelectedItem().toString();

        if (!hasValidationErrors(name, rollNo, book, fine, dept, email)) {

            storeStudentDetails StoreDetails = new storeStudentDetails(
                    name,
                    rollNo,
                    book,
                    Double.parseDouble(fine),
                    dept,
                    email
            );
           holddatabaseReference.collection("details").document(StoreDetails.getId())
                .update("name",StoreDetails.getStudentName(),
                                "book",StoreDetails.getBook(),
                                "dept",StoreDetails.getDept(),
                                "email",StoreDetails.getEmail(),
                                    "rollNo",StoreDetails.getRollNo(),
                                            "fine",StoreDetails.getFine()
                )
                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(update_details_activity.this,"details updated!",Toast.LENGTH_SHORT).show();
                       }
                   });



        }
    }
}

