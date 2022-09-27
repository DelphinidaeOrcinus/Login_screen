package com.example.login_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreenActivity extends AppCompatActivity {

    EditText edt_mail, edt_password;
    Button btn_login;
    TextView no_account;

    FirebaseAuth permission;
    String mail, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loign_screen);
        getSupportActionBar().hide();


        permission =FirebaseAuth.getInstance();

        edt_mail =findViewById(R.id.edtmail);
        edt_password =findViewById(R.id.edtPassword);
        btn_login =findViewById(R.id.btnlogin);
        no_account =findViewById(R.id.noacconut);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mail= edt_mail.getText().toString();
                 password = edt_password.getText().toString();

                if(mail.isEmpty()|| password.isEmpty()){
                    Toast.makeText(LoginScreenActivity.this, "boş alanları doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else{
                    Login();
                }
            }
        });

        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreenActivity.this, RegisterScreenActivity.class));
            }
        });
    }

    private void Login() {
        permission.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginScreenActivity.this,MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginScreenActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected  void onStart(){
        super.onStart();;
        FirebaseUser currentUser = permission.getCurrentUser();
        if(currentUser !=null){
            startActivity(new Intent(LoginScreenActivity.this,MainActivity.class));
            finish();
        }
    }

}