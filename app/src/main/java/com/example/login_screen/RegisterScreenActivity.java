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

public class RegisterScreenActivity extends AppCompatActivity {

    EditText edtmail, edtPassword;
    Button btnRegister;
    TextView account;

    FirebaseAuth auth;
    String mail, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        getSupportActionBar().hide();

        auth =FirebaseAuth.getInstance();

        edtmail=findViewById(R.id.edt_mail_register);
        edtPassword =findViewById(R.id.edt_password_register);
        btnRegister =findViewById(R.id.btn_register);
        account =findViewById(R.id.account);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreenActivity.this, LoginScreenActivity.class));
                finish();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 mail=edtmail.getText().toString();
                 password = edtPassword.getText().toString();

                if(mail.isEmpty()|| password.isEmpty()){
                    Toast.makeText(RegisterScreenActivity.this, "boş alanları doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else{
                    Register();
                }
            }
        });



}

    private void Register() {
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//kayıt başarılı ise
                if(task.isSuccessful()){
                    startActivity(new Intent(RegisterScreenActivity.this,MainActivity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//başarısız ise
                Toast.makeText(RegisterScreenActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}