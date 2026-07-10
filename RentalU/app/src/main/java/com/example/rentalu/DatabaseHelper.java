package com.example.rentalu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="Rental_data.db";
    private static final int DATABASE_VERSION=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table users(email TEXT primary key,password TEXT,color TEXT,movie TEXT)");
        sqLiteDatabase.execSQL("Create Table rentdata(id INTEGER primary key autoincrement,listing TEXT,property TEXT,bedroom TEXT,date TEXT,time TEXT,monthly_rent TEXT,furniture TEXT,remark TEXT,name TEXT,contact TEXT)");
        sqLiteDatabase.execSQL("Create Table feedback(name TEXT primary key,mail TEXT,subject TEXT,feed TEXT,rating FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS rentdata");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS feedback");
        onCreate(sqLiteDatabase);
    }
    public Boolean insertData(String email,String password,String color,String movie){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        cv.put("color",color);
        cv.put("movie",movie);
        long result=sqLiteDatabase.insert("users",null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean Feedback(String name,String mail,String subject,String feed,Float rating){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("mail",mail);
        cv.put("subject",subject);
        cv.put("feed",feed);
        cv.put("rating",rating);
        long result=sqLiteDatabase.insert("feedback",null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where email = ?",new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkEmailPassword(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where email = ? and password = ?",new String[]{email,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkColorMovieEmail(String email,String color,String movie) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email = ? and color = ? and movie = ?", new String[]{email, color,movie});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Cursor showPassword(String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where email = ?",new String[]{email});
        return cursor;
    }
    public Cursor showAll(String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from users where email = ?",new String[]{email});
        return cursor;
    }
    void addRentInfo(String listing,String property,String bedroom,String date,String time,String monthly_rent,
                     String furniture, String remark,String name,String contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("listing",listing);
        cv.put("property",property);
        cv.put("bedroom",bedroom);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("monthly_rent",monthly_rent);
        cv.put("furniture",furniture);
        cv.put("remark",remark);
        cv.put("name",name);
        cv.put("contact",contact);
        long result=db.insert("rentdata",null,cv);
        if(result==-1){
            Toast.makeText(context,"Failed to add",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query="SELECT * FROM rentdata";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id,String listing,String property,String bedroom,String date,
                    String time,String monthly_rent,String furniture,
                    String remark,String name,String contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("listing",listing);
        cv.put("property",property);
        cv.put("bedroom",bedroom);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("monthly_rent",monthly_rent);
        cv.put("furniture",furniture);
        cv.put("remark",remark);
        cv.put("name",name);
        cv.put("contact",contact);
        long result=db.update("rentdata",cv,"id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Failed to update",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show();
        }

    }
    void deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete("rentdata","id=?",new String[]{id});
        if(result==-1){
            Toast.makeText(context, "Can't delete the data.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteEverything(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("Delete from rentdata");
    }

}
