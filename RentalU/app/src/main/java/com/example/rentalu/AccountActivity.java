package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {
    EditText email,password,color,movie;
    DatabaseHelper databaseHelper;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        email=(EditText) findViewById(R.id.ed1);
        password=(EditText) findViewById(R.id.ed2);
        color=(EditText) findViewById(R.id.ed3);
        movie=(EditText) findViewById(R.id.ed4);
        b1=(Button) findViewById(R.id.button);
        databaseHelper=new DatabaseHelper(AccountActivity.this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_txt=email.getText().toString();
                String password_txt=password.getText().toString();
                String color_txt=color.getText().toString();
                String movie_txt=movie.getText().toString();
                if(email_txt.equals("") || password_txt.equals("") || color_txt.equals("") || movie_txt.equals("")){
                    Toast.makeText(AccountActivity.this, "Enter all data", Toast.LENGTH_SHORT).show();
                }else if(password_txt.length()<5){
                    Toast.makeText(AccountActivity.this, "Make your password longer", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUserEmail=databaseHelper.checkEmail(email_txt);
                    if(checkUserEmail==false){
                        Boolean insert=databaseHelper.insertData(email_txt,password_txt,color_txt,movie_txt);
                        if(insert==true){
                            Toast.makeText(AccountActivity.this, "Account creation successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AccountActivity.this, "Creation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AccountActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}