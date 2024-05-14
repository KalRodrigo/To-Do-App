package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText etUser,etpwd, etRepwd;
    Button btnRegister, btnGoToLogin;
    DataHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        etUser=findViewById(R.id.username);
        etpwd=findViewById(R.id.password);
        etRepwd=findViewById(R.id.repassword);
        btnRegister=findViewById(R.id.btnRegister);
        dbHelper=new DataHelper(this);
        btnGoToLogin=findViewById(R.id.btnGoToLogin);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd, rePwd;
                user=etUser.getText().toString();
                pwd=etpwd.getText().toString();
                rePwd=etRepwd.getText().toString();
                if(user.equals("")||pwd.equals("")||rePwd.equals("")){
                    Toast.makeText(Register.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else {
                    if (pwd.equals(rePwd)) {
                        //Proceed with registration
                        if(dbHelper.checkusername(user)){
                            Toast.makeText(Register.this, "Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper.insertData(user, pwd);
                        if (registeredSuccess)
                            Toast.makeText(Register.this, "User Registered Successfully!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Register.this, "User Registration Failed!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Register.this, "Password do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
