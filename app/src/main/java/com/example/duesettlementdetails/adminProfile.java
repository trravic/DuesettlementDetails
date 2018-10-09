package com.example.duesettlementdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminProfile extends AppCompatActivity {

    private EditText studentEt;
    private EditText rollEt;
    private Spinner mdeptSpinner;
    private EditText bookEt;
    private EditText fineAmtEt;
    private Button saveBtn;

    //google realtime database
    DatabaseReference holddatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        //reference will be acting as the root node
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        holddatabaseReference = database.getInstance().getReference("details");

        studentEt = (EditText)findViewById(R.id.studentName);
        rollEt = (EditText) findViewById(R.id.rollNumber);
        mdeptSpinner = (Spinner) findViewById(R.id.deptSpinner);
        bookEt = (EditText) findViewById(R.id.bookName);
        fineAmtEt = (EditText) findViewById(R.id.fineAmt);
        saveBtn = (Button) findViewById(R.id.save_details);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

    }

    private void saveDetails(){
        String name = studentEt.getText().toString().trim();
        String rollno = rollEt.getText().toString().trim();
        String book = bookEt.getText().toString().trim();
        String fine = fineAmtEt.getText().toString().trim();

        String dept = mdeptSpinner.getSelectedItem().toString();

        if((!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(rollno)) && (!TextUtils.isEmpty(book))
            && (!TextUtils.isEmpty(fine)) ){

            String id = holddatabaseReference.push().getKey();

            storeStudentDetails obj = new storeStudentDetails(id,name,rollno,book,fine,dept);

            holddatabaseReference.child(id).setValue(obj);

            Toast.makeText(this,"added successfully",Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this,"All the details are mandatory",Toast.LENGTH_SHORT).show();
        }

    }



}
