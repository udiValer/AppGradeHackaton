package com.androidacademyhackaton.appgradehackaton.Database;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppGradeDatabase{

    public interface OnResultCallback {
        void callback(Object data);
    }

    private Activity mActivity;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public AppGradeDatabase(Activity activity){
        mActivity = activity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(final String firstName, final String lastName, final String email, String password, final OnResultCallback onResultCallback){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                     @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            Student student = new Student(firstName, lastName, email);
                            DatabaseReference refStudents = mDatabase.child("students");
                            refStudents.child(uid).setValue(student);
                            onResultCallback.callback(student);
                        } else {
                            onResultCallback.callback(null);
                        }
                    }
                });
    }

    public void signIn(String email, String password, final OnResultCallback onResultCallback){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getCurrentStudent(new OnResultCallback() {
                                @Override
                                public void callback(Object data) {
                                    onResultCallback.callback(data);
                                }
                            });
                        }
                        else {
                            onResultCallback.callback(null);
                        }
                    }
                });
    }

    public void getCurrentStudent(final OnResultCallback onResultCallback){
        final String uid = mAuth.getCurrentUser().getUid();
        if(uid == null){
            onResultCallback.callback(null);
        }
        DatabaseReference refStudents = mDatabase.child("students/" + uid);
        refStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                onResultCallback.callback(student);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                onResultCallback.callback(null);
            }
        });
    }
}
