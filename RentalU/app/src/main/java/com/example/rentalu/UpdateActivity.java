package com.example.rentalu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText listing2,enterdate2,entertime2,monthlyrentprice2,remark2,namerep2,contactnum2;
    Button btndate2,btntime2,button2,delete_btn,returnbtn;
    Spinner spinner,spinner1,spinner2;
    String id,listing,property,bedroom,date,time,monthly_rent,furniture,remark,name,contact;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int year, month, dayofmonth;
    int hour, minute;
    String ampm;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        listing2=(EditText) findViewById(R.id.listing2);
        enterdate2=(EditText) findViewById(R.id.enterdate2);
        entertime2=(EditText) findViewById(R.id.entertime2);
        monthlyrentprice2=(EditText) findViewById(R.id.monthlyrentprice2);
        remark2=(EditText) findViewById(R.id.remark2);
        namerep2=(EditText) findViewById(R.id.namerep2);
        contactnum2=(EditText) findViewById(R.id.contactnum2);
        returnbtn=(Button)findViewById(R.id.return_btn);
        spinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.property_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner1=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.bedroom, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        spinner2=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.furnituretypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        delete_btn=(Button) findViewById(R.id.delete_btn);
        btndate2=(Button) findViewById(R.id.btndate2);
        btndate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        enterdate2.setText(i2 + "-" + (i1 + 1) + "-" + i);
                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });
        btntime2=(Button) findViewById(R.id.btntime2);
        btntime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        if (i >= 12) {
                            ampm = "PM";
                        } else {
                            ampm = "AM";
                        }
                        entertime2.setText(String.format("%02d:%02d", i, i1) + ampm);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        button2=(Button) findViewById(R.id.button2);
        intentData();
        ActionBar ab=getSupportActionBar();
        if(ab!=null){
            ab.setTitle("Rental Info " + id);
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(UpdateActivity.this);
                listing=listing2.getText().toString();
                property=spinner.getSelectedItem().toString();
                bedroom=spinner1.getSelectedItem().toString();
                date=enterdate2.getText().toString();
                time=entertime2.getText().toString();
                monthly_rent=monthlyrentprice2.getText().toString();
                furniture=spinner2.getSelectedItem().toString();
                remark=remark2.getText().toString();
                name=namerep2.getText().toString();
                contact=contactnum2.getText().toString();
                databaseHelper.updateData(id,listing,property,bedroom,date,time,monthly_rent,furniture,remark,name,contact);
                Intent intent=new Intent(UpdateActivity.this,ListInfoActivity.class);
                intent.putExtra("mail",contact);
                startActivity(intent);
            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog();
            }
        });
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateActivity.this,ListInfoActivity.class);
                intent.putExtra("mail",contact);
                startActivity(intent);
            }
        });
    }
    void intentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("listing") && getIntent().hasExtra("property") && getIntent().hasExtra("bedroom") && getIntent().hasExtra("date")
                && getIntent().hasExtra("time") && getIntent().hasExtra("monthly_rent") && getIntent().hasExtra("furniture")
                && getIntent().hasExtra("remark") && getIntent().hasExtra("name") && getIntent().hasExtra("contact")){
            id=getIntent().getStringExtra("id");
            listing=getIntent().getStringExtra("listing");
            property=getIntent().getStringExtra("property");
            bedroom=getIntent().getStringExtra("bedroom");
            date=getIntent().getStringExtra("date");
            time=getIntent().getStringExtra("time");
            monthly_rent=getIntent().getStringExtra("monthly_rent");
            furniture=getIntent().getStringExtra("furniture");
            remark=getIntent().getStringExtra("remark");
            name=getIntent().getStringExtra("name");
            contact=getIntent().getStringExtra("contact");

            listing2.setText(listing);
            selectValue(spinner,property);
            selectValue(spinner1,bedroom);
            enterdate2.setText(date);
            entertime2.setText(time);
            monthlyrentprice2.setText(monthly_rent);
            selectValue(spinner2,furniture);
            remark2.setText(remark);
            namerep2.setText(name);
            contactnum2.setText(contact);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete Rent Info " + id);
        builder.setMessage("Are you sure to delete rental info " + id + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper=new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteData(id);
                finish();
                Intent intent=new Intent(UpdateActivity.this,ListInfoActivity.class);
                intent.putExtra("mail",contact);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String s1selected=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}