package com.example.duesettlementdetails;


import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

public class adminDetailsAdapter extends RecyclerView.Adapter<adminDetailsAdapter.adminViewHolder>{

    private Context context;
    private List<storeStudentDetails> studentDetailsList;

    public adminDetailsAdapter(Context context, List<storeStudentDetails> studentDetailsList) {
        this.context = context;
        this.studentDetailsList = studentDetailsList;
    }

    @NonNull
    @Override
    public adminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adminViewHolder(
                LayoutInflater.from(context).inflate(R.layout.view_details, parent, false)
        );
    }



    @Override
    public void onBindViewHolder(@NonNull adminViewHolder holder, int position) {
        storeStudentDetails mStoreDetails = studentDetailsList.get(position);

        holder.studName.setText(mStoreDetails.getStudentName());
        holder.email.setText( mStoreDetails.getEmail());
        holder.rollNum.setText(mStoreDetails.getRollNo());
        holder.bookName.setText( mStoreDetails.getBook());
        holder.fine.setText("Fine:" + mStoreDetails.getFine());
        holder.dept.setText(mStoreDetails.getDept());

    }

    @Override
    public int getItemCount() {
        return studentDetailsList.size();
    }

    class adminViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView studName,rollNum,bookName,dept,fine,email;


        public adminViewHolder(View itemView) {
            super(itemView);

            studName=itemView.findViewById(R.id.studentName_prof);
            rollNum = itemView.findViewById(R.id.rollNumber_prof);
            email = itemView.findViewById(R.id.email_prof);
            bookName = itemView.findViewById(R.id.bookName_prof);
            fine = itemView.findViewById(R.id.fineAmt_prof);
            dept = itemView.findViewById(R.id.department_prof);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            storeStudentDetails sd = studentDetailsList.get(getAdapterPosition());
            Intent intent = new Intent(context,update_details_activity.class);
            intent.putExtra("details",sd);
            context.startActivity(intent);
        }
    }
}
