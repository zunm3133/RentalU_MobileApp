package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetActivity extends AppCompatActivity {
    EditText color,movie,email,password;
    Button b1;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        color=(EditText) findViewById(R.id.ed1);
        movie=(EditText) findViewById(R.id.ed2);
        email=(EditText) findViewById(R.id.ed3);
        b1=(Button) findViewById(R.id.button);
        password=(EditText) findViewById(R.id.ed4);
        databaseHelper=new DatabaseHelper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color_txt=color.getText().toString();
                String movie_txt=movie.getText().toString();
                String email_txt=email.getText().toString();
                if(color_txt.equals("")||movie_txt.equals("")||email_txt.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter all data",Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkForget=databaseHelper.checkColorMovieEmail(email_txt,color_txt,movie_txt);
                    if(checkForget==true){
                        Cursor result=databaseHelper.showPassword(email_txt);
                        while(result.moveToNext()){
                            password.setText(result.getString(1));
                        }
                    }else{
                        Toast.makeText(ForgetActivity.this, "No related data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}