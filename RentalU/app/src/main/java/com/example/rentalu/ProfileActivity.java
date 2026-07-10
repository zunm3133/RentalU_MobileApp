package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    EditText email,password,color,movie;
    String mail;
    String emailAdd;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email=(EditText) findViewById(R.id.ed1);
        password=(EditText) findViewById(R.id.ed2);
        color=(EditText) findViewById(R.id.ed3);
        movie=(EditText) findViewById(R.id.ed4);
        mail=getIntent().getStringExtra("mailing");
        databaseHelper=new DatabaseHelper(this);
        emailAdd=mail;
        email.setText(emailAdd);
        Boolean checkUserEmail=databaseHelper.checkEmail(emailAdd);
        if(checkUserEmail==true){
            Cursor result=databaseHelper.showAll(emailAdd);
            while(result.moveToNext()){
                password.setText(result.getString(1));
                color.setText(result.getString(2));
                movie.setText(result.getString(3));
            }
        }else{
            Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
        }
    }
}