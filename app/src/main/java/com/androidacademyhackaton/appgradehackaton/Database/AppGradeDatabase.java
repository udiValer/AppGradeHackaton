package com.androidacademyhackaton.appgradehackaton.Database;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.androidacademyhackaton.appgradehackaton.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
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

    public void createUser(final String firstName, final String lastName, String email, String password, final OnResultCallback onResultCallback){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                     @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            String studentId = writeNewStudent(firstName, lastName, firebaseUser.getUid());
                            writeNewUser(firebaseUser.getUid(), firebaseUser.getEmail(), studentId);
                            onResultCallback.callback(firebaseUser.getUid());
                        } else {
                            Toast.makeText(mActivity, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signInUser(String email, String password, final OnResultCallback onResultCallback){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            onResultCallback.callback(firebaseUser.getUid());
                        } else {
                            Toast.makeText(mActivity, "Sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void getCurrentUser(final OnResultCallback onResultCallback){
        final String currUserId = mAuth.getCurrentUser().getUid();
        if(currUserId == null){
            onResultCallback.callback(null);
        }
        DatabaseReference refUsers = mDatabase.child("users/" + currUserId);
        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onResultCallback.callback(dataSnapshot.getValue(User.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                onResultCallback.callback(null);
            }
        });
    }

    public void getCurrentStudent(final OnResultCallback onResultCallback){
        final String currUserId = mAuth.getCurrentUser().getUid();
        if(currUserId == null){
            onResultCallback.callback(null);
        }
        DatabaseReference refUsers = mDatabase.child("users/" + currUserId);
        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                DatabaseReference refStudents = mDatabase.child("students/" + user.studentId);
                refStudents.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        onResultCallback.callback(dataSnapshot.getValue(Student.class));                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        onResultCallback.callback(null);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                onResultCallback.callback(null);
            }
        });
    }

    private void writeNewUser(String userId, String email, String studentId){
        User user = new User(email, studentId);
        DatabaseReference refUsers = mDatabase.child("users");
        refUsers.child(userId).setValue(user);
    }

    private String writeNewStudent(String firstName, String lastName, String userId) {
        Student student = new Student(firstName, lastName, userId);
        DatabaseReference refStudents = mDatabase.child("students");
        String studentId = refStudents.push().getKey();
        refStudents.child(studentId).setValue(student);
        return studentId;
    }

}
