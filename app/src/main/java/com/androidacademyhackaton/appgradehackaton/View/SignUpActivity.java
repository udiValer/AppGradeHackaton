package com.androidacademyhackaton.appgradehackaton.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidacademyhackaton.appgradehackaton.Database.AppGradeDatabase;
import com.androidacademyhackaton.appgradehackaton.Model.MySharedPref;
import com.androidacademyhackaton.appgradehackaton.Model.Student;
import com.androidacademyhackaton.appgradehackaton.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Udi on 3/15/2018.
 */

public class SignUpActivity extends Activity {

    private AppGradeDatabase database;
    private ProgressDialog progressDialog;
    private TextInputEditText txtFirstName;
    private TextInputEditText txtLastName;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_layout);

        txtFirstName = findViewById(R.id.signInFirstNameTxt);
        txtLastName = findViewById(R.id.signInLastNameTxt);
        txtEmail = findViewById(R.id.signInEmailTxt);
        txtPassword = findViewById(R.id.signInPasswordTxt);
        btnRegister = findViewById(R.id.signInSignBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInputs()){
                     showProcessDialog();
                     database = new AppGradeDatabase(SignUpActivity.this);
                     database.signUp(txtFirstName.getText().toString(), txtLastName.getText().toString(),
                             txtEmail.getText().toString(), txtPassword.getText().toString(), new AppGradeDatabase.OnResultCallback() {
                                 @Override
                                 public void callback(Object data) {
                                     Student student = (Student)data;
                                     if(student == null){
                                         //TODO: Set error message. For now Toast
                                         progressDialog.cancel();
                                         Toast.makeText(SignUpActivity.this , R.string.signUpFailed , Toast.LENGTH_LONG).show();
                                     }
                                     else {
                                         writeStudentToSharedPref(student);
                                         progressDialog.cancel();
                                         startActivity(new Intent(SignUpActivity.this , AddCourseActivity.class));
                                     }
                                 }
                             });
                }
            }
        });
    }

    private boolean validateInputs(){
        if (!isValidName(txtFirstName.getText().toString() , txtLastName.getText().toString())) {
            txtEmail.setError(getResources().getString(R.string.NameIsNotValid));
            return false;
        }

        if (!isValidEmail(txtEmail.getText().toString())) {
            txtEmail.setError(getResources().getString(R.string.EmailNotValid));
            return false;
        }

        if(!isValidPassword(txtPassword.getText().toString())) {
            txtPassword.setError(getResources().getString(R.string.PasswordTooShort));
            return false;
        }

        return true;
    }

    private boolean isValidName(String firstName, String lastName) {
        if(firstName != null && !(firstName.matches("[0-9]+")) && lastName != null && !(lastName.matches("[0-9]+"))){
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    private void showProcessDialog(){
        progressDialog = ProgressDialog.show(SignUpActivity.this , "AppGrade" , "מבצע הרשמה...");
    }

    private void writeStudentToSharedPref(Student student){
        MySharedPref mySharedPref = new MySharedPref(getApplicationContext());
        mySharedPref.setFirstName(student.firstName);
        mySharedPref.setLastName(student.lastName);
        mySharedPref.setEmail(student.email);
        mySharedPref.setPassword(txtPassword.getText().toString());
    }


}
