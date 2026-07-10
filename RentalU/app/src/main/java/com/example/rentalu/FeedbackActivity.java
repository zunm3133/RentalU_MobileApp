package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    EditText name,mail,subject,feed;
    String mailing;
    RatingBar rt;
    Button btn,returnbtn;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mailing=getIntent().getStringExtra("mailing");
        name=(EditText) findViewById(R.id.name);
        mail=(EditText) findViewById(R.id.mail);
        subject=(EditText) findViewById(R.id.subject);
        returnbtn=(Button)findViewById(R.id.return_btn);
        feed=(EditText) findViewById(R.id.feed);
        rt=(RatingBar) findViewById(R.id.rating);
        btn=(Button) findViewById(R.id.btn);
        mail.setText(mailing);
        databaseHelper=new DatabaseHelper(FeedbackActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_txt=name.getText().toString();
                String mail_txt=mail.getText().toString();
                String subject_txt=subject.getText().toString();
                String feed_txt=feed.getText().toString();
                Float rating=rt.getRating();
                if(name_txt.equals("")||mail_txt.equals("")||subject_txt.equals("")||feed_txt.equals("")){
                    Toast.makeText(FeedbackActivity.this, "Enter the required data", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean insert=databaseHelper.Feedback(name_txt,mail_txt,subject_txt,feed_txt,rating);
                    if(insert==true){
                        Toast.makeText(FeedbackActivity.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(FeedbackActivity.this,ListInfoActivity.class);
                        intent.putExtra("mail",mailing);
                        startActivity(intent);
                    }else{
                        Toast.makeText(FeedbackActivity.this, "Failed to send a feedback.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(FeedbackActivity.this,ListInfoActivity.class);
                        intent.putExtra("mail",mailing);
                        startActivity(intent);
                    }
                }
            }
        });
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FeedbackActivity.this,ListInfoActivity.class);
                intent.putExtra("mail",mailing);
                startActivity(intent);
            }
        });
    }
}