package com.example.rentalu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListInfoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fb1;
    DatabaseHelper databaseHelper;
    ArrayList<String> id,listing,property,bedroom,date,time,monthly_rent,furniture,remark,name,contact;
    CustomAdapter customAdapter;
    TextView data_prompt;
    String mailing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info);
        mailing=getIntent().getStringExtra("mail");
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        data_prompt=(TextView)findViewById(R.id.textView2);
        fb1=(FloatingActionButton) findViewById(R.id.addButton);
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListInfoActivity.this,AddActivity.class);
                intent.putExtra("mailing",mailing);
                startActivity(intent);
            }
        });
        databaseHelper=new DatabaseHelper(ListInfoActivity.this);
        id=new ArrayList<>();
        listing=new ArrayList<>();
        property=new ArrayList<>();
        bedroom=new ArrayList<>();
        date=new ArrayList<>();
        time=new ArrayList<>();
        monthly_rent=new ArrayList<>();
        furniture=new ArrayList<>();
        remark=new ArrayList<>();
        name=new ArrayList<>();
        contact=new ArrayList<>();
        storeData();
        customAdapter=new CustomAdapter(ListInfoActivity.this,this,id,listing,property,bedroom,date,time,monthly_rent,furniture,remark,name,contact);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListInfoActivity.this));
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeData(){
        Cursor cursor=databaseHelper.readAllData();
        if(cursor.getCount()==0){
            data_prompt.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                listing.add(cursor.getString(1));
                property.add(cursor.getString(2));
                bedroom.add(cursor.getString(3));
                date.add(cursor.getString(4));
                time.add(cursor.getString(5));
                monthly_rent.add(cursor.getString(6));
                furniture.add(cursor.getString(7));
                remark.add(cursor.getString(8));
                name.add(cursor.getString(9));
                contact.add(cursor.getString(10));
            }
            data_prompt.setVisibility(View.GONE);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_page,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(ListInfoActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.profile) {
            Intent intent=new Intent(ListInfoActivity.this,ProfileActivity.class);
            intent.putExtra("mailing",mailing);
            startActivity(intent);
        } else {
            Intent intent=new Intent(ListInfoActivity.this,FeedbackActivity.class);
            intent.putExtra("mailing",mailing);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}