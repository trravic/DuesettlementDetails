package com.example.duesettlementdetails;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class profileDistinctAdapter extends RecyclerView.Adapter<profileDistinctAdapter.profileViewHolder>{

    private Context context;
    private List<storeStudentDetails> studentDetailsList;
    private Button notifyBtn;

    private String Name;
    private String  RollNumber;
    private String Dept;
    private String  bookNAme;
    private String FineAmt;
    private String  EmailId;


    FirebaseAuth mFireAuth;

    FirebaseUser user = mFireAuth.getCurrentUser();


    public profileDistinctAdapter(Context context, List<storeStudentDetails> studentDetailsList) {
        this.context = context;
        this.studentDetailsList = studentDetailsList;
    }

    @NonNull
    @Override
    public profileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new profileViewHolder(
                LayoutInflater.from(context).inflate(R.layout.profile_distinct_activity, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull profileViewHolder holder, int position) {
        storeStudentDetails mStoreDetails = studentDetailsList.get(position);


        String user_detail = user.getEmail();
        holder.userTv.setText("Welcome Buddy ," + user_detail);


        EmailId = mStoreDetails.getEmail();

        if (user_detail.equals(EmailId)) {


            holder.studName.setText(mStoreDetails.getStudentName());
            holder.email.setText(mStoreDetails.getEmail());
            holder.rollNum.setText(mStoreDetails.getRollNo());
            holder.bookName.setText(mStoreDetails.getBook());
            holder.fine.setText("Fine:" + mStoreDetails.getFine());
            holder.dept.setText(mStoreDetails.getDept());

        }
        else {

            holder.studName.setText(mStoreDetails.getStudentName());

            holder.fine.setText("You have no fine , thanks !");
        }

    }







    @Override
    public int getItemCount() {
        return studentDetailsList.size();
    }



    class profileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView studName,rollNum,bookName,dept,fine,email,userTv;

        private FirebaseAuth mFireAuth;


        public profileViewHolder(View itemView) {
            super(itemView);

            userTv = itemView.findViewById(R.id.user_textView);
            studName=itemView.findViewById(R.id.studentName_prof);
            rollNum = itemView.findViewById(R.id.rollNumber_prof);
            email = itemView.findViewById(R.id.email_prof);
            bookName = itemView.findViewById(R.id.bookName_prof);
            fine = itemView.findViewById(R.id.fineAmt_prof);
            dept = itemView.findViewById(R.id.department_prof);
            notifyBtn = itemView.findViewById(R.id.notify);


            itemView.setOnClickListener(this);
            notifyBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            storeStudentDetails sd = studentDetailsList.get(getAdapterPosition());
            switch (view.getId()){
                //why switch in future it might be useful
                case R.id.notify:

                    String name = sd.getStudentName();
                    String rollNumber = sd.getRollNo();
                    String department = sd.getDept();
                    String book = sd.getBook();

                    String Fine = String.valueOf(sd.getFine());
                    String email = sd.getEmail();

                    String Generatemessage = "Name : " + name  +"\n" +
                            "Roll Number : " + rollNumber + "\n" +
                            "Department : "+  department + "\n" +
                            "Book : "+ book + "\n" +
                            "Fine : "+ Fine + "\n" ;


                    Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                    mailIntent.setData(Uri.parse("mailto:" + sd.getEmail()));
                    mailIntent.putExtra(Intent.EXTRA_SUBJECT,"Pay immediately -  " + sd.getFine());
                    mailIntent.putExtra(Intent.EXTRA_TEXT,Generatemessage);

                    PackageManager pm = context.getPackageManager();
                    if (mailIntent.resolveActivity(pm) != null) {
                        context.startActivity(mailIntent);
                    }
                    break;

                default:
                    Intent intent = new Intent(context,update_details_activity.class);
                    intent.putExtra("details",sd);
                    context.startActivity(intent);
            }
        }


    }


}
