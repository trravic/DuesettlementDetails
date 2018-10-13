package com.example.duesettlementdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

public class storeDetailsAdapter extends RecyclerView.Adapter<storeDetailsAdapter.StudentViewHolder>{

    private Context context;
    private List<storeStudentDetails> studentDetailsList;

    public storeDetailsAdapter(Context context, List<storeStudentDetails> studentDetailsList) {
            this.context = context;
            this.studentDetailsList = studentDetailsList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(
                LayoutInflater.from(context).inflate(R.layout.profile_activity, parent, false)
        );
    }



    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        storeStudentDetails mStoreDetails = studentDetailsList.get(position);

        holder.studName.setText(mStoreDetails.getStudentName());
        holder.rollNum.setText(mStoreDetails.getRollNo());
        holder.bookName.setText( mStoreDetails.getBook());
        holder.fine.setText("Fine:" + mStoreDetails.getFine());
        holder.dept.setText(mStoreDetails.getDept());

    }



    @Override
    public int getItemCount() {
        return studentDetailsList.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView studName,rollNum,bookName,dept,fine;


        public StudentViewHolder(View itemView) {
            super(itemView);

            studName=itemView.findViewById(R.id.studentName_prof);
            rollNum = itemView.findViewById(R.id.rollNumber_prof);
            bookName = itemView.findViewById(R.id.bookName_prof);
            fine = itemView.findViewById(R.id.fineAmt_prof);
            dept = itemView.findViewById(R.id.department_prof);
        }
    }
}
