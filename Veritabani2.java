package com.example.beyza.telefonrehberi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Veritabani2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "musteriler";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLO_KISILER = "kisiler";
    private static final String ROW_ID = "id";
    private static final String ROW_AD = "ad";
    private static final String ROW_SOYAD = "soyad";
    private static final String ROW_TEL = "tel";
    private static final String ROW_CINSIYET="cinsiyet";




    public Veritabani2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_KISILER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_AD + " TEXT NOT NULL, "
                + ROW_SOYAD + " TEXT NOT NULL, "
                + ROW_TEL + " TEXT NOT NULL, "
                + ROW_CINSIYET + " TEXT NOT NULL) ");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KISILER);
        onCreate(db);

    }

    public void VeriEkle(String ad,String soyad, String tel,String cinsiyet)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(ROW_AD,ad);
            cv.put(ROW_SOYAD,soyad);
            cv.put(ROW_TEL,tel);
            cv.put(ROW_CINSIYET,cinsiyet);
            db.insert(TABLO_KISILER, null,cv);
        }catch (Exception e)
        {

        }
        db.close();
    }

    public List<String> VeriListele()
    {
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=getReadableDatabase();

        try
        {
            String[] stunlar={ROW_ID, ROW_AD, ROW_SOYAD};
            Cursor cursor=db.query(TABLO_KISILER,stunlar,null,null,null,null,null);
            while(cursor.moveToNext())
            {


                veriler.add(cursor.getInt(0)
                        + " -         "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2));
                       /* + " - "
                        +cursor.getString(3)
                        + " - "
                        + cursor.getString(4));*/
            }
        }catch (Exception e)
        {

        }
        db.close();
        return veriler;

    }

    public boolean updateData(String id,String ad, String soyad, String telefon, String cinsiyet)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ROW_ID,id);
        contentValues.put(ROW_AD,ad);
        contentValues.put(ROW_SOYAD,soyad);
        contentValues.put(ROW_TEL,telefon);
        contentValues.put(ROW_CINSIYET, cinsiyet);
        db.update(TABLO_KISILER, contentValues, "ID= ?", new String[] { id });
        return true;
    }

    public void VeriSil(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // id ye göre verimizi siliyoruz
            String where = ROW_ID + " = " + id ;
            db.delete(TABLO_KISILER,where,null);
        }catch (Exception e){

        }
        db.close();
    }





}

