package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    DatabaseHelper databaseHelper;
    Button b1;
    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        b1=(Button) findViewById(R.id.button);
        txt1=(TextView) findViewById(R.id.textView);
        txt2=(TextView) findViewById(R.id.textView1);
        databaseHelper=new DatabaseHelper(MainActivity.this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_txt=email.getText().toString();
                String password_txt=password.getText().toString();
                if(email_txt.equals("")||password_txt.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter the required data",Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkBoth=databaseHelper.checkEmailPassword(email_txt,password_txt);
                    if(checkBoth==true){
                        Toast.makeText(MainActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,ListInfoActivity.class);
                        intent.putExtra("mail",email_txt);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }
}