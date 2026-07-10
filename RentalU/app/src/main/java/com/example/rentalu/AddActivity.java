package com.example.rentalu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Button b1, dateselect, timeselect,returnbtn;
    EditText listing, monthly_rent, date, time, name, email, remark;
    Spinner s1, s2, s3;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int year, month, dayofmonth;
    int hour, minute;
    String ampm;
    Calendar calendar;
    private static String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        b1 = (Button) findViewById(R.id.button);
        mail=getIntent().getStringExtra("mailing");
        dateselect = (Button) findViewById(R.id.btndate);
        timeselect = (Button) findViewById(R.id.btntime);
        listing = (EditText) findViewById(R.id.listing);
        monthly_rent = (EditText) findViewById(R.id.monthlyrentprice);
        returnbtn=(Button)findViewById(R.id.return_btn);
        date = (EditText) findViewById(R.id.enterdate);
        time = (EditText) findViewById(R.id.entertime);
        name = (EditText) findViewById(R.id.namerep);
        email = (EditText) findViewById(R.id.mailing);
        email.setText(mail);
        remark = (EditText) findViewById(R.id.remark);
        s1 = (Spinner) findViewById(R.id.spinner);
        s2 = (Spinner) findViewById(R.id.spinner1);
        s3 = (Spinner) findViewById(R.id.spinner2);
        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2 + "-" + (i1 + 1) + "-" + i);
                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });
        timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        if (i >= 12) {
                            ampm = "PM";
                        } else {
                            ampm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", i, i1) + ampm);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listing.getText().toString().isEmpty() || s1.getSelectedItem().toString().isEmpty() || s2.getSelectedItem().toString().isEmpty() || monthly_rent.getText().toString().isEmpty() ||
                        date.getText().toString().isEmpty() || time.getText().toString().isEmpty() ||
                        name.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the required data", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                    databaseHelper.addRentInfo(listing.getText().toString().trim(), s1.getSelectedItem().toString().trim(),
                            s2.getSelectedItem().toString().trim(), date.getText().toString().trim(),
                            time.getText().toString().trim(), monthly_rent.getText().toString().trim(),
                            s3.getSelectedItem().toString().trim(), remark.getText().toString().trim(),
                            name.getText().toString().trim(), email.getText().toString().trim());
                    Intent intent=new Intent(AddActivity.this,ListInfoActivity.class);
                    intent.putExtra("mail",mail);
                    startActivity(intent);
                }
            }
        });
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddActivity.this,ListInfoActivity.class);
                intent.putExtra("mail",mail);
                startActivity(intent);
            }
        });
    }
}